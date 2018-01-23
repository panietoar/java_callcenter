package com.panietoar.callcenter.dispatch;

import com.panietoar.callcenter.exceptions.CallsOverflowException;
import com.panietoar.callcenter.model.Call;
import com.panietoar.callcenter.queue.AgentQueue;
import com.panietoar.callcenter.queue.AssignCallTask;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.panietoar.callcenter.Utils.getCallDuration;
import static com.panietoar.callcenter.Utils.getNewId;

public class Dispatcher {

    private static final int MAX_CALLS = 10;

    private AgentQueue agentQueue;
    private List<Call> callQueue;
    private ExecutorService executor;

    public Dispatcher(List<Call> callQueue, ExecutorService executor) {
        agentQueue = AgentQueue.createAgentQueue();
        this.callQueue = callQueue;
        this.executor = executor;
    }

    public void dispatchCall() {
        callQueue.add(new Call(getNewId(), getCallDuration()));
        try {
            peekCall();
            executor.submit(new AssignCallTask(agentQueue, callQueue));
        } catch (CallsOverflowException e) {
            System.err.println(e.getMessage());
        }
    }

    private void peekCall() throws CallsOverflowException {
        if (callQueue.size() > MAX_CALLS) {
            throw new CallsOverflowException();
        }
    }

}
