package edu.snippethub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@ToString(exclude = "password")
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_name", length = 36, nullable = false)
    @NonNull
    private String userName;

    @JsonIgnore
    @Column(name = "password", length = 128, nullable = false)
    @NonNull
    private String password;

    @Column(name = "enabled", length = 2, nullable = false)
    @NonNull
    private boolean enabled;

    @Column(name = "email", length = 128, nullable = false)
    @NonNull
    private String email;

    @Column(name = "role", length = 10, nullable = false)
    @NonNull
    private String role;
}