package com.github.github.client;

import com.github.github.dto.GitHubStatsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
@RequiredArgsConstructor
public class GitHubApiClient {

    private final RestTemplate githubRestTemplate;

    public GitHubStatsDto fetchGitHubStats(String username) {
        List<Map<String, Object>> repos = fetchRepositories(username);
        int repoCount = repos.size();
        int totalCommits = 0;
        Map<String, Integer> languageTotals = new HashMap<>();

        for (Map<String, Object> repo : repos) {
            String repoName = (String) repo.get("name");
            totalCommits += fetchUserCommitCount(username, repoName);
            mergeLanguageStats(username, repoName, languageTotals);
        }

        return new GitHubStatsDto(repoCount, totalCommits, languageTotals);
    }

    private List<Map<String, Object>> fetchRepositories(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";
        ResponseEntity<List<Map<String, Object>>> response = githubRestTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return Optional.ofNullable(response.getBody()).orElse(Collections.emptyList());
    }

    private int fetchUserCommitCount(String username, String repoName) {
        String url = "https://api.github.com/repos/" + username + "/" + repoName + "/contributors";
        ResponseEntity<List<Map<String, Object>>> response = githubRestTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        List<Map<String, Object>> contributors = response.getBody();
        if (contributors == null) return 0;

        for (Map<String, Object> contributor : contributors) {
            if (username.equalsIgnoreCase((String) contributor.get("login"))) {
                return (Integer) contributor.get("contributions");
            }
        }
        return 0;
    }

    private void mergeLanguageStats(String username, String repoName, Map<String, Integer> totalStats) {
        String url = "https://api.github.com/repos/" + username + "/" + repoName + "/languages";
        Map<String, Integer> langStats = githubRestTemplate.getForObject(url, Map.class);

        if (langStats != null) {
            for (Map.Entry<String, Integer> entry : langStats.entrySet()) {
                totalStats.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }
    }
}
