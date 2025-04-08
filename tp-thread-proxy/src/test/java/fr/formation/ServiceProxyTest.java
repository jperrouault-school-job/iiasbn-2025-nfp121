package fr.formation;

import java.util.concurrent.ExecutorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServiceProxyTest {
    @Mock
    private Service service;

    @Mock
    private ExecutorService threadPool;

    @InjectMocks
    private ServiceProxy proxy;

    @Captor
    private ArgumentCaptor<Runnable> runnableCaptor;

    @Test
    void shouldNoThreadNotCreateThread() {
        // Given

        // When
        proxy.noThread();

        // Then
        Mockito.verify(this.threadPool, Mockito.never()).submit(Mockito.any(Runnable.class));
    }

    @Test
    void shouldWithThreadCreateThreadAndCallMainMethod() {
        // Given
        Mockito.when(this.threadPool.submit(this.runnableCaptor.capture())).thenReturn(null);

        // When
        proxy.withThread();

        // Then
        Runnable submittedTask = this.runnableCaptor.getValue();
        submittedTask.run();

        Mockito.verify(this.service).withThread();
    }

    @Test
    void shouldWithOtherThreadCreateThreadAndCallMainMethod() {
        // Given
        Mockito.when(this.threadPool.submit(this.runnableCaptor.capture())).thenReturn(null);

        // When
        proxy.withOtherThread();

        // Then
        Runnable submittedTask = this.runnableCaptor.getValue();
        submittedTask.run();

        Mockito.verify(this.service).withOtherThread();
    }

    @Test
    void shouldExecTenTimesCreateThreadAndCallMainMethod() {
        // Given
        Mockito.when(this.threadPool.submit(this.runnableCaptor.capture())).thenReturn(null);

        // When
        proxy.execTenTimes();

        // Then
        Runnable submittedTask = this.runnableCaptor.getValue();
        submittedTask.run();

        Mockito.verify(this.service).execTenTimes();
    }
}
