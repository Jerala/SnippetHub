package edu.snippethub.dao;

import edu.snippethub.entity.Snippet;

import java.sql.SQLException;

public interface SnippetDAO {

    public Snippet addSnippet(Snippet snippet) throws SQLException;

    public void deleteSnippet(Snippet snippet) throws SQLException;

    public Snippet getSnippetById(Long id) throws SQLException;

}
