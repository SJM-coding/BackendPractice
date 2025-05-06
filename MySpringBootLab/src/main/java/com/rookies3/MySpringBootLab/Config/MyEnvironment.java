package com.rookies3.MySpringBootLab.Config;

public class MyEnvironment {
    private String mode;

    public MyEnvironment(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}