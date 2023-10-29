package io.github.haskaqwerty.filmlibrary.pojo;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Movie {
    private Integer id;
    private String name;
    private Integer releasedYear;
    private String directorFirstName;
    private String directorLastName;
    private String genre;
}
