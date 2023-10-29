package io.github.haskaqwerty.filmlibrary.pojo;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@Setter
@ToString
public class Genre {
    private int id;
    private String name;
    public Genre(int id, String name){
        this.name=name;
        this.id=id;
    }

}
