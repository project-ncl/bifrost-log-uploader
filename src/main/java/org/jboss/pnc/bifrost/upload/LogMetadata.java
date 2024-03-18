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

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.jboss.pnc.bifrost.upload.BifrostLogUploader.HEADER_PROCESS_CONTEXT;
import static org.jboss.pnc.bifrost.upload.BifrostLogUploader.HEADER_PROCESS_CONTEXT_VARIANT;
import static org.jboss.pnc.bifrost.upload.BifrostLogUploader.HEADER_REQUEST_CONTEXT;
import static org.jboss.pnc.bifrost.upload.BifrostLogUploader.HEADER_TMP;

@Data
@Builder
public class LogMetadata {
    private OffsetDateTime endTime;
    private String loggerName;
    private BifrostTag tag;

    @NonNull
    private Map<String, String> headers = new HashMap<>();

    public void setProcessContext(String processContext) {
        headers.put(HEADER_PROCESS_CONTEXT, processContext);
    }

    public void setProcessContextVariant(String processContextVariant) {
        headers.put(HEADER_PROCESS_CONTEXT_VARIANT, processContextVariant);
    }

    public void setTmp(String tmp) {
        headers.put(HEADER_TMP, tmp);
    }

    public void setRequestContext(String requestContext) {
        headers.put(HEADER_REQUEST_CONTEXT, requestContext);
    }

    public static class LogMetadataBuilder {
        private Map<String, String> headers = new HashMap<>();

        public LogMetadataBuilder processContext(String processContext) {
            headers.put(HEADER_PROCESS_CONTEXT, processContext);
            return this;
        }

        public LogMetadataBuilder processContextVariant(String processContextVariant) {
            headers.put(HEADER_PROCESS_CONTEXT_VARIANT, processContextVariant);
            return this;
        }

        public LogMetadataBuilder tmp(String tmp) {
            headers.put(HEADER_TMP, tmp);
            return this;
        }

        public LogMetadataBuilder requestContext(String requestContext) {
            headers.put(HEADER_REQUEST_CONTEXT, requestContext);
            return this;
        }

    }
}
