package aiss.GitHubMiner.Service;

import aiss.GitHubMiner.model.Dto.ProjectDto;
import aiss.GitHubMiner.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProjectService {

    @Value("${token}")
    private String token;

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUri = "https://api.github.com/repos/";

    public Project getProject(String owner, String repo) {

        String uri = baseUri + owner + "/" + repo;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<ProjectDto> response = restTemplate.exchange(
                uri, HttpMethod.GET, request, ProjectDto.class);

        ProjectDto dto = response.getBody();

        return new Project(
                dto.getId().toString(),
                dto.getName(),
                dto.getHtmlUrl()
        );
    }
}
