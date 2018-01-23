package com.panietoar.callcenter.queue;

import com.panietoar.callcenter.model.Agent;
import com.panietoar.callcenter.model.AgentType;
import com.panietoar.callcenter.model.Call;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AssignCallTaskTest {

    AssignCallTask assignCallTask;

    List<Call> calls;
    AgentQueue agentQueue;
    Agent agent;

    @Before
    public void setup() {
        calls = mock(List.class);
        when(calls.isEmpty()).thenReturn(false).thenReturn(true);
        when(calls.remove(anyInt())).thenReturn(new Call(1, 200));

        agent = mock(Agent.class);
        when(agent.getType()).thenReturn(AgentType.OPERATOR);

        agentQueue = mock(AgentQueue.class);
        when(agentQueue.pollQueue()).thenReturn(agent);
    }

    @Test
    public void shouldAcceptCallWithOperator() throws InterruptedException {

        //if
        assignCallTask = new AssignCallTask(agentQueue, calls);
        String expectedMessage = "Call 1 has been assigned to agent of type OPERATOR. Duration:  200 ms";

        //when
        String actualMessage = assignCallTask.call();

        //then
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void shouldAcceptCallWithSupervisor() throws InterruptedException {
        //if
        when(agent.getType()).thenReturn(AgentType.SUPERVISOR);
        assignCallTask = new AssignCallTask(agentQueue, calls);
        String expectedMessage = "Call 1 has been assigned to agent of type SUPERVISOR. Duration:  200 ms";

        //when
        String actualMessage = assignCallTask.call();

        //then
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void shouldAcceptCallWithDirector() throws InterruptedException {
        //if
        when(agent.getType()).thenReturn(AgentType.DIRECTOR);
        assignCallTask = new AssignCallTask(agentQueue, calls);
        String expectedMessage = "Call 1 has been assigned to agent of type DIRECTOR. Duration:  200 ms";

        //when
        String actualMessage = assignCallTask.call();

        //then
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void shouldWorkWhileThereAreCalls() throws InterruptedException{

        when(calls.isEmpty()).thenReturn(false).thenReturn(false).thenReturn(true); // Accepts 2 calls
        assignCallTask = new AssignCallTask(agentQueue, calls);

        assignCallTask.call();

        verify(calls, times(2)).remove(anyInt());

    }

    @Test
    public void shouldWaitForAvailableAgent() throws InterruptedException{
        // Returns valid agent after two unsuccessful tries
        when(agent.getType()).thenReturn(AgentType.NOT_FOUND).thenReturn(AgentType.NOT_FOUND).thenReturn(AgentType.OPERATOR);
        assignCallTask = new AssignCallTask(agentQueue, calls);
        String expectedMessage = "Call 1 has been assigned to agent of type OPERATOR. Duration:  200 ms";

        //when
        String actualMessage = assignCallTask.call();

        //then
        assertEquals(expectedMessage, actualMessage);

    }

}
