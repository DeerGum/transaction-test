package com.deergum.transactiontest.service;

import com.deergum.transactiontest.domain.User;
import com.deergum.transactiontest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void testTransactional() {
        User user1 = new User();
        user1.setName("Alice");
        userRepository.save(user1);

        // 의도적으로 예외를 발생시켜 트랜잭션을 테스트합니다.
        if (true) {
            throw new RuntimeException("Test exception");
        }

        User user2 = new User();
        user2.setName("Bob");
        userRepository.save(user2);
    }

}