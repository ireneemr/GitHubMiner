package aiss.GitHubMiner.controller;

import aiss.GitHubMiner.model.Comment;
import aiss.GitHubMiner.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/repos/{owner}/{repo}/{issueId}/comments")
    public List<Comment> getComments(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo,
            @PathVariable("issueId") String issueId,
            @RequestParam(name = "maxPages", defaultValue = "2") int maxPages){
        return commentService.getComments(owner, repo, issueId);
    }

    @PostMapping("/repos/{owner}/{repo}/{issueId}/comments")
    public List<Comment> postComments(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo,
            @PathVariable("issueId") String issueId,
            @RequestParam(name = "maxPages", defaultValue = "2") int maxPages){
        return commentService.getComments(owner, repo, issueId);
    }


}
