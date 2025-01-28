package fr.formation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class DataSource {
    private String host;
    private int port;
    private boolean secure;
    private String username;
    private String password;
}
