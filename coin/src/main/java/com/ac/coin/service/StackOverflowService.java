package com.ac.coin.service;

import com.ac.coin.po.Question;
import com.ac.coin.po.Questions;
import com.ac.coin.po.tagTrend;
import com.ac.coin.po.Tags;
import com.ac.coin.vo.NodeVO;
import com.ac.coin.vo.RelationVO;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface StackOverflowService {
    List<NodeVO> findDefaultStackNode(int year);
    List<RelationVO> findDefaultStackRelation(int year);
    Tags findTargetTag(String name);
    List<NodeVO> findTargetSubTag(String name);
    List<RelationVO> findTargetSubRelation(String name);
    tagTrend findTargetNodesChart(String name);
    List<Question> findHotQuestions(String name);
    List<String> findNodesFuzzy(String name);
}
