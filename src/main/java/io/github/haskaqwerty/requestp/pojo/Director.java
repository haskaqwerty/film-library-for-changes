package io.github.haskaqwerty.filmlibrary.pojo;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor

public class Director {
    private int id;
    private String lastname;
    private String firstname;
    private Date birthdayDate;
}
