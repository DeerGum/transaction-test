package com.deergum.transactiontest.repository;

import com.deergum.transactiontest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
