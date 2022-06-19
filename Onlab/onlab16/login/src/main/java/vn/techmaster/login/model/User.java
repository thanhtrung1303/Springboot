package vn.techmaster.login.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private String id, fullname, email, hashedPassword;
    private State state;
}
