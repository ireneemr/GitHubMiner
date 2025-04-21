package aiss.GitHubMiner.controller;

import aiss.GitHubMiner.model.Issue;
import aiss.GitHubMiner.Service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class IssueController {

    @Autowired
    public IssueService issueService;

    @GetMapping("/repos/{owner}/{repo}/issues")
    public List<Issue> getIssues(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo,
            @RequestParam(value = "sinceIssues", defaultValue = "20") int sinceIssues,
            @RequestParam(value = "maxPages", defaultValue = "2") int maxPages) {
        return issueService.getIssues(owner, repo, sinceIssues, maxPages);
    }

    @PostMapping ("/repos/{owner}/{repo}/issues")
    public List<Issue> postIssues(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo,
            @RequestParam(value = "sinceIssues", defaultValue = "20") int sinceIssues,
            @RequestParam(value = "maxPages", defaultValue = "2") int maxPages) {
        return issueService.getIssues(owner, repo, sinceIssues, maxPages);
    }
}