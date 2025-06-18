package fr.formation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
// @AllArgsConstructor
@Builder
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Photo {
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

    private int albumId;
    private String albumTitle;
}
