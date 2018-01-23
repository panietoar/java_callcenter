package com.panietoar.callcenter.dispatch;

import com.panietoar.callcenter.exceptions.CallsOverflowException;
import com.panietoar.callcenter.model.Call;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DispatcherTest {

    ExecutorService executor;

    List<Call> callQueue;

    Dispatcher dispatcher;

    @Test
    public void shouldAddCallToQueue() {
        callQueue = mock(List.class);
        executor = mock(ExecutorService.class);
        dispatcher = new Dispatcher(callQueue, executor);

        dispatcher.dispatchCall();

        verify(callQueue).add(any(Call.class));

    }

    @Test
    public void shouldCreateRightAmountOfThreads() {

        callQueue = new ArrayList<>();
        executor = mock(ExecutorService.class);
        dispatcher = new Dispatcher(callQueue, executor);

        for (int i = 0; i < 12; i++) {
            dispatcher.dispatchCall();
        }

        verify(executor, times(10)).submit(any(Callable.class));
    }

}
