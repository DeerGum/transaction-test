package com.deergum.transactiontest.service;

import com.deergum.transactiontest.domain.User;
import com.deergum.transactiontest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findUserById(String id) {
        return userRepository.findUserById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String testTransactional() {
        User user1 = new User();
        user1.setId(UUID.randomUUID().toString());
        user1.setName("Alice");
        userRepository.save(user1);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("http://127.0.0.1:3000/data/check/" + user1.getId(), String.class);

        log.info("Call Result : {}", result);
        return result;
    }

    public String testTransactional2() {
        User user1 = new User();
        user1.setId(UUID.randomUUID().toString());
        user1.setName("Alice");
        saveUser(user1);

        String result = callApi(user1.getId());

        log.info("Call Result : {}", result);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(User user) {
        userRepository.save(user);
    }

    private String callApi(String uid) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("http://127.0.0.1:3000/data/check/" + uid, String.class);

        return result;
    }
}