package aiss.GitHubMiner.Service;

import aiss.GitHubMiner.model.Commit;
import aiss.GitHubMiner.model.Dto.CommitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommitService {

    @Value("${token}")
    private String token;

    @Autowired
    RestTemplate restTemplate;

    private final String baseUri = "https://api.github.com/repos/";

    public List<Commit> getCommits(String owner, String repo, int sinceDays, int maxPages) {

        List<Commit> result = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + token);

        for (int i = 1; i <= maxPages; i++) {
            String uri = baseUri + owner + "/" + repo + "/commits?page=" + i + "&per_page=30";

            HttpEntity<String> request = new HttpEntity<>(null, headers);
            ResponseEntity<CommitDto[]> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    request,
                    CommitDto[].class
            );

            CommitDto[] commitDtos = response.getBody();

            if (commitDtos == null || commitDtos.length == 0) {
                break; // No hay más commits
            }

            for (CommitDto dto : commitDtos) {
                // Construir el modelo Commit
                Commit commit = new Commit(
                        dto.getSha(),
                        dto.getCommit().getMessage().split("\n")[0], // Título = primera línea
                        dto.getCommit().getMessage(),
                        dto.getCommit().getAuthor().getName(),
                        dto.getCommit().getAuthor().getEmail(),
                        dto.getCommit().getAuthor().getDate(),
                        dto.getHtmlUrl()
                );
                result.add(commit);
            }
        }

        return result;
    }
}
