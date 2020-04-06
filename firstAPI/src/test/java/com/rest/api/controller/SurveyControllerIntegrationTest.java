package com.rest.api.controller;

import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.api.Application;
import com.rest.api.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIntegrationTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = createHeaders("user1", "secret1");

	HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

	@Before
	public void setupJSONAcceptType() {
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testRetrieveSurveyQuestion() throws Exception {

		String expected = "{\"id\":\"Question1\",\"description\":\"Largest Country in the World\",\"correctAnswer\":\"Russia\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]}";

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/surveys/Survey1/questions/Question1"), HttpMethod.GET,
				new HttpEntity<String>("DUMMY_DOESNT_MATTER", headers), String.class);

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void retrieveAllSurveyQuestions() throws Exception {

		ResponseEntity<List<Question>> response = restTemplate.exchange(createURLWithPort("/surveys/Survey1/questions"),
				HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESNT_MATTER", headers),
				new ParameterizedTypeReference<List<Question>>() {
				});

		Question sampleQuestion = new Question("Question1", "Largest Country in the World", "Russia",
				Arrays.asList("India", "Russia", "United States", "China"));

		assertTrue(response.getBody().contains(sampleQuestion));
	}

	@Test
	public void addQuestion() {

		Question question = new Question("DOESNTMATTER", "Question1", "Russia",
				Arrays.asList("India", "Russia", "United States", "China"));

		HttpEntity entity = new HttpEntity<Question>(question, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/surveys/Survey1/questions"),
				HttpMethod.POST, entity, String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(actual.contains("/surveys/Survey1/questions/"));

	}

	private String createURLWithPort(final String uri) {
		return "http://localhost:" + port + uri;
	}

}
