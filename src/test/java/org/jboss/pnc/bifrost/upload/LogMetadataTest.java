package org.jboss.pnc.bifrost.upload;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogMetadataTest {

    @Test
    void getTag() {
        LogMetadata logMetadata = LogMetadata.builder().tag(TagOption.ALIGNMENT_LOG).build();
        Assertions.assertEquals(TagOption.ALIGNMENT_LOG.getTagName(), logMetadata.getTag());
    }
}