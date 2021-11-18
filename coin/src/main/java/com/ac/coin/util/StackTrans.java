package com.ac.coin.util;

import com.ac.coin.enums.NodeShape;
import com.ac.coin.enums.Size;
import com.ac.coin.po.*;
import com.ac.coin.vo.GraphVO;
import com.ac.coin.vo.NodeVO;
import com.ac.coin.vo.RelationVO;
import com.ac.coin.vo.UserVO;
import org.neo4j.driver.Value;
import org.neo4j.driver.internal.value.IntegerValue;
import org.neo4j.driver.internal.value.MapValue;
import org.neo4j.driver.internal.value.RelationshipValue;
import org.neo4j.driver.internal.value.StringValue;
import org.neo4j.driver.types.Relationship;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class StackTrans {

    private static String QuestionsToGraph(String question){
        return question.replace('丨','\'').replace('亖','\\');
    }

    private static String QuestionsToDatabase(String question){
        return question.replace('\'','丨').replace('\\','亖');
    }

    public static boolean has(List<NodeVO> nodeVOS,Long id){
        for(NodeVO x:nodeVOS){
            if(x.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public static NodeVO NodeVO(StackNode stackNode){
        NodeVO nodeVO=new NodeVO();
        int check = Integer.parseInt(stackNode.getDescription());
        String name = check==-1?QuestionsToGraph(stackNode.getName()):stackNode.getName();
        nodeVO.setName(name);
        nodeVO.setColor(stackNode.getColor());
        nodeVO.setShape(NodeShape.CIRCLE.toString());
        nodeVO.setFont_size(stackNode.getFont_size());
        nodeVO.setNode_size(stackNode.getFont_size());
        nodeVO.setGraphId(stackNode.getGraphId());
        nodeVO.setHighlighted(false);
        nodeVO.setShown(true);
        nodeVO.setId(stackNode.getId());
        if(check==-2){nodeVO.setLabel("Tags");}
        else if(check==-1){nodeVO.setLabel("Questions");}
        else {nodeVO.setLabel("Replies");}
        return nodeVO;
    }

    public static RelationVO relationVO(StackRelation stackRelation){
        RelationVO relationVO = new RelationVO();
        relationVO.setId(stackRelation.getId());
        relationVO.setSource(stackRelation.getFrom_id());
        relationVO.setTarget(stackRelation.getTo_id());
        relationVO.setGraphId(stackRelation.getGraphId());
        relationVO.setName(stackRelation.getName());
        relationVO.setLabel(stackRelation.getName());
        relationVO.setSolid(true);
        relationVO.setHighlighted(false);
        relationVO.setShown(true);
        return relationVO;
    }

}
