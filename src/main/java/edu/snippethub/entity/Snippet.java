package edu.snippethub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Document(indexName = "snippethub", type = "snippet")
@Entity
@Table(name = "Snippet")
@ToString(exclude = {"likes","user"})
public class Snippet {

    @Column(name = "snippet_id", nullable = false)
    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long snippetId;

    @Column(name = "pl_id", length = 5, nullable = false)
    @NonNull
    private Long plId;

    @Field(type = FieldType.text)
    @Column(name = "snippet_name", length = 100, nullable = false)
    @NonNull
    private String snippetName;

    @Column(name = "upload_date", nullable = false)
    @NonNull
    private Date uploadDate;

    @Column(name = "like_count", length = 10, nullable = false)
    @NonNull
    private Long likeCount;

    @Column(name = "approved", length = 1, nullable = false)
    @NonNull
    private Integer approved;

    @MultiField(
            mainField = @Field(type = FieldType.text, fielddata = true),
            otherFields = {
                    @InnerField(suffix = "engAn", indexAnalyzer = "english", type = FieldType.text)
            }
    )
    @Column(name = "tags", nullable = true)
    @NonNull
    private String tags;

    @OneToMany(mappedBy = "snippet")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Like> likes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    private Float score;

    @Transient
    private String snippetText;
}
