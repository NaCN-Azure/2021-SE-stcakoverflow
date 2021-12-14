package com.ac.coin.controller;

import com.ac.coin.service.StackOverflowService;
import com.ac.coin.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/stackoverflow")
@CrossOrigin
public class StackOverflowController {
    @Autowired
    StackOverflowService stackOverflowService;

    @GetMapping("/findStackNodes")
    public ResponseVO findStackNode(){
        return ResponseVO.buildSuccess(stackOverflowService.findDefaultStackNode(0));//默认年份的初始返回值
    }

    @GetMapping("/findStackRelations")
    public ResponseVO findStackRelation(){
        return ResponseVO.buildSuccess(stackOverflowService.findDefaultStackRelation(0));
    }

    @GetMapping("/findStackNodesYear/{year}")
    public ResponseVO findStackNodesYears(@PathVariable("year") int year){
        return ResponseVO.buildSuccess(stackOverflowService.findDefaultStackNode(year));//限定2015-2021的年份
    }

    @GetMapping("/findStackRelationsYear/{year}")
    public ResponseVO findStackRelationsYears(@PathVariable("year") int year){
        return ResponseVO.buildSuccess(stackOverflowService.findDefaultStackRelation(year));
    }

    @GetMapping("/findTargetNodesInfo")
    @ResponseStatus(value= HttpStatus.OK)
    public ResponseVO findTargetNodesInfo(@RequestParam("name") String name){
        return ResponseVO.buildSuccess(stackOverflowService.findTargetSubTag(name));//本方法返回单独tag(给图谱展示用的)及其子节点
    }

    @GetMapping("/findTargetNodesDescription")
    @ResponseStatus(value= HttpStatus.OK)
    public ResponseVO findTargetNodesDescription(@RequestParam("name") String name){
        return ResponseVO.buildSuccess(stackOverflowService.findTargetTag(name));//本方法返回单独tag介绍（返回一个完整的tag类型，前端可能需要新定义方法）
    }

    @GetMapping("/findTargetNodesRelated")
    @ResponseStatus(value= HttpStatus.OK)
    public ResponseVO findTargetNodesRelated(@RequestParam("name") String name){
        return ResponseVO.buildSuccess(stackOverflowService.findTargetSubRelation(name));//本方法返回单独tag及其附属关系(给图谱展示用的)
    }

    @GetMapping("/findTargetQuestions")
    @ResponseStatus(value= HttpStatus.OK)
    public ResponseVO findTargetQuestions(@RequestParam("name") String name) {
        return ResponseVO.buildSuccess(stackOverflowService.findHotQuestions(name));//返回最热五个问题
    }

    @GetMapping("/findTargetNodesChart")
    @ResponseStatus(value= HttpStatus.OK)
    public ResponseVO findTargetNodesChart(@RequestParam("name") String name){
        return ResponseVO.buildSuccess(stackOverflowService.findTargetNodesChart(name)); //输入一个tagname，返回他的折线图信息
    }

    @GetMapping("/findNodesFuzzy")
    @ResponseStatus(value= HttpStatus.OK)
    public ResponseVO findNodesFuzzy(@RequestParam("name") String name){
        return ResponseVO.buildSuccess(stackOverflowService.findNodesFuzzy(name));
    }

}
