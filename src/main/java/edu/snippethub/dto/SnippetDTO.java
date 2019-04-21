package edu.snippethub.dto;

import edu.snippethub.entity.Snippet;
import edu.snippethub.entity.User;
import edu.snippethub.model.SnippetModel;

public interface SnippetDTO {

    public Snippet createSnippetFromSnippetModel(SnippetModel snippetModel, User user) throws NullPointerException;

}
