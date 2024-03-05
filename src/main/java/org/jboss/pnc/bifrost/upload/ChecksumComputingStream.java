/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2024-2024 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.bifrost.upload;

import javax.xml.bind.DatatypeConverter;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChecksumComputingStream extends FilterInputStream {

    private final MessageDigest md5;
    private final MessageDigest sha512;

    private ChecksumComputingStream(InputStream stream, MessageDigest md5, MessageDigest sha512) {
        super(stream);
        this.md5 = md5;
        this.sha512 = sha512;
    }

    public static ChecksumComputingStream of(InputStream is) {
        MessageDigest md5;
        MessageDigest sha512;
        try {
            md5 = MessageDigest.getInstance("MD5");
            sha512 = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        DigestInputStream md5dis = new DigestInputStream(is, md5);
        DigestInputStream sha512dis = new DigestInputStream(md5dis, sha512);
        return new ChecksumComputingStream(sha512dis, md5, sha512);
    }

    public static ChecksumComputingStream computeChecksums(InputStream is) throws IOException {
        ChecksumComputingStream cheksumStream = of(is);
        cheksumStream.readFully();
        return cheksumStream;
    }

    public String getMD5Sum() {
        return DatatypeConverter.printHexBinary(md5.digest());
    }

    public String getSHA512Sum() {
        return DatatypeConverter.printHexBinary(sha512.digest());
    }

    public void readFully() throws IOException {
        while (true) {
            if (read() == -1) break;
        }
    }
}