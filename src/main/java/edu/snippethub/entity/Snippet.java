package edu.snippethub.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Document(indexName = "snippethub", type = "snippet")
@Entity
@Table(name = "Snippet")
public class Snippet {

    @Column(name = "snippet_id", nullable = false)
    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long snippetId;

    @Column(name = "pl_id", length = 5, nullable = false)
    @NonNull
    private Long plId;

    @Field(type = FieldType.keyword)
    @Column(name = "snippet_name", length = 100, nullable = false)
    @NonNull
    private String snippetName;

    @Column(name = "user_id", length = 15, nullable = false)
    @NonNull
    private Long userId;

    @Column(name = "upload_date", nullable = false)
    @NonNull
    private Date uploadDate;

    @Column(name = "like_count", length = 10, nullable = false)
    @NonNull
    private Long likeCount;

    @Column(name = "approved", length = 1, nullable = false)
    @NonNull
    private Integer approved;

    @Field(type = FieldType.keyword)
    @Column(name = "tags", nullable = true)
    @NonNull
    private String tags;

    @Transient
    private Float score;
}
