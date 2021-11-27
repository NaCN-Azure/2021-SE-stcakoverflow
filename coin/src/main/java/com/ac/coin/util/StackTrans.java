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

    public static NodeVO nodeVO(Tags tags,int i){
        NodeVO nodeVO = new NodeVO();
        nodeVO.setId(tags.getId());
        nodeVO.setGraphId(tags.getGraphId());
        nodeVO.setName( tags.getName());
        nodeVO.setColor(tags.getColor());
        nodeVO.setShape(NodeShape.CIRCLE.toString());
        nodeVO.setNode_size(tags.getNode_size());
        nodeVO.setFont_size(tags.getFont_size());
        nodeVO.setHighlighted(false);
        nodeVO.setShown(true);
        if(i<=5){
            nodeVO.setLabel("SuperTag");
            nodeVO.setColor("#00FF00");
        }
        else {
            nodeVO.setLabel("SonTag");
            nodeVO.setNode_size(20);
        }
        return nodeVO;
    }

    public static Relation relationshipValueToRelation(RelationshipValue relationshipValue){
        Relation relation = new Relation();
        Relationship relationship = relationshipValue.asRelationship();
        relation.setId(relationship.id());
        relation.setFromId(relationship.startNodeId());
        relation.setToId(relationship.endNodeId());
        Map<String, Object> properties = relationship.asMap();
        relation.setGraphId((Long)properties.get("graphId"));
        relation.setName((String)properties.get("name"));
        relation.setLabel((String)properties.get("label"));
        relation.setShown(Boolean.parseBoolean((String)properties.get("isShown")));
        relation.setSolid(Boolean.parseBoolean((String)properties.get("isSolid")));
        relation.setHighlighted(Boolean.parseBoolean((String)properties.get("isHighLighted")));
        return relation;
    }

    public boolean nodeCheck(List<NodeVO> nodeVOS,Long checkid){
        for(NodeVO nodeVO:nodeVOS){
            if(nodeVO.getId().equals(checkid)){
                return true;
            }
        }
        return false;
    }

}
