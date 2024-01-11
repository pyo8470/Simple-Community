package com.example.seb45pre011.member;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    @Query(value = "SELECT * FROM Member WHERE email = :email", nativeQuery = true)
    Optional<Member> findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM Member WHERE id = :id", nativeQuery = true)
    Optional<Member> findById(@Param("id") String id);

    @Query("SELECT m FROM Member m WHERE m.email = :email AND m.username = :username")
    Optional<Member> findByEmailAndUsername(@Param("email") String email, @Param("username") String username);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.password = :password WHERE m.email = :email")
    Member updatePasswordByEmail(@Param("email") String email, @Param("password") String password);


}