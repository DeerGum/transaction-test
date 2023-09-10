package com.deergum.transactiontest.controller;

import com.deergum.transactiontest.domain.User;
import com.deergum.transactiontest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 모든 사용자 조회
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    // 특정 사용자 조회
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        return user.orElse(null);
    }

    // 새로운 사용자 추가
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // 사용자 정보 수정
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.saveUser(user);
    }

    // 사용자 삭제
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // 트랜잭션 테스트
    @PostMapping("/transactional")
    public String transactionalTest() {
        try {
            userService.testTransactional();
            return "Transaction successful";
        } catch (Exception e) {
            return "Transaction failed: " + e.getMessage();
        }
    }
}
