package aiss.GitHubMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;


public class Project {

    @Id
    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    @NotEmpty(message= "The name of the project cannot be empty")
    public String name;

    @JsonProperty("web_url")
    @NotEmpty(message="The web url of the project cannot be empty")
    private String webUrl;

    @JsonProperty("html_url")
    @JsonIgnore
    @NotEmpty(message= "The web url of the project cannot be empty")
    private String htmlUrl;

    @JsonProperty("commits")
    private List<Commit> commits;

    @JsonProperty("issues")
    private List<Issue> issues;

    public Project() {
        commits = new ArrayList<>();
        issues = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
