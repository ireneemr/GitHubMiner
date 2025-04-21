package aiss.GitHubMiner.Service;

import aiss.GitHubMiner.model.Comment;
import aiss.GitHubMiner.model.Dto.CommentDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class CommentService {

    @Value("${token}")
    private String token;

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUri = "https://api.github.com/repos/";


    public Comment convertToModel(CommentDto commentDto) {
        return new Comment(
                commentDto.getId(),
                commentDto.getBody(),
                commentDto.getCreated_at(),
                commentDto.getUpdated_at()
        );
    }
    public List<Comment> getComments(String owner, String repo,String issueId) {
        String uri = baseUri + owner + "/" + repo + "/isssues" + issueId + "/comments";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<CommentDto[]> response = restTemplate.exchange(uri, HttpMethod.GET,
                                                entity, CommentDto[].class);

        List<Comment> comments = new ArrayList<>();
        for (CommentDto commentDto : response.getBody()) {
            comments.add(convertToModel(commentDto));
        }

        return comments;


    }
}