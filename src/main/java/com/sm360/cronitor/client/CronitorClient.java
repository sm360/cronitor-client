package com.sm360.cronitor.client;

public class CronitorClient {

    private String apiKey;

    public CronitorClient() {

        this("");
    }

    public CronitorClient(String apiKey) {

        this.apiKey = apiKey;
    }

    public Monitor getMonitor(String monitorCode) {

        return new Monitor(apiKey, monitorCode);
    }
}
