package edu.snippethub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.snippethub.dao.SnippetDAO;
import edu.snippethub.dto.SnippetDTO;
import edu.snippethub.entity.Snippet;
import edu.snippethub.entity.User;
import edu.snippethub.model.SnippetModel;
import edu.snippethub.search.SnippetRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@Service
public class SnippetService {


    @Autowired
    private SnippetDAO snippetDAO;
    @Autowired
    private SnippetDTO snippetDTO;
    @Autowired
    private SnippetRepository snippetRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public Snippet save(Snippet snippet) {
        try {
            Snippet snippetWithId = snippetDAO.addSnippet(snippet);
            return snippetWithId;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void deleteSnippet(Snippet snippet) {
        try {
            snippetDAO.deleteSnippet(snippet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Snippet addSnippet(SnippetModel snippetModel, User user) throws NullPointerException {
        Snippet snippet = snippetDTO.createSnippetFromSnippetModel(snippetModel, user);
        save(snippet);
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(
                                "snippets/" + snippet.getSnippetId() + ".txt"), "utf-8"))) {
            writer.write(snippet.getSnippetText());
            return snippet;
        } catch (Exception e) {
            System.out.println(e);
            deleteSnippet(snippet);
        }
        return null;
    }

    public Snippet getSnippetById(Long id) {
        try {
            Snippet snippet = snippetDAO.getSnippetById(id);
            snippet.setSnippetText(getSnippetTextById(id));
            return snippet;
        } catch (NullPointerException | SQLException | IOException e) {
            return null;
        }
    }

    public String getSnippetTextById(Long id) throws IOException {
        String path = "./snippets/" + id + ".txt";
        String text = "";
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        text = new String(encoded, "UTF-8");
        return text;
    }

    public List<Snippet> search(String tags) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQuery(tags)
                        .field("tags.engAn")
                        .field("tags")
                        .field("snippetName")
                        .fuzziness(Fuzziness.ONE)
                        .prefixLength(1)
                        .type(MultiMatchQueryBuilder.Type.MOST_FIELDS))
                .build();
        Pageable pageRequest = new PageRequest(0, 10);
        Page<Snippet> result = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Page<Snippet>>() {
            @Override
            public Page<Snippet> extract(SearchResponse response) {
                List<Snippet> content = new ArrayList<>();
                SearchHit[] hits = response.getHits().getHits();
                ObjectMapper objectMapper = new ObjectMapper();
                Arrays.stream(hits).forEach(hit -> {
                    String source = hit.getSourceAsString();
                    try {
                        Snippet snippet = objectMapper.readValue(source, Snippet.class);
                        snippet.setScore(hit.getScore());
                        content.add(snippet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                return new PageImpl<Snippet>(content, pageRequest, response.getHits().getTotalHits());
            }
        });
        List<Snippet> snippets = result.getContent();
        return snippets;
    }

}
