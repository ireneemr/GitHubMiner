package aiss.GitHubMiner.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Issue {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("state")
    private String state;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("closed_at")
    private String closedAt;

    @JsonProperty("labels")
    private List<String> labels;

    @JsonProperty("votes")
    private Integer votes;

    @JsonProperty("Comments")
    private List<Comment> comments;

}
