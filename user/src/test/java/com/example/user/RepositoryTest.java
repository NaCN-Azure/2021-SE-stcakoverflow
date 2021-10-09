package com.example.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;


@SpringBootTest
public class RepositoryTest {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AccountMapper accountMapper;


    @Test
    void createNewAccountTest() {
        User user = new User();
        user.setEmail("yyf@qq.com");
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        user.setUserName("yyf");
        user.setPhoneNumber("12345648900");

        accountMapper.createNewAccount(user);

        User user1 = accountMapper.getAccountByEmail("yyf@qq.com");

        assertEquals("yyf@qq.com", user1.getEmail());
        assertTrue(bCryptPasswordEncoder.matches("123456", user1.getPassword()));
        assertEquals("yyf", user1.getUserName());
        assertEquals("12345648900", user1.getPhoneNumber());

        accountMapper.deleteAccountById(user1.getId());

    }

    @Test
    void deleteAccountByEmailTest() {
        User user = new User();
        user.setEmail("yyf@qq.com");
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        accountMapper.createNewAccount(user);

        User user1 = accountMapper.getAccountByEmail("yyf@qq.com");
        assertEquals("yyf@qq.com", user1.getEmail());
        assertTrue(bCryptPasswordEncoder.matches("123456", user1.getPassword()));

        accountMapper.deleteAccountByEmail("yyf@qq.com");

        User user2 = accountMapper.getAccountByEmail("yyf@qq.com");
        assertTrue(user2 == null);

    }

    @Test
    void updateAccountTest() {

        User user = new User();
        user.setEmail("yyf@qq.com");
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        user.setUserName("yyf");
        user.setPhoneNumber("12345648900");

        accountMapper.createNewAccount(user);

        User user1 = accountMapper.getAccountByEmail("yyf@qq.com");

        assertEquals("yyf@qq.com", user1.getEmail());
        assertTrue(bCryptPasswordEncoder.matches("123456", user1.getPassword()));
        assertEquals("yyf", user1.getUserName());
        assertEquals("12345648900", user1.getPhoneNumber());

        accountMapper.updateAccount(user1.getId(), bCryptPasswordEncoder.encode("111111"), "YYF", "12304567890", user1.getEmail());

        User user2 = accountMapper.getAccountByEmail("yyf@qq.com");

        assertEquals("yyf@qq.com", user2.getEmail());
        assertTrue(bCryptPasswordEncoder.matches("111111", user2.getPassword()));
        assertEquals("YYF", user2.getUserName());
        assertEquals("12304567890", user2.getPhoneNumber());


        accountMapper.deleteAccountById(user2.getId());
    }
}
