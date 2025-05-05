package aiss.GitHubMiner.Service;

import aiss.GitHubMiner.model.User;
import aiss.GitHubMiner.model.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Value("${token}")
    private String token;

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUri = "https://api.github.com/users/";

    public List<User> getUsers(String[] usernames, int maxPages) {

        List<User> result = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        for (String username : usernames) {
            for (int i = 1; i <= maxPages; i++) {
                String uri = baseUri + username + "/repos?page=" + i + "&per_page=30";

                HttpEntity<String> request = new HttpEntity<>(null, headers);
                ResponseEntity<UserDto[]> response = restTemplate.exchange(
                        uri,
                        HttpMethod.GET,
                        request,
                        UserDto[].class
                );

                UserDto[] userDtos = response.getBody();

                if (userDtos == null || userDtos.length == 0) {
                    break; // No hay más repositorios para este usuario
                }

                for (UserDto dto : userDtos) {
                    // Construir el modelo User
                    User user = new User(
                            dto.getId(),
                            dto.getUsername(),
                            dto.getName(),
                            dto.getAvatarUrl(),
                            dto.getWebUrl()
                    );
                    result.add(user);
                }
            }
        }

        return result;
    }
}
