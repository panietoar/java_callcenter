package com.panietoar.callcenter.queue;

import com.panietoar.callcenter.factory.AgentFactory;
import com.panietoar.callcenter.model.Agent;
import com.panietoar.callcenter.model.AgentType;
import com.panietoar.callcenter.model.Assignable;

import java.util.List;

public class AgentQueue {

    private static final int OPERATOR_QNT = 6;
    private static final int SUPERVISOR_QNT = 2;
    private static final int DIRECTOR_QNT = 1;

    private List<Agent> operators;
    private List<Agent> supervisors;
    private List<Agent> directors;

    public static AgentQueue createAgentQueue() {
        AgentQueue agentQueue = new AgentQueue();
        agentQueue.setupOperators(OPERATOR_QNT);
        agentQueue.setupSupervisors(SUPERVISOR_QNT);
        agentQueue.setupDirectors(DIRECTOR_QNT);
        return agentQueue;
    }

    public Agent pollQueue() {
        return operators.stream().filter(Assignable::isAvailable).findFirst()
                .orElse(supervisors.stream().filter(Assignable::isAvailable).findFirst()
                .orElse(directors.stream().filter(Assignable::isAvailable).findFirst()
                .orElse(new Agent(AgentType.NOT_FOUND))));
    }

    public void setupOperators(int amountOfOperators) {
        operators = AgentFactory.newOperatorQueue(amountOfOperators);
    }

    public void setupSupervisors(int amountOfSupervisors) {
        supervisors = AgentFactory.newSupervisorQueue(amountOfSupervisors);
    }

    public void setupDirectors(int amountOfDirectors) {
        directors = AgentFactory.newDirectorQueue(amountOfDirectors);
    }

}
