package com.example.springdemo.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HelloModel {

    public HelloModel(String s) {
        if(s.equals("empty")) {
            this.id = -1L;
            this.password = new String("empty");
            this.username = new String("empty");
            this.name = new String("empty");
            this.email = new String("empty");
        }
    }
    public static boolean Check(HelloModel hm) {
        if(hm.getId()==-1 && 
        hm.getPassword().equals("empty") && 
        hm.getUsername().equals("empty") && 
        hm.getEmail().equals("empty") && 
        hm.getName().equals("empty")) {
            return true;
        }
        return false;
    }
    
    static public HelloModel copy(HelloModel hm) {
        HelloModel chm = new HelloModel();
        chm.setId(hm.getId());
        if(hm.getId() != null) {
            chm.setId(hm.getId());
        }
        if(hm.getPassword() != null) {
            chm.setPassword(hm.getPassword());
        }
        if(hm.getUsername() != null) {
            chm.setUsername(hm.getUsername());
        }
        if(hm.getName() != null) {
            chm.setName(hm.getName());
        }
        if(hm.getRole() != null) {
            chm.setRole(hm.getRole());
        }
        if(hm.getEmail() != null) {
            chm.setEmail(hm.getEmail());
        }
        if(hm.getCreatetimestamp() != null) {
            chm.setCreatetimestamp(hm.getCreatetimestamp());
        }
        return chm;
    }
    
    // 식별값
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저 비밀번호 
    @Column(nullable = false, length = 100)
    private String password;

    // 유저 이름(로그인 아이디) 
    @Column(nullable = false, unique = true, length = 20)
    private String username;

    // 유저 본명 
    @Column(nullable = false, length = 20)
    private String name;

    // 유저 이메일 
    @Column(nullable = false, length = 20)
    private String email;

    // 유저 등급 
    @Enumerated(EnumType.STRING)
    private UserRole role;

    // 유저 회원가입 날짜
    @CreationTimestamp
    private Timestamp createtimestamp;
}
