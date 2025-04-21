package aiss.GitHubMiner.Service;

import aiss.GitHubMiner.model.Dto.IssueDto;
import aiss.GitHubMiner.model.Issue;
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
public class IssueService {

    @Value("${token}")
    private String token;

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUri = "https://api.github.com/repos/";

    public Issue ConvertToModel(IssueDto issueDto) {
        return new Issue(

        issueDto.getId(),
        issueDto.getTitle(),
        issueDto.getDescription(),
        issueDto.getState(),
        issueDto.getCreatedAt(),
        issueDto.getUpdatedAt(),
        issueDto.getClosedAt(),
        issueDto.getLabels(),
        issueDto.getVotes()
        );

    }
    public List<Issue> getIssues(String owner, String repo, int sinceIssues, int maxPages) {
        String uri = baseUri + owner + "/" + repo + "/issues";
        uri += "?since=" + sinceIssues + "&maxPages=" + maxPages;


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<IssueDto[]> response = restTemplate.exchange(uri,
                HttpMethod.GET, entity, IssueDto[].class);
        List<Issue> issues = new ArrayList<>();
        for (IssueDto issueDto : response.getBody()) {
            issues.add(ConvertToModel(issueDto));
        }
        return issues;
    }

}
