package com.ac.coin.service;

import com.ac.coin.po.Node;
import com.ac.coin.po.Tags;
import com.ac.coin.vo.NodeVO;
import com.ac.coin.vo.RelationVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StackOverflowServiceTest {
    @Autowired
    NodeService nodeService;
    @Autowired
    RelationService relationService;
    @Autowired
    GraphService graphService;
    @Autowired
    UserService userService;
    @Autowired
    StackOverflowService stackOverflowService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findDefaultStackNode() {
        List<NodeVO> nodeVOList=stackOverflowService.findDefaultStackNode(2018);
        for(NodeVO node:nodeVOList)
            System.out.println(node.getName());
    }

    @Test
    void findDefaultStackRelation() {
        List<RelationVO> relationVOList=stackOverflowService.findDefaultStackRelation(2018);
        for(RelationVO relationVO:relationVOList)
            System.out.println("("+relationVO.getSource()+")"+" -> "+"("+relationVO.getTarget()+")"+","+relationVO.getName());
    }

    @Test
    void findTargetTag() {
        Tags tags=stackOverflowService.findTargetTag("java");
        System.out.println(tags.getName()+":"+"\n"+"total:"+tags.getCount()+"\n"+"count2015:"+tags.getCount_2015()
                +"\n"+"count2016:"+tags.getCount_2016()+"\n"+"count2017:"+tags.getCount_2017()+"\n"+"count2018:"+tags.getCount_2018()
                +"\n"+"count2019:"+tags.getCount_2019()+"\n"+"count2020:"+tags.getCount_2020());
    }

    @Test
    void findTargetSubTag() {
        List<NodeVO> nodeVOList=stackOverflowService.findTargetSubTag("java");
        for (NodeVO nodeVO:nodeVOList)
            System.out.println(nodeVO.getName());
    }

    @Test
    void findTargetSubRelation() {
        List<RelationVO> relationVOList=stackOverflowService.findTargetSubRelation("java");
        for(RelationVO relationVO:relationVOList)
            System.out.println("("+relationVO.getSource()+")"+" -> "+"("+relationVO.getTarget()+")"+","+relationVO.getName());
    }
}