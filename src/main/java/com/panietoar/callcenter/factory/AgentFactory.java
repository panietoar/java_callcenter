package com.panietoar.callcenter.factory;

import com.panietoar.callcenter.model.Agent;
import com.panietoar.callcenter.model.AgentType;

import java.util.ArrayList;
import java.util.List;

public class AgentFactory {

    private AgentFactory() {

    }

    public static List<Agent> newOperatorQueue(int amount) {
        List<Agent> queue = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            queue.add(new Agent(AgentType.OPERATOR));
        }

        return queue;
    }

    public static List<Agent> newSupervisorQueue(int amount) {
        List<Agent> queue = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            queue.add(new Agent(AgentType.SUPERVISOR));
        }

        return queue;
    }

    public static List<Agent> newDirectorQueue(int amount) {
        List<Agent> queue = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            queue.add(new Agent(AgentType.DIRECTOR));
        }

        return queue;
    }
}
