package aiss.GitHubMiner.controller;

import aiss.GitHubMiner.model.Project;
import aiss.GitHubMiner.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/githubminer")
public class GitHubController {

    @Autowired
    ProjectService projectService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{owner}/{repo}")
    public Project getData(@PathVariable String owner, @PathVariable String repo,
                           @RequestParam(defaultValue = "5") Integer sinceCommits,
                           @RequestParam(defaultValue = "20") Integer sinceIssues,
                           @RequestParam(defaultValue = "2") Integer maxPages) {
        return projectService.getProject(owner, repo, sinceCommits, sinceIssues, maxPages);
    }

    @PostMapping("/{owner}/{repo}")
    public ResponseEntity<String> sendProject(@PathVariable String owner,
                                              @PathVariable String repo,
                                              @RequestParam(defaultValue = "5") Integer sinceCommits,
                                              @RequestParam(defaultValue = "30") Integer sinceIssues,
                                              @RequestParam(defaultValue = "2") Integer maxPages) {
        Project project = projectService.getProject(owner, repo, sinceCommits, sinceIssues, maxPages);
        projectService.sendProjectToGitMiner(project);
        return ResponseEntity.ok("Project sent successfully to GitMiner");
    }

}
