package aiss.GitHubMiner.model.CommitData;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CommitProperty {

    @JsonProperty("author")
    private Author author;

    @JsonProperty("committer")
    private Commiter committer;

    @JsonProperty("message")
    private String message;

    @JsonProperty("url")
    private String url;

    @JsonProperty("comment_count")
    private Integer commentCount;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Commiter getCommitter() {
        return committer;
    }

    public void setCommitter(Commiter committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
