package com.panietoar.callcenter.factory;

import com.panietoar.callcenter.model.Agent;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static com.panietoar.callcenter.model.AgentType.*;

public class AgentFactoryTest {

    @Test
    public void shouldCreateOperatorQueue() {
        List<Agent> list = AgentFactory.newOperatorQueue(1);

        Agent agent = list.get(0);

        assertEquals(OPERATOR, agent.getType());
    }

    @Test
    public void shouldCreateSupervisorQueue() {
        List<Agent> list = AgentFactory.newSupervisorQueue(1);

        Agent agent = list.get(0);

        assertEquals(SUPERVISOR, agent.getType());
    }

    @Test
    public void shouldCreateDirectorQueue() {
        List<Agent> list = AgentFactory.newDirectorQueue(1);

        Agent agent = list.get(0);

        assertEquals(DIRECTOR, agent.getType());
    }

    @Test
    public void shouldCreateQueueWithRightSize() {
        int queueSize = 5;

        List<Agent> list = AgentFactory.newDirectorQueue(queueSize);

        assertEquals(queueSize, list.size());
    }

}
