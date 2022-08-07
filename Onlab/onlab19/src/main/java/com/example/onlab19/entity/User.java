package com.example.onlab19.entity;

import javax.persistence.*;

import com.example.onlab19.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SqlResultSetMapping(
        name = "person_phone_count",
        classes = @ConstructorResult(
                targetClass = UserDto.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "email")

                }
        )
)

@NamedNativeQuery(
        name = "getUserinfo",
        resultSetMapping = "userInfo",
        query = "SELECT user.id, user.name, user.email " +
                "FROM user " +
                "WHERE user.email = ?1"
)


@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "avatar")
    private String avatar;
}
