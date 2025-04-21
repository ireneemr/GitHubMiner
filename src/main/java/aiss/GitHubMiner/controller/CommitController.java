package aiss.GitHubMiner.controller;

import aiss.GitHubMiner.model.Commit;
import aiss.GitHubMiner.Service.CommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CommitController {

    @Autowired
    private CommitService commitService;

    @GetMapping("/repos/{owner}/{repo}/commits")
    public List<Commit> getCommits(
            @PathVariable("owner")String owner,
            @PathVariable("repo")String repo,
            @RequestParam( name = "sinceDays", defaultValue = "2") int sinceDays,
            @RequestParam(name = "maxPages", defaultValue =  "2") int maxPages){
        return commitService.getCommits(owner, repo, sinceDays, maxPages);
    }

    @PostMapping("/repos/{owner}/{repo}/commits")
    public List<Commit> postCommits(
            @PathVariable("owner")String owner,
            @PathVariable("repo")String repo,
            @RequestParam( name = "sinceDays", defaultValue = "2") int sinceDays,
            @RequestParam(name = "maxPages", defaultValue =  "2") int maxPages){
        return commitService.getCommits(owner, repo, sinceDays, maxPages);
    }

}
