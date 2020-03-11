package io.cronitor.client;

public enum Command {

    RUN("run"),
    PAUSE("pause"),
    COMPLETE("complete"),
    FAIL("fail");

    private String value;

    Command(String value) {

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
