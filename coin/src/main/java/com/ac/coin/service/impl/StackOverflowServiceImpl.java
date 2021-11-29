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
import org.neo4j.driver.internal.value.RelationshipValue;
import org.neo4j.driver.types.Relationship;
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
    public List<NodeVO> findDefaultStackNode(int year){
        List<Tags> tags = stackOverflowDAO.findHottestTags(year);
        List<NodeVO> nodeVOS = new ArrayList<>();
        int i=1;
        for(Tags t:tags){
            if(StackTrans.has(nodeVOS,t.getId())) continue;
            nodeVOS.add(StackTrans.nodeVO(t,i,5));
            i=i+1;
        }
        return nodeVOS;
    }

    @Override
    public List<RelationVO> findDefaultStackRelation(int year){
        List<RelationshipValue> relationships = stackOverflowDAO.findHottestRelations(year);
        List<RelationVO> relationVOS = new ArrayList<>();
        for(RelationshipValue r:relationships){
            relationVOS.add(StackTrans.relationVO(Transform.relationshipValueToRelation(r)));
        }
        return relationVOS;
    }

    @Override
    public Tags findTargetTag(String name){
        return stackOverflowDAO.findTargetTag(name);
    }

    @Override
    public List<NodeVO> findTargetSubTag(String name){
        List<Tags> tags = stackOverflowDAO.findTargetSubTarget(name);
        List<NodeVO> nodeVOS = new ArrayList<>();
        int i=1;
        for(Tags t:tags){
            if(StackTrans.has(nodeVOS,t.getId())) continue;
            nodeVOS.add(StackTrans.nodeVO(t,i,1));
            i=i+1;
        }
        return nodeVOS;
    }

    @Override
    public List<RelationVO> findTargetSubRelation(String name){
        List<RelationshipValue> relationships = stackOverflowDAO.findTargetSubRelation(name);
        List<RelationVO> relationVOS = new ArrayList<>();
        for(RelationshipValue r:relationships){
            relationVOS.add(StackTrans.relationVO(Transform.relationshipValueToRelation(r)));
        }
        return relationVOS;
    }

}
