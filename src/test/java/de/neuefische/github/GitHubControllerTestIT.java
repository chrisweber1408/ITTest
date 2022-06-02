package de.neuefische.github;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


//hier wird die Webaplikation gestartet und es wird ein zufälliger
//port belegt
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class GitHubControllerTestIT {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private GitHubService gitHubService;

    //das restTemplate ruft die methode oder http auf

    /*
    @Test
    void shouldGetReposFromUser(){
        //given
        String name = "chrisweber1408";
        List<String> repos = List.of(
                "FirstSpringBootProject",
                "FreitagsaufgabeWeek2",
                "Github-Aufgabe1",
                "Test",
                "Week1",
                "Week2_Uebungen");
        //when
        ResponseEntity<String[]> actual = restTemplate.getForEntity("/github/" + name, String[].class);
        //then
        Assertions.assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(actual.getBody()).containsAll(repos);
    }

     */

    @Test
    void shouldGetReposFromUser(){
        //given
        String name = "chrisweber1408";
        List<String> repos = List.of(
                "FirstSpringBootProject",
                "FreitagsaufgabeWeek2",
                "Github-Aufgabe1",
                "Test",
                "Week1",
                "Week2_Uebungen");

        when(gitHubService.findReposByName(name)).thenReturn(repos);

        //when
        ResponseEntity<String[]> actual = restTemplate.getForEntity("/github/" + name, String[].class);
        //then
        Assertions.assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(actual.getBody()).containsAll(repos);
    }
}