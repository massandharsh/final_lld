package dev.harsh.tictactoe.models;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {
    private String name;
    private String email;
    private String photo;
}
