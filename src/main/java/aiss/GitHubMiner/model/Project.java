package aiss.GitHubMiner.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Project {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("web_url")
    private String web_url;

    @JsonProperty("commits")
    private List<Commit> commits;

    //Constructor
    public Project(String id, String name, String web_url) {
        this.id = id;
        this.name = name;
        this.web_url = web_url;
        this.commits = commits;
    }
    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    //ToString


    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", web_url='" + web_url + '\'' +
                ", commits=" + commits +
                '}';
    }
}
