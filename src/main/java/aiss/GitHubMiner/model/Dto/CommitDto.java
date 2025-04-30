package aiss.GitHubMiner.model.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommitDto {

    @JsonProperty("sha")
    private String sha;  // ID único del commit

    @JsonProperty("commit")
    private CommitDetails commit;  // Información detallada del commit

    @JsonProperty("html_url")
    private String htmlUrl;  // URL al commit en GitHub

    // Getters y Setters

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public CommitDetails getCommit() {
        return commit;
    }

    public void setCommit(CommitDetails commit) {
        this.commit = commit;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    // Inner class para mapear la parte "commit" de la respuesta
    public static class CommitDetails {

        @JsonProperty("author")
        private Author author;  // Información del autor

        @JsonProperty("message")
        private String message;  // Mensaje del commit

        // Getters y Setters

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    // Inner class para mapear la parte "author" del commit
    public static class Author {

        @JsonProperty("name")
        private String name;  // Nombre del autor

        @JsonProperty("email")
        private String email;  // Correo electrónico del autor

        @JsonProperty("date")
        private String date;  // Fecha del commit

        // Getters y Setters

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
