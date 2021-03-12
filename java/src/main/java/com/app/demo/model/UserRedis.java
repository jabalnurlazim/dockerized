package com.app.demo.model;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@RedisHash("UserRedis")
public class UserRedis {

    private Integer id;
    private String firstName;
    private String lastName;
    private String nickName;
    private int age;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }
}
