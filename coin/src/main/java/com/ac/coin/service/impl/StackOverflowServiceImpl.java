package com.ac.coin.service.impl;

import com.ac.coin.dao.*;
import com.ac.coin.enums.NodeShape;
import com.ac.coin.po.*;
import com.ac.coin.service.GraphService;
import com.ac.coin.service.StackOverflowService;
import com.ac.coin.util.StackTrans;
import com.ac.coin.util.Transform;
import com.ac.coin.vo.GraphVO;
import com.ac.coin.vo.NodeVO;
import com.ac.coin.vo.RelationVO;
import com.ac.coin.vo.ResponseVO;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StackOverflowServiceImpl implements StackOverflowService {

    static String default_question_1 = "Why is processing a sorted array faster than processing an unsorted array?";
    static String default_question_2 = "How do I undo the most recent local commits in Git?";
    static String default_question_3 = "How do I delete a Git branch locally and remotely?";

    @Autowired
    private StackOverflowDAO stackOverflowDAO;

    @Override
    public List<NodeVO> findDefaultStackNode(){
        Set<StackNode> hashset = new HashSet<>();
        hashset.addAll(stackOverflowDAO.getTagsByQuestionName(default_question_1));
        hashset.addAll(stackOverflowDAO.getTagsByQuestionName(default_question_2));
        hashset.addAll(stackOverflowDAO.getTagsByQuestionName(default_question_3));
        hashset.addAll(stackOverflowDAO.getRepliesByQuestionName(default_question_1));
        hashset.addAll(stackOverflowDAO.getRepliesByQuestionName(default_question_2));
        hashset.addAll(stackOverflowDAO.getRepliesByQuestionName(default_question_3));
        hashset.add(stackOverflowDAO.getQuestion(default_question_1).get());
        hashset.add(stackOverflowDAO.getQuestion(default_question_2).get());
        hashset.add(stackOverflowDAO.getQuestion(default_question_3).get());
        List<NodeVO> nodeVOS = new ArrayList<>();
        for(StackNode s:hashset){
            System.out.println(s.getName());
            if(StackTrans.has(nodeVOS,s.getId())) {
                continue;
            }
            nodeVOS.add(StackTrans.NodeVO(s));
        }
        return nodeVOS;
    }

    @Override
    public List<RelationVO> findDefaultStackRelation(){
        Set<Relation> hashset = new HashSet<>();
        hashset.addAll(stackOverflowDAO.getBelongsByQuestion(default_question_1));
        hashset.addAll(stackOverflowDAO.getBelongsByQuestion(default_question_2));
        hashset.addAll(stackOverflowDAO.getBelongsByQuestion(default_question_3));
        hashset.addAll(stackOverflowDAO.getAnswersByQuestion(default_question_1));
        hashset.addAll(stackOverflowDAO.getAnswersByQuestion(default_question_2));
        hashset.addAll(stackOverflowDAO.getAnswersByQuestion(default_question_3));
        List<RelationVO> relationVOS = new ArrayList<>();
        for(Relation s:hashset){
            System.out.println(s.getFromId()+" "+s.getToId());
            relationVOS.add(Transform.relationVO(s));
        }
        return relationVOS;
    }

}
