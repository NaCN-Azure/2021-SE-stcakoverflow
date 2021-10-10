package com.example.user.controller;

//import com.ac.coin.service.UserService;
//import com.ac.coin.vo.ResponseVO;
//import com.ac.coin.vo.UserVO;
import com.example.user.service.UserService;
import com.example.user.vo.ResponseVO;
import com.example.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin()
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseVO register(@RequestBody UserVO userVO){
        System.out.println(userVO.getId());
        return userService.add(userVO);
    }

    @GetMapping("/delete/{userId}")
    public ResponseVO delete(@PathVariable("userId") Long userId){
        return userService.delete(userId);
    }

    @GetMapping("/deleteAllGraphs/{userId}")
    public ResponseVO deleteAllGraphs(@PathVariable("userId") Long userId){
        return userService.deleteAllGraphs(userId);
    }

    @PostMapping("/edit")
    public ResponseVO edit(@RequestBody UserVO userVO){
        return userService.edit(userVO);
    }

    @GetMapping("/find/{userId}")
    public ResponseVO find(@PathVariable("userId") Long userId){
        Optional<UserVO> optionalUserVO = userService.find(userId);
        return optionalUserVO.map(ResponseVO::buildSuccess).orElseGet(()->ResponseVO.buildFailure("不存在该用户"));
    }

    @PostMapping("/login")
    public ResponseVO login(@RequestBody UserVO userVO){
        return userService.verify(userVO.getName(),userVO.getPassword());
    }

    @GetMapping("/findGraphs/{userId}")
    public ResponseVO findGraphs(@PathVariable("userId") Long userId){
        return userService.findGraphs(userId);
    }

    @GetMapping("/listFavors/{userName}/{stockName}")
    public ResponseVO listFavors(@PathVariable("userName") String userName, @PathVariable("stockName") String stockName){
        return userService.listFavors(userName, stockName);
    }

    @GetMapping("/log")
    public String getLog(){
        String fileName="/log/so.log";
        StringBuilder sb=new StringBuilder("");
        try{
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            /* read log line by line */
            while ((strLine = br.readLine()) != null)   {
                /* parse strLine to obtain what you want */
                sb.append(strLine);
                sb.append("<br/>");
            }
            fstream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
//        StringBuilder sb=new StringBuilder();
//        sb.append("abcd");
//        sb.append("<br/>");
//        sb.append("bcde");
        return sb.toString();

    }

    @GetMapping("/testUser")
    public String TestUser(){
        return "testUser";
    }

}
