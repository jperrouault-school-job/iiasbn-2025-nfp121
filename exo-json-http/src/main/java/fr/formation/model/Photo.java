package fr.formation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
// @AllArgsConstructor
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Photo {
    private int id;
    private int albumId;

    private String title;
    private String url;
    private String thumbnailUrl;
}
