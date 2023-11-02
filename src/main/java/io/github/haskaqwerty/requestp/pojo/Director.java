package io.github.haskaqwerty.requestp.pojo;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Director {
    private int id;
    private String lastname;
    private String firstname;
    private Date birthdayDate;
}
