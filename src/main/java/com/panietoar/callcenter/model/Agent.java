package com.panietoar.callcenter.model;

public class Agent implements Assignable{

    private AgentType type;
    private boolean available;

    public Agent(AgentType type) {
        this.type = type;
        available = true;
    }

    public AgentType getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
