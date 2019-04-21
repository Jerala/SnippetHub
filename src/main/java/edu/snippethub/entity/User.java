package edu.snippethub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = {"likes", "snippets", "password"})
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

    @Column(name = "user_name", length = 36, nullable = false, unique = true)
    @NonNull
    private String userName;

    @JsonIgnore
    @Column(name = "password", length = 128, nullable = false)
    @NonNull
    private String password;

    @Column(name = "enabled", length = 2, nullable = false)
    @NonNull
    private boolean enabled;

    @Column(name = "email", length = 128, nullable = false, unique = true)
    @NonNull
    private String email;

    @Column(name = "role", length = 10, nullable = false)
    @NonNull
    private String role;

    @OneToMany(mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Snippet> snippets;

    @OneToMany(mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Like> likes;
}