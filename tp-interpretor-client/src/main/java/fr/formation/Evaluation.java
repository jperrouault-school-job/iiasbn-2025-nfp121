package fr.formation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class Evaluation {
    @Builder.Default
    private int type = -1;
    
    private byte[] value;
}
