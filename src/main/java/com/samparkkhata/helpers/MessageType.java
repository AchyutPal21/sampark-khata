package com.samparkkhata.helpers;

public enum MessageType {
    SUCCESS("green"), WARNING("yellow"), ERROR("red"), INFO("blue");

    private final String text;

    MessageType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
