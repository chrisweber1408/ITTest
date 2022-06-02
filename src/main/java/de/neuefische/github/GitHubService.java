package de.neuefische.github;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GitHubService {


    public List<String> findReposByName(String name) {
        RestTemplate restTemplate = new RestTemplate();
        return Arrays.stream(restTemplate.getForObject("https://api.github.com/users/" + name + "/repos", GitHubRepository[].class))
                .map(GitHubRepository::getName)
                .toList();
    }
}
