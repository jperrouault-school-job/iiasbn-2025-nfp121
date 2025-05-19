package fr.formation.service;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.model.Photo;
import fr.formation.proxy.HttpClientProxy;

@ExtendWith(MockitoExtension.class)
class PhotoServiceTest {
    @Mock
    private HttpClientProxy httpClient;
    
    @Mock
    private ObjectMapper mapper;
    
    @InjectMocks
    private PhotoService service;

    @Test
    void shouldFindAllCallServices() throws Exception {
        // given
        HttpResponse resp = Mockito.mock(HttpResponse.class);

        Mockito
            .when(this.httpClient.send(Mockito.any(), Mockito.any()))
            .thenReturn(resp)
        ;

        Mockito.when(resp.body())
            .thenReturn(new byte[0]);
        ;

        List<Photo> expected = List.of(Photo.builder().build());

        Mockito
            .when(this.mapper.readValue(Mockito.any(byte[].class), Mockito.any(TypeReference.class)))
            .thenReturn(expected)
        ;

        // when
        List<Photo> result = this.service.findAll();

        // then
        Assertions.assertEquals(expected, result);

        Mockito.verify(this.httpClient).send(Mockito.any(), Mockito.any());
        Mockito.verify(this.mapper).readValue(Mockito.any(byte[].class), Mockito.any(TypeReference.class));
    }
}
