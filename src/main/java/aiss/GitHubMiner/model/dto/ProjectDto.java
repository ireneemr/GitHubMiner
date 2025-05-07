package aiss.GitHubMiner.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectDto {

    private Integer id;
    private String name;


    @JsonProperty("html_url")
    private String htmlUrl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

}
