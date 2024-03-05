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

import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.util.TimeValue;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

class BifrostHttpRequestRetryStrategy implements HttpRequestRetryStrategy {
    private final int maxRetries;
    private final int delay;

    public BifrostHttpRequestRetryStrategy(int maxRetries, int delay) {
        this.maxRetries = maxRetries;
        this.delay = delay;
    }

    @Override
    public boolean retryRequest(HttpRequest request, IOException exception, int execCount, HttpContext context) {
        boolean exceptionBad = !(exception instanceof SSLException);
        return exceptionBad && execCount < maxRetries;
    }

    @Override
    public boolean retryRequest(HttpResponse response, int execCount, HttpContext context) {
        boolean codeBad = response.getCode() == HttpStatus.SC_TOO_MANY_REQUESTS || response.getCode() >= 500;
        return codeBad && execCount < maxRetries;
    }

    @Override
    public TimeValue getRetryInterval(HttpResponse response, int execCount, HttpContext context) {
        return TimeValue.of((long) execCount * delay, TimeUnit.SECONDS);
    }
}
