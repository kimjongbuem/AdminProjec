package com.study.study.repository;

import com.study.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select* from user user where user.deleted = true", nativeQuery = true)
    List<User> findUserDeleted();
}
