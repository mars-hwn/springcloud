package com.alonginfo.controller;

/**
 * Author:hwn
 * Date:2020-03-13 11:02
 * Description:<描述>
 */
public class ChatMessage {

    private String contents;
    private long time;

    public ChatMessage() {

    }

    public ChatMessage(String contents, long time) {

        this.contents = contents;
        this.time = time;
    }

    public String getContents() {

        return contents;
    }

    public long getTime() {

        return time;
    }

    public void setTime(long time) {

        this.time = time;
    }

    @Override
    public String toString() {

        return "ChatMessage [contents=" + contents + ", time=" + time + "]";
    }

}