package org.jboss.pnc.bifrost.upload;

/**
 * Specify the tag for a final-log. This is used to provide some consistency in the naming of the tag for the various
 * PNC applications.
 */
public enum TagOption {

    BUILD_LOG("build-log"),
    ALIGNMENT_LOG("alignment-log"),
    BREW_PUSH_LOG("brew-push-log");

    private final String TAG_NAME;


    private TagOption(final String tagName) {
        this.TAG_NAME = tagName;
    }

    public String getTagName() {
        return TAG_NAME;
    }
}
