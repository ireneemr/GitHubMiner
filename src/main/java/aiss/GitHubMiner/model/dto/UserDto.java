package aiss.GitHubMiner.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

    @JsonProperty("id")
    private String id;  // ID único del usuario

    @JsonProperty("login")
    private String username;  // Nombre de usuario en GitHub

    @JsonProperty("name")
    private String name;  // Nombre completo del usuario

    @JsonProperty("avatar_url")
    private String avatarUrl;  // URL de la imagen de avatar del usuario

    @JsonProperty("url")
    private String webUrl;  // URL al perfil del usuario en GitHub


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

}
