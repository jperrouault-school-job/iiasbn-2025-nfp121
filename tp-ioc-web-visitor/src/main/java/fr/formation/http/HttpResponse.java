package fr.formation.http;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class HttpResponse {
    private HttpResponseStatus status;
    private HttpContentType contentType;
    private String content;
}
