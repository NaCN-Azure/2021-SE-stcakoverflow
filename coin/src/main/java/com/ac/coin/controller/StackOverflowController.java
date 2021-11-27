package com.ac.coin.controller;

import com.ac.coin.service.StackOverflowService;
import com.ac.coin.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
