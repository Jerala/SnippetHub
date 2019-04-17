package edu.snippethub.dao;

import edu.snippethub.entity.Snippet;

import java.sql.SQLException;

public interface SnippetDAO {

    public Snippet addSnippet(Snippet snippet) throws SQLException;

}
