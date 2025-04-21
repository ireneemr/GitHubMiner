package aiss.GitHubMiner.controller;

import aiss.GitHubMiner.Service.ProjectService;
import aiss.GitHubMiner.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/repos/{owner}/{repo}")
    public Project getProject(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo) {
        return projectService.getProject(owner, repo);
    }

    @PostMapping("/repos/{owner}/{repo}")
    public Project postProject(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo) {
        return projectService.getProject(owner, repo);

    }
}