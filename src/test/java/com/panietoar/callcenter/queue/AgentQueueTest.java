package com.panietoar.callcenter.queue;

import com.panietoar.callcenter.model.Agent;
import com.panietoar.callcenter.model.AgentType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AgentQueueTest {

    private AgentQueue queue;

    @Before
    public void setupTest() {
        queue = new AgentQueue();
    }

    @Test
    public void shouldPollOperator() {
        queue.setupOperators(1);
        queue.setupSupervisors(0); //No supervisors in queue
        queue.setupDirectors(0); //No directors in queue

        Agent agent = queue.pollQueue();

        assertEquals(AgentType.OPERATOR, agent.getType());
    }

    @Test
    public void shouldPollSupervisor() {
        queue.setupOperators(0); //No operators in queue
        queue.setupSupervisors(1);
        queue.setupDirectors(0); //No directors in queue

        Agent agent = queue.pollQueue();

        assertEquals(AgentType.SUPERVISOR, agent.getType());
    }

    @Test
    public void shouldPollDirector() {
        queue.setupOperators(0); //No operators in queue
        queue.setupSupervisors(0); //No supervisors in queue
        queue.setupDirectors(1);

        Agent agent = queue.pollQueue();

        assertEquals(AgentType.DIRECTOR, agent.getType());
    }

    @Test
    public void shouldNotFindAvailableAgents() {

        queue.setupOperators(0); //No operators in queue
        queue.setupSupervisors(0); //No supervisors in queue
        queue.setupDirectors(0); //No directors in queue

        Agent agent = queue.pollQueue();
        assertEquals(AgentType.NOT_FOUND, agent.getType());
    }

}
