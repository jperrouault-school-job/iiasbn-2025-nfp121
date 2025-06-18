package fr.formation.http;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class HttpRequest {
    private String path;

    @Builder.Default
    private Map<String, String> headers = new HashMap<>();
}
