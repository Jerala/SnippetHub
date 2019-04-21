package edu.snippethub.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id", nullable = false)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
//    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User user;

    @ManyToOne
    @JoinColumn(name = "snippet_id")
//    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Snippet snippet;
}
