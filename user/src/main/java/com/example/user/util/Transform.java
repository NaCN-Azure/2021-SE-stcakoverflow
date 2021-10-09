package com.example.user.util;

//import com.ac.coin.enums.NodeShape;
//import com.ac.coin.enums.Size;
import com.example.user.po.Graph;
import com.example.user.po.User;
import com.example.user.vo.GraphVO;
import com.example.user.vo.UserVO;
import org.neo4j.driver.Value;
import org.neo4j.driver.internal.value.IntegerValue;
import org.neo4j.driver.internal.value.MapValue;
import org.neo4j.driver.internal.value.RelationshipValue;
import org.neo4j.driver.internal.value.StringValue;
import org.neo4j.driver.types.Relationship;

import java.util.HashMap;
import java.util.Map;

public class Transform {

    public static GraphVO graphVO(Graph graph){
        GraphVO graphVO = new GraphVO();
        graphVO.setId(graph.getId());
        graphVO.setName(graph.getName());
        graphVO.setNodeLabelVisible(graph.isNodeLabelVisible());
        graphVO.setRelationLabelVisible(graph.isRelationLabelVisible());
        graphVO.setUrl(graph.getUrl());
        graphVO.setTime(graph.getTime());
        graphVO.setFavored(graph.getFavored());
        return graphVO;
    }


    public static UserVO userVO(User user){
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setName(user.getName());
        userVO.setPassword(user.getPassword());
        userVO.setBalance(user.getBalance());
        userVO.setRisk(user.getRisk());
        userVO.setAcceptSt(user.isAcceptSt());
        userVO.setStocks(user.getStocks());
        userVO.setIndustries(user.getIndustries());
        return userVO;
    }

    public static User userPO(UserVO userVO){
        User user = new User(userVO.getName(),userVO.getPassword());
        if(userVO.getId()!=-1) user.setId(userVO.getId());
        user.setRisk((userVO.getRisk()!=-1)? userVO.getRisk():1);
        user.setBalance((userVO.getBalance()!=-1)? userVO.getBalance():2.0);
        user.setStocks(userVO.getStocks());
        user.setIndustries(userVO.getIndustries());
        user.setAcceptSt(userVO.isAcceptSt());
        return user;
    }
}
