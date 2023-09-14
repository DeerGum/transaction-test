package com.deergum.transactiontest.controller;

import com.deergum.transactiontest.domain.User;
import com.deergum.transactiontest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // id로 사용자 조회
    @GetMapping("/{id}")
    public String getUserById(@PathVariable String id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isEmpty())
            return "user not found";
        else
            return "user id : " + user.get().getId();
    }

    // 트랜잭션 테스트
    @PostMapping("/transactional")
    public String transactionalTest() {
        try {
            return userService.testTransactional();
        } catch (Exception e) {
            return "Transaction failed: " + e.getMessage();
        }
    }

    @PostMapping("/transactional2")
    public String transactionalTest2() {
        try {
            return  userService.testTransactional2();
        } catch (Exception e) {
            return "Transaction failed: " + e.getMessage();
        }
    }
}
