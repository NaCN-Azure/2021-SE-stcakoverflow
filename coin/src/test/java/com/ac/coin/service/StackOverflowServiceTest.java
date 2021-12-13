package com.ac.coin.service;

import com.ac.coin.po.Node;
import com.ac.coin.po.Questions;
import com.ac.coin.po.Tags;
import com.ac.coin.po.tagTrend;
import com.ac.coin.vo.NodeVO;
import com.ac.coin.vo.RelationVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//unit test finished
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
        List<NodeVO> nodeVOList;
        for(int year=2015;year<2022;year++){
            nodeVOList=stackOverflowService.findDefaultStackNode(year);
//            System.out.println(year+":");
//            for(NodeVO node:nodeVOList)
//                System.out.println(node.getName());
            assertNotEquals(0,nodeVOList.size());
        }

    }

    @Test
    void findDefaultStackRelation() {
        List<RelationVO> relationVOList;
        for (int year=2015;year<2022;year++){
            relationVOList=stackOverflowService.findDefaultStackRelation(year);
            assertNotEquals(0,relationVOList.size());
//            System.out.println(year+":");
//            for(RelationVO relationVO:relationVOList)
//                System.out.println("("+relationVO.getSource()+")"+" -> "+"("+relationVO.getTarget()+")"+","+relationVO.getName());
            for(RelationVO relationVO:relationVOList){
                assertNotEquals(-1,relationVO.getId());
                assertNotEquals(-1,relationVO.getSource());
                assertNotEquals(-1,relationVO.getTarget());
                assertEquals("contains",relationVO.getName());
            }
        }

    }

    @Test
    void findTargetTag() {
        List<String> nameList=new ArrayList<>();
        nameList.add("java");
        nameList.add("python");
        nameList.add("c++");
        Tags tags;

        for(int i=0;i<nameList.size();i++){
            tags=stackOverflowService.findTargetTag(nameList.get(i));
//            System.out.println(tags.getName()+":"+"\n"+"total:"+tags.getCount()+"\n"+"count2015:"+tags.getCount_2015()
//                    +"\n"+"count2016:"+tags.getCount_2016()+"\n"+"count2017:"+tags.getCount_2017()+"\n"+"count2018:"+tags.getCount_2018()
//                    +"\n"+"count2019:"+tags.getCount_2019()+"\n"+"count2020:"+tags.getCount_2020()+"\n"+"count2021:"+tags.getCount_2021());
            assertEquals(nameList.get(i),tags.getName());
            assertNotEquals(0,tags.getCount());
            assertNotEquals(0,tags.getCount_2015());
            assertNotEquals(0,tags.getCount_2016());
            assertNotEquals(0,tags.getCount_2017());
            assertNotEquals(0,tags.getCount_2018());
            assertNotEquals(0,tags.getCount_2019());
            assertNotEquals(0,tags.getCount_2020());
            assertNotEquals(0,tags.getCount_2021());
        }

    }

    @Test
    void findTargetSubTag() {
        List<String> nameList=new ArrayList<>();
        nameList.add("java");
        nameList.add("python");
        nameList.add("c++");
        List<NodeVO> nodeVOList;
        for(int i=0;i<nameList.size();i++){
            nodeVOList=stackOverflowService.findTargetSubTag(nameList.get(i));
//            System.out.println(nameList.get(i)+":");
            assertNotEquals(0,nodeVOList.size());
//            for (NodeVO nodeVO:nodeVOList)
//                System.out.println(nodeVO.getName());

        }


    }

    @Test
    void findTargetSubRelation() {
        List<String> nameList=new ArrayList<>();
        nameList.add("java");
        nameList.add("python");
        nameList.add("c++");
        List<RelationVO> relationVOList;
        for(int i=0;i<nameList.size();i++){
            relationVOList=stackOverflowService.findTargetSubRelation(nameList.get(i));
//            System.out.println(nameList.get(i)+":");
            for(RelationVO relationVO:relationVOList){
//                System.out.println("("+relationVO.getSource()+")"+" -> "+"("+relationVO.getTarget()+")"+","+relationVO.getName());
                assertNotEquals(-1,relationVO.getId());
                assertNotEquals(-1,relationVO.getSource());
                assertNotEquals(-1,relationVO.getTarget());
                assertEquals("contains",relationVO.getName());

            }

        }

    }

    @Test
    void findTargetNodesChart(){
        List<String> nameList=new ArrayList<>();
        nameList.add("java");
        nameList.add("python");
        nameList.add("c++");
        tagTrend tagTrend;
        for(int i=0;i<nameList.size();i++){
            tagTrend=stackOverflowService.findTargetNodesChart(nameList.get(i));
            assertEquals(12,tagTrend.getYear2015().size());
            assertEquals(12,tagTrend.getYear2016().size());
            assertEquals(12,tagTrend.getYear2017().size());
            assertEquals(12,tagTrend.getYear2018().size());
            assertEquals(12,tagTrend.getYear2019().size());
            assertEquals(12,tagTrend.getYear2020().size());
            assertEquals(12,tagTrend.getYear2021().size());
//            System.out.println(nameList.get(i)+":");
//            System.out.println(tagTrend.getYear2015().toString());
//            System.out.println(tagTrend.getYear2016().toString());
//            System.out.println(tagTrend.getYear2017().toString());
//            System.out.println(tagTrend.getYear2018().toString());
//            System.out.println(tagTrend.getYear2019().toString());
//            System.out.println(tagTrend.getYear2020().toString());
//            System.out.println(tagTrend.getYear2021().toString());
        }


    }

    @Test
    void findHotQuestions(){
        List<String> nameList=new ArrayList<>();
        nameList.add("java");
        nameList.add("python");
        nameList.add("c++");
        List<Questions> questions;
        for(int i=0;i<nameList.size();i++){
            questions=stackOverflowService.findHotQuestions(nameList.get(i));
//            System.out.println(nameList.get(i)+":");
            for(Questions question:questions){
                assertNotEquals(-1,question.getAnswers());
                assertNotEquals(-1,question.getViews());
                assertNotEquals(-1,question.getCreateDate());
                assertNotEquals("",question.getName());
//                System.out.println("AnswerId: "+question.getAnswers());
//                System.out.println("ViewId: "+question.getViews());
//                System.out.println("Name: "+question.getName());
//                System.out.println("CreateDate: "+question.getCreateDate());
            }
        }

    }

    @Test
    void findNodesFuzzy(){
        List<String> nameList=new ArrayList<>();
        nameList.add("java");
        nameList.add("python");
        nameList.add("c++");
        List<String> strings;
        for(int i=0;i<nameList.size();i++){
            strings=stackOverflowService.findNodesFuzzy(nameList.get(i));
            assertNotEquals(0,strings.size());
//            System.out.println(nameList.get(i)+":");
//            System.out.println(strings.toString());
        }

    }
}
