package aiss.GitHubMiner.service;

import aiss.GitHubMiner.model.Comment;
import aiss.GitHubMiner.model.Issue;
import aiss.GitHubMiner.model.User;
import aiss.GitHubMiner.model.dto.CommentDto;
import aiss.GitHubMiner.model.dto.IssueDto;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueService {

    @Value("${token}")
    private String token;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUri = "https://api.github.com/repos/";

    public Issue ConvertToModel(IssueDto issueDto) {
        UserDto authorDto = issueDto.getAuthor();
        UserDto assigneeDto = issueDto.getAssignee();

        User author = null;
        User assignee = null;

        if (authorDto != null) {
            author = new User(
                    authorDto.getId(),
                    authorDto.getUsername(),
                    authorDto.getName(),
                    authorDto.getAvatarUrl(),
                    authorDto.getWebUrl()
            );
        }

        if (assigneeDto != null) {
            assignee = new User(
                    assigneeDto.getId(),
                    assigneeDto.getUsername(),
                    assigneeDto.getName(),
                    assigneeDto.getAvatarUrl(),
                    assigneeDto.getWebUrl()
            );
        }

        return new Issue(
                issueDto.getId(),
                issueDto.getTitle(),
                issueDto.getDescription(),
                issueDto.getState(),
                issueDto.getCreatedAt(),
                issueDto.getUpdatedAt(),
                issueDto.getClosedAt(),
                issueDto.getLabels(),
                issueDto.getVotes(),
                author,
                assignee
        );
    }

    public List<Issue> getIssues(String owner, String repo, int sinceIssues, int maxPages) {
        String uri = baseUri + owner + "/" + repo + "/issues";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        List<Issue> allIssues = new ArrayList<>();
        int currentPage = 1;
        boolean hasMorePages = true;

        while (hasMorePages && currentPage <= maxPages) {
            ResponseEntity<IssueDto[]> response = restTemplate.exchange(
                    uri + "?page=" + currentPage,
                    HttpMethod.GET,
                    entity,
                    IssueDto[].class
            );

            if (response.getBody() != null) {
                for (IssueDto issueDto : response.getBody()) {
                    Issue issue = ConvertToModel(issueDto);

                    // Obtener comentarios usando el número del issue
                    String issueId = issueDto.getId();
                    List<Comment> comments = getComments(owner, repo, issueId);
                    issue.setComments(comments);

                    allIssues.add(issue);
                }
            }

            String linkHeader = response.getHeaders().getFirst("Link");
            if (linkHeader != null && linkHeader.contains("rel=\"next\"")) {
                currentPage++;
            } else {
                hasMorePages = false;
            }
        }

        return allIssues;
    }


    public List<Comment> getComments(String owner, String repo, String issueId) {
        return commentService.getComments(owner, repo, issueId);
    }

}
