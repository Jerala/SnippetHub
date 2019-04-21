package edu.snippethub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.snippethub.entity.Snippet;
import edu.snippethub.entity.User;
import edu.snippethub.exception.ValueAlreadyExistException;
import edu.snippethub.model.UserModel;
import edu.snippethub.search.SnippetRepository;
import edu.snippethub.service.SnippetService;
import edu.snippethub.service.UserService;
import org.elasticsearch.action.search.SearchResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@RestController
public class UserController {

    @Autowired
    private SnippetRepository snippetRepository;
    @Autowired
    private SnippetService snippetService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private UserService userService;

    @GetMapping("/admin/1")
    public String helloA() {
        return "hello Admin!";
    }

    @GetMapping("/1")
    public List<Snippet> helloB() throws IOException {
//        Snippet snippet = new Snippet(1L, "cats", 1L, new Date(), 0L, 0,
//                new String[]{"cats", "fun"});
//        snippetRepository.save(snippet);
//        snippet = new Snippet(1L, "cats", 1L, new Date(), 0L, 0,
//                new String[]{"dogs", "fun"});
//        snippetRepository.save(snippet);
//        snippet = new Snippet(1L, "cats", 1L, new Date(), 0L, 0,
//                new String[]{"cats"});
//        snippetRepository.save(snippet);
//        Snippet snippet = new Snippet(1L, "cats", new Date(), 0L, 0,
//                new String[] {"cats", "dogs"});
//        snippet = snippetService.save(snippet);
//        snippetRepository.save(snippet);

//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(multiMatchQuery("cats do")
//                        .field("tags")
//                        .field("snippetName")
//                        .type(MultiMatchQueryBuilder.Type.MOST_FIELDS))
//                .build();
//        Pageable pageRequest = new PageRequest(0, 10);
//        Page<Snippet> result = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Page<Snippet>>() {
//            @Override
//            public Page<Snippet> extract(SearchResponse response) {
//                List<Snippet> content = new ArrayList<>();
//                SearchHit[] hits = response.getHits().getHits();
//                ObjectMapper objectMapper = new ObjectMapper();
//                Arrays.stream(hits).forEach(hit -> {
//                    String source = hit.getSourceAsString();
//                    try {
//                        Snippet snippet = objectMapper.readValue(source, Snippet.class);
//                        snippet.setScore(hit.getScore());
//                        content.add(snippet);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//                return new PageImpl<Snippet>(content, pageRequest, response.getHits().getTotalHits());
//            }
//        });
//        List<Snippet> snippets = result.getContent();
//        return snippets;
        return null;
    }

    @GetMapping("/user/1")
    public String helloC() {
        return "hello User!";
    }

    @GetMapping("/user/getByName")
    public User getUserName(Authentication authentication) {
        return userService.getUserByName(authentication.getName());
    }

    @PostMapping("/registerUser")
    public User addUser(@RequestBody UserModel userModel) {
        try {
            User user = userService.registerUser(userModel);
            return user;
        } catch(NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (ValueAlreadyExistException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }
}
