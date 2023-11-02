package io.github.haskaqwerty.requestp.pojo;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private Integer id;
    private String name;
    private Integer releasedYear;
    private String directorFirstName;
    private String directorLastName;
    private String genre;
}
