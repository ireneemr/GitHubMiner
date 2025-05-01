package aiss.GitHubMiner.service;

import aiss.GitHubMiner.model.Comment;
import aiss.GitHubMiner.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    private final String baseUri = "https://api.github.com/";

    @Value("${token}")
    private String token;

    public List<Comment> getComments(String owner, String repo,String issueId) {
        String uri = baseUri + owner + "/" + repo + "/isssues" + issueId + "/comments";
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Comment[]> response = restTemplate.exchange(uri, HttpMethod.GET,
                entity, Comment[].class);

        List<Comment> comments = Arrays.stream(response.getBody()).toList();
        mapAuthorValues(comments);
        return comments;
    }

    public void mapAuthorValues(List<Comment> comments) {
        for (Comment comment : comments) {
            if(comment != null) {
                User commentAuthor=comment.getUser();
                String commentAuthorUsername=commentAuthor.getLogin();
                String commentAuthorName=commentAuthor.getLogin();
                String commentAuthorWebUrl= commentAuthor.getHtmlUrl();

                comment.setAuthor(commentAuthor);
                commentAuthor.setUsername(commentAuthorUsername);
                commentAuthor.setName(commentAuthorName);
                commentAuthor.setWebUrl(commentAuthorWebUrl);
            }
        }
    }


}
