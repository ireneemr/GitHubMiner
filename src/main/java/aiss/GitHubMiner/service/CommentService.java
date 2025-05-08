package aiss.GitHubMiner.service;

import aiss.GitHubMiner.model.Comment;
import aiss.GitHubMiner.model.User;
import aiss.GitHubMiner.model.dto.CommentDto;
import aiss.GitHubMiner.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Value("${token}")
    private String token;

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUri = "https://api.github.com/repos/";


    public Comment convertToModel(CommentDto commentDto) {
        UserDto authorDto = commentDto.getAuthor();
        User author = null;
        if (authorDto != null) {
            author = new User(
                    authorDto.getId(),
                    authorDto.getUsername(),
                    authorDto.getName(),
                    authorDto.getAvatarUrl(),
                    authorDto.getWebUrl()
            );
        }
        return new Comment(
                commentDto.getId(),
                commentDto.getBody(),
                commentDto.getCreated_at(),
                commentDto.getUpdated_at(),
                author
        );
    }
    public List<Comment> getComments(String owner, String repo, String issueId) {
        String uri = baseUri + owner + "/" + repo + "/issues/" + issueId + "/comments";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<CommentDto[]> response = restTemplate.exchange(
                    uri, HttpMethod.GET, entity, CommentDto[].class);

            List<Comment> comments = new ArrayList<>();
            if (response.getBody() != null) {
                for (CommentDto commentDto : response.getBody()) {
                    comments.add(convertToModel(commentDto));
                }
            }

            return comments;
        } catch (Exception e) {
            System.err.println("ERROR fetching comments: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

}
