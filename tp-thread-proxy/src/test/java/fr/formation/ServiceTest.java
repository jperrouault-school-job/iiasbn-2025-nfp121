package fr.formation;

import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServiceTest {
    @Mock
    private PrintStream out;

    @InjectMocks
    private Service service;

    @BeforeEach
    void beforeEach() {
        System.setOut(this.out);
    }

    @Test
    void shouldNoThreadPrint() {
        // Given

        // When
        service.noThread();

        // Then
        Mockito.verify(this.out).println("Pas de thread ...");
    }

    @Test
    void shouldWithThreadPrint() {
        // Given

        // When
        service.withThread();

        // Then
        Mockito.verify(this.out).println("Thread ici main");
    }

    @Test
    void shouldWithOtherThreadPrint() {
        // Given

        // When
        service.withOtherThread();

        // Then
        Mockito.verify(this.out).println("Autre thread ici main");
    }

    @Test
    void shouldZxecTenTimesPrint() {
        // Given

        // When
        service.execTenTimes();

        // Then
        Mockito.verify(this.out, Mockito.times(10)).println("Exécution toutes les secondes : main");
    }
}
