package aiss.GitHubMiner.model.dto;

import aiss.GitHubMiner.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class IssueDto {

    @JsonProperty("number")
    private String number;

    private String title;

    @JsonProperty("body")
    private String description;

    private String state;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("closed_at")
    private String closedAt;

    private List<Label> labels;

    private Reactions reactions;

    @JsonProperty("user")
    private UserDto author;

    @JsonProperty("assignee")
    private UserDto assignee;


    // GETTERS adaptados

    public String getId() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public List<String> getLabels() {
        if (labels == null) return null;
        return labels.stream().map(Label::getName).collect(Collectors.toList());
    }

    public int getVotes() {
        if (reactions == null) return 0;
        return reactions.getPlusOne();
    }

    public UserDto getAuthor() {
        return author;
    }

    public UserDto getAssignee() {
        return assignee;
    }



    // Subclases para mapear correctamente los objetos anidados

    public static class Label {
        private String name;
        public String getName() { return name; }
    }

    public static class Reactions {
        @JsonProperty("+1")
        private int plusOne;
        public int getPlusOne() { return plusOne; }
    }


}
