package com.learning.spring.currencies;

import com.learning.spring.currencies.application.request.SendMoneyRequest;
import com.learning.spring.user.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

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
    }

    @Test
    public void sendMoneyAndEnsureTransactionHappenedBetweenUsers() {
        sendMoney(mindeToken, new SendMoneyRequest("jonas", "USD", 15d));
        assertEquals(5d, getBalance(mindeToken).get("USD"));
        assertEquals(115d, getBalance(jonasToken).get("USD"));
    }

    private Map getBalance(String token) {
        return restTemplate
                .exchange(URI + "/user/balance", HttpMethod.GET, new HttpEntity<>(getHttpHeadersWithAuth(token)),
                        Map.class).getBody();
    }

    private void sendMoney(String token, SendMoneyRequest sendMoneyRequest) {
        HttpEntity<SendMoneyRequest> request =
                new HttpEntity<>(sendMoneyRequest, getHttpHeadersWithAuth(token));
        restTemplate.postForEntity(URI + "/money/send", request, String.class);
    }

    private HttpHeaders getHttpHeadersWithAuth(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
        return httpHeaders;
    }
}
