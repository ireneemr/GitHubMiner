package aiss.GitHubMiner.Service;

import aiss.GitHubMiner.model.Commit;
import aiss.GitHubMiner.model.Dto.ProjectDto;
import aiss.GitHubMiner.model.Issue;
import aiss.GitHubMiner.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProjectService {

    @Value("${token}")
    private String token;

    final String gitMinerUri= "http://localhost:8080/githubminer/projects";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CommitService commitService;

    @Autowired
    private IssueService issueService;

    private final String baseUri = "https://api.github.com/repos/";

    public Project getProject(String owner, String repo, Integer sinceCommits, Integer sinceIssues, Integer maxPages) {
        String uri = baseUri + owner + "/" + repo;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<ProjectDto> response = restTemplate.exchange(
                uri, HttpMethod.GET, request, ProjectDto.class
        );

        ProjectDto dto = response.getBody();

        Project project = new Project(
                dto.getId().toString(),
                dto.getName(),
                dto.getHtmlUrl()
        );

        List<Commit> commits = commitService.getCommits(owner, repo, sinceCommits, maxPages);
        List<Issue> issues = issueService.getIssues(owner, repo, sinceIssues, maxPages);

        project.setCommits(commits);
        project.setIssues(issues);

        return project;
    }

    public void sendProjectToGitMiner(Project project) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Project> request = new HttpEntity<>(project, headers);

        restTemplate.postForEntity(gitMinerUri, request, Void.class);
    }
}
