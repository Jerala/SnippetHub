package edu.snippethub.search;

import edu.snippethub.entity.Snippet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


import java.io.Serializable;
import java.util.List;

@Repository
public interface SnippetRepository extends ElasticsearchRepository<Snippet, Long> {
    @Query("{\"multi_match\": {\"query\": \"?0\", \"type\": \"most_fields\", " +
            "\"fields\": [\"tags\",\"snippetName\"]}}")
    Page<Snippet> findByTagsAndAndSnippetName(String tags, Pageable pageable);
}
