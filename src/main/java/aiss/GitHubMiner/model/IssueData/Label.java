package aiss.GitHubMiner.model.IssueData;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Label {

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
