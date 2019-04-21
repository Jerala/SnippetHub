package edu.snippethub.dto;

import edu.snippethub.entity.Snippet;
import edu.snippethub.entity.User;
import edu.snippethub.model.SnippetModel;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SnippetDTOImpl implements SnippetDTO {

    public Snippet createSnippetFromSnippetModel(SnippetModel snippetModel, User user) throws NullPointerException {
        Snippet snippet = new Snippet();
        snippet.setUser(user);
        snippet.setSnippetName(snippetModel.getSnippetName());
        snippet.setTags(snippetModel.getTags());
        snippet.setSnippetText(snippetModel.getSnippetText());
        snippet.setApproved(0);
        snippet.setLikeCount(0L);
        // TODO
        snippet.setPlId(1L);
        snippet.setUploadDate(new Date());
        return snippet;
    }

}
