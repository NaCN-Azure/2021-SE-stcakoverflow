package com.example.user;

import com.example.user.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;


@SpringBootTest
class ControllerTests {

    @Autowired
    private AccountController accountController;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void loginTest() {
        User user = new User();
        user.setEmail("yyf@qq.com");
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        user.setUserName("yyf");
        user.setPhoneNumber("12345648900");

        accountMapper.createNewAccount(user);

        UserForm userForm = new UserForm();
        userForm.setEmail("yyf@qq.com");
        userForm.setPassword("123456");
        ResponseVO responseVO = accountController.login(userForm);
        assertTrue(responseVO.getSuccess());

        User user1 = (User) responseVO.getContent();
        assertEquals("yyf@qq.com", user1.getEmail());
        assertEquals("12345648900", user1.getPhoneNumber());
        assertEquals("yyf", user1.getUserName());

        accountMapper.deleteAccountById(user1.getId());
    }

    @Test
    void registerAccountTest() {
        UserVO user = new UserVO();
        user.setEmail("yyf@qq.com");
        user.setPassword("123456");
        user.setUserName("yyf");
        user.setPhoneNumber("12345648900");

        ResponseVO responseVO = accountController.registerAccount(user);
        assertTrue(responseVO.getSuccess());

        User user1 = accountMapper.getAccountByEmail("yyf@qq.com");

        assertEquals("yyf@qq.com", user1.getEmail());
        assertTrue(bCryptPasswordEncoder.matches("123456", user1.getPassword()));
        assertEquals("yyf", user1.getUserName());
        assertEquals("12345648900", user1.getPhoneNumber());

        accountMapper.deleteAccountById(user1.getId());

    }

    @Test
    void getUserInfoTest() {
        User user = new User();
        user.setEmail("yyf@qq.com");
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        user.setUserName("yyf");
        user.setPhoneNumber("12345648900");

        accountMapper.createNewAccount(user);

        User user1 = accountMapper.getAccountByEmail("yyf@qq.com");

        ResponseVO responseVO = accountController.getUserInfo(user1.getId());
        assertTrue(responseVO.getSuccess());

        User user2 = (User) responseVO.getContent();
        assertEquals("yyf@qq.com", user2.getEmail());
        assertTrue(bCryptPasswordEncoder.matches("123456", user2.getPassword()));
        assertEquals("yyf", user2.getUserName());
        assertEquals("12345648900", user2.getPhoneNumber());

        accountMapper.deleteAccountById(user1.getId());
    }

    @Test
    void updateInfoTest() {
        User user = new User();
        user.setEmail("yyf@qq.com");
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        user.setUserName("yyf");
        user.setPhoneNumber("12345648900");

        accountMapper.createNewAccount(user);

        User user1 = accountMapper.getAccountByEmail("yyf@qq.com");

        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setId(user1.getId());
        userInfoVO.setEmail("YYF@qq.com");
        userInfoVO.setOld_password("123456");
        userInfoVO.setNew_password("111111");
        userInfoVO.setUserName("YYF");
        userInfoVO.setPhoneNumber("12304567890");

        ResponseVO responseVO = accountController.updateInfo(userInfoVO);
        assertTrue(responseVO.getSuccess());

        User user2 = accountMapper.getAccountByEmail("YYF@qq.com");

        assertEquals("YYF@qq.com", user2.getEmail());
        assertTrue(bCryptPasswordEncoder.matches("111111", user2.getPassword()));
        assertEquals("YYF", user2.getUserName());
        assertEquals("12304567890", user2.getPhoneNumber());

        accountMapper.deleteAccountById(user1.getId());

    }

}
