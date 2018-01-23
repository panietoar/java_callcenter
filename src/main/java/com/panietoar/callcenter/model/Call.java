package com.panietoar.callcenter.model;

public class Call {

    private long id;
    private long duration;

    public Call(long id, long duration) {
        this.id = id;
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
