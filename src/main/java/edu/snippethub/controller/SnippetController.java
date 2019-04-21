package edu.snippethub.controller;

import edu.snippethub.entity.Snippet;
import edu.snippethub.entity.User;
import edu.snippethub.model.SnippetModel;
import edu.snippethub.search.SnippetRepository;
import edu.snippethub.service.SnippetService;
import edu.snippethub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class SnippetController {

    @Autowired
    private UserService userService;
    @Autowired
    private SnippetService snippetService;
    @Autowired
    private SnippetRepository snippetRepository;

    @PostMapping("/user/addSnippet")
    public Snippet addSnippet(@RequestBody SnippetModel snippetModel,
                              Authentication authentication) {
        // TODO check for duplicate
        try {
            User user = userService.getUserByName(authentication.getName());
            Snippet snippet = snippetService.addSnippet(snippetModel, user);
            snippetRepository.save(snippet);
            return snippet;
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PostMapping("/getSnippetById")
    public Snippet getSnippet(@RequestParam(name = "id", required = true) Long id) {
        Snippet snippet = snippetService.getSnippetById(id);
        if (snippet != null) {
            return snippet;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no snippet with such id");
    }

    @PostMapping("/search")
    public List<Snippet> searchSnippets(@RequestParam(name = "tags") String tags) {
        List<Snippet> snippets = snippetService.search(tags);
        if (!snippets.isEmpty()) {
            return snippets;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no snippets found :(");
    }
}
