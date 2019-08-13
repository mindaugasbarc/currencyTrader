package com.learning.spring.currency;

import com.learning.spring.currencies.request.SendMoneyRequest;
import com.learning.spring.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SendMoneyTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    private static final String URI = "http://localhost:8080";
    private String mindeToken;
    private String jonasToken;

    @Before
    public void setup() {
        mindeToken = getToken("minde");
        jonasToken = getToken("jonas");
    }

    @Test
    public void sendMoneyRequestHappensAndUser1HasLessMoneyAndUser2GetsTheMoney() {
        sendMoney(mindeToken, new SendMoneyRequest("jonas", "USD", 15d));
        assertEquals(5d, getBalance(mindeToken).get("USD"));
        assertEquals(115d, getBalance(jonasToken).get("USD"));
    }

    private void sendMoney(String token, SendMoneyRequest sendMoneyRequest) {
        HttpEntity<SendMoneyRequest> request = new HttpEntity<>(sendMoneyRequest,
                getHttpHeadersWithAuth(token));
        restTemplate.postForEntity(URI + "money/send", request, String.class);

    }

    private String getToken(String jonas) {
        return restTemplate
                .postForEntity(URI + "/user/login",
                        userRepository.findByUserDetails_Username(jonas).get().getUserDetails(),
                        String.class).getBody();
    }

    private HttpHeaders getHttpHeadersWithAuth(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
        return httpHeaders;
    }

    private Map<String, Double> getBalance(String token) {
        return restTemplate
                .exchange(URI + "user/balance", HttpMethod.GET,
                        new HttpEntity<Map>(getHttpHeadersWithAuth(token)), Map.class).getBody();
    }
}
