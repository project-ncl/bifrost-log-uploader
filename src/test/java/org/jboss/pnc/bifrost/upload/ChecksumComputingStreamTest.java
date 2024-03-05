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

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class ChecksumComputingStreamTest {


    @Test
    public void computeStringChecksums() throws IOException {
        String message = "My message";

        String md5SumExpected = "be59f66a07d05b2c52a1387d1f62d753";
        String sha512SumExpected = "4ad19663eb6877501dce4efc91f3051487b7b02fbbf61656ccb56ae877c005607dbc08c473bbcfbed1f9755644e5e1af4e316b79a0df605f1f7f323597734998";

        String md5Sum;
        String sha512Sum;
        try (ChecksumComputingStream checksums = ChecksumComputingStream.computeChecksums(new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8)))) {
            md5Sum = checksums.getMD5Sum();
            sha512Sum = checksums.getSHA512Sum();
        }

        assertEquals(md5SumExpected, md5Sum.toLowerCase());
        assertEquals(sha512SumExpected, sha512Sum.toLowerCase());
    }


}