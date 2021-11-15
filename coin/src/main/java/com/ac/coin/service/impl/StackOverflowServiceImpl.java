package com.ac.coin.service.impl;

import com.ac.coin.dao.*;
import com.ac.coin.enums.NodeShape;
import com.ac.coin.po.Graph;
import com.ac.coin.po.Node;
import com.ac.coin.po.Relation;
import com.ac.coin.po.User;
import com.ac.coin.service.GraphService;
import com.ac.coin.service.StackOverflowService;
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

    @Autowired
    private StackOverflowDAO stackOverflowDAO;

    @Override
    public List<NodeVO> findStackNode(){
        //todo 等数据爬好后用limit进行限制，不然1w条数据会炸
        List<Node> nodeList = stackOverflowDAO.findStackNodesLimit();//Stack图谱是Id为-1的默认图谱
        List<NodeVO> nodeVOList = new ArrayList<>();
        for(Node node:nodeList){
            nodeVOList.add(Transform.nodeVO(node));
        }
        return nodeVOList;
    }

    @Override
    public List<RelationVO> findStackRelation(){
        List<Relation> relationList = stackOverflowDAO.findStackRelationsLimit();
        List<RelationVO> relationVOList = new ArrayList<>();
        for(Relation relation:relationList){
            relationVOList.add(Transform.relationVO(relation));
        }
        return relationVOList;
    }

}
