package com.panietoar.callcenter.queue;

import com.panietoar.callcenter.model.Agent;
import com.panietoar.callcenter.model.AgentType;
import com.panietoar.callcenter.model.Call;

import java.util.List;
import java.util.concurrent.Callable;

public class AssignCallTask implements Callable<String> {

    private static final int FIRST_ELEMENT_INDEX = 0;

    private AgentQueue agentQueue;

    private List<Call> callQueue;

    public AssignCallTask(AgentQueue agentQueue, List<Call> callQueue) {
        this.agentQueue = agentQueue;
        this.callQueue = callQueue;
    }

    @Override
    public String call() throws InterruptedException {
        String message = "";
        Agent agent = agentQueue.pollQueue();
        while (!callQueue.isEmpty()) {
            while (agent.getType() == AgentType.NOT_FOUND) {
                agent = agentQueue.pollQueue();
            }
            try {
                Call call = callQueue.remove(FIRST_ELEMENT_INDEX);
                message = callTakenMessage(call, agent);
                System.out.println(message);
                takeCall(agent, call);
            } catch (IndexOutOfBoundsException | InterruptedException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }
        return message;
    }

    private void takeCall(Agent agent, Call call) throws InterruptedException{
        agent.setAvailable(false);
        Thread.sleep(call.getDuration());
        agent.setAvailable(true);
        System.out.println("Call " + call.getId() + " finished.");
    }

    private String callTakenMessage(Call call, Agent agent) {
        return new StringBuilder().append("Call ")
                .append(call.getId())
                .append(" has been assigned to agent of type ")
                .append(agent.getType())
                .append(". Duration:  ")
                .append(call.getDuration())
                .append(" ms")
                .toString();
    }
}
