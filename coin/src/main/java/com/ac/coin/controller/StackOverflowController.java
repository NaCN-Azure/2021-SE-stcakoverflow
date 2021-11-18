package com.ac.coin.controller;

import com.ac.coin.dao.repository.StackNodeRepository;
import com.ac.coin.po.Replies;
import com.ac.coin.po.StackNode;
import com.ac.coin.po.Tags;
import com.ac.coin.service.StackOverflowService;
import com.ac.coin.vo.ResponseVO;
import com.ac.coin.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stackoverflow")
@CrossOrigin
public class StackOverflowController {
    @Autowired
    StackOverflowService stackOverflowService;

    @GetMapping("/findStackNodes")
    public ResponseVO findStackNode(){
        return ResponseVO.buildSuccess(stackOverflowService.findDefaultStackNode());
    }

    @GetMapping("/findStackRelations")
    public ResponseVO findStackRelation(){
        return ResponseVO.buildSuccess(stackOverflowService.findDefaultStackRelation());
    }

}
