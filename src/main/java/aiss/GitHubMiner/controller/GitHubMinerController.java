package aiss.GitHubMiner.controller;

import aiss.GitHubMiner.model.Comment;
import aiss.GitHubMiner.model.Commit;
import aiss.GitHubMiner.model.Issue;
import aiss.GitHubMiner.model.Project;
import aiss.GitHubMiner.Service.CommentService;
import aiss.GitHubMiner.Service.CommitService;
import aiss.GitHubMiner.Service.IssueService;
import aiss.GitHubMiner.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/repos/{owner}/{repo}")
public class GitHubMinerController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommitService commitService;

    @Autowired
    private IssueService issueService;

    @Autowired
    private ProjectService projectService;

    // Project endpoints
    @GetMapping
    public Project getProject(@PathVariable("owner") String owner, @PathVariable("repo") String repo) {
        return projectService.getProject(owner, repo);
    }

    @PostMapping
    public Project postProject(@PathVariable("owner") String owner, @PathVariable("repo") String repo) {
        return projectService.getProject(owner, repo);
    }

    // Comment endpoints
    @GetMapping("/issues/{issueId}/comments")
    public List<Comment> getComments(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo,
            @PathVariable("issueId") String issueId,
            @RequestParam(name = "maxPages", defaultValue = "2") int maxPages) {

        try {
            return commentService.getComments(owner, repo, issueId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PostMapping("/issues/{issueId}/comments")
    public List<Comment> postComments(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo,
            @PathVariable("issueId") String issueId,
            @RequestParam(name = "maxPages", defaultValue = "2") int maxPages) {

        return commentService.getComments(owner, repo, issueId);
    }

    // Commit endpoints
    @GetMapping("/commits")
    public List<Commit> getCommits(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo,
            @RequestParam(name = "sinceDays", defaultValue = "2") int sinceDays,
            @RequestParam(name = "maxPages", defaultValue = "2") int maxPages) {

        return commitService.getCommits(owner, repo, sinceDays, maxPages);
    }

    @PostMapping("/commits")
    public List<Commit> postCommits(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo,
            @RequestParam(name = "sinceDays", defaultValue = "2") int sinceDays,
            @RequestParam(name = "maxPages", defaultValue = "2") int maxPages) {

        return commitService.getCommits(owner, repo, sinceDays, maxPages);
    }

    // Issue endpoints
    @GetMapping("/issues")
    public List<Issue> getIssues(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo,
            @RequestParam(value = "sinceIssues", defaultValue = "20") int sinceIssues,
            @RequestParam(value = "maxPages", defaultValue = "2") int maxPages) {

        return issueService.getIssues(owner, repo, sinceIssues, maxPages);
    }

    @PostMapping("/issues")
    public List<Issue> postIssues(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo,
            @RequestParam(value = "sinceIssues", defaultValue = "20") int sinceIssues,
            @RequestParam(value = "maxPages", defaultValue = "2") int maxPages) {

        return issueService.getIssues(owner, repo, sinceIssues, maxPages);
    }
}
