package edu.snippethub.service;

import edu.snippethub.dao.SnippetDAO;
import edu.snippethub.entity.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class SnippetService {


    @Autowired
    private SnippetDAO snippetDAO;

    public Snippet save(Snippet snippet) {
        try {
           Snippet snippetWithId = snippetDAO.addSnippet(snippet);
           return snippetWithId;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
