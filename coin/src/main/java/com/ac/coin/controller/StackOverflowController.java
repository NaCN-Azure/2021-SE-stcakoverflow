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

    @GetMapping("/findTargetNodesInfo/{name}")
    public ResponseVO findTargetNodesInfo(@PathVariable("name") String name){
        return ResponseVO.buildSuccess(stackOverflowService.findTargetSubTag(name));//本方法返回单独tag(给图谱展示用的)及其子节点
    }

    @GetMapping("/findTargetNodesDescription/{name}")
    public ResponseVO findTargetNodesDescription(@PathVariable("name") String name){
        return ResponseVO.buildSuccess(stackOverflowService.findTargetTag(name));//本方法返回单独tag介绍（返回一个完整的tag类型，前端可能需要新定义方法）
    }

    @GetMapping("/findTargetNodesRelated/{name}")
    public ResponseVO findTargetNodesRelated(@PathVariable("name") String name){
        return ResponseVO.buildSuccess(stackOverflowService.findTargetSubRelation(name));//本方法返回单独tag及其附属关系(给图谱展示用的)
    }

    @GetMapping("/findTargetQuestions/{name}")
    public ResponseVO findTargetQuestions(@PathVariable("name") String name){
        return ResponseVO.buildSuccess();//todo 本方法返回tag的最佳回答
    }

    @GetMapping("/findTargetNodesChart/{name}")
    public ResponseVO findTargetNodesChart(@PathVariable("name") String name){
        return ResponseVO.buildSuccess(stackOverflowService.findTargetNodesChart(name)); //todo
    }

}
