package com.ac.coin.util;

import com.ac.coin.enums.NodeShape;
import com.ac.coin.enums.Size;
import com.ac.coin.po.*;
import com.ac.coin.vo.*;
import org.neo4j.driver.Value;
import org.neo4j.driver.internal.value.*;
import org.neo4j.driver.types.Relationship;

import java.util.HashMap;
import java.util.Map;

public class Transform {

    public static String QuestionsToGraph(String question){
        return question.replace('丨','\'');
    }

    public static String QuestionsToDatabase(String question){
        return question.replace('\'','丨');
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

    public static MapValue relationToMapValue(Relation relation){
        Map<String, Value> properties = new HashMap<>();
        properties.put("graphId",new IntegerValue(relation.getGraphId()));
        properties.put("name",new StringValue(relation.getName()));
        if(relation.getLabel()!=null) properties.put("label",new StringValue(relation.getLabel()));
        else properties.put("label",new StringValue(""));
        properties.put("isShown", new StringValue(String.valueOf(relation.isShown())));
        properties.put("isSolid", new StringValue(String.valueOf(relation.isSolid())));
        properties.put("isHighLighted", new StringValue(String.valueOf(relation.isHighlighted())));
        return new MapValue(properties);
    }

    public static RelationVO relationVO(Relation relation){
        RelationVO relationVO = new RelationVO();
        relationVO.setId(relation.getId());
        relationVO.setSource(relation.getFromId());
        relationVO.setTarget(relation.getToId());
        relationVO.setGraphId(relation.getGraphId());
        relationVO.setName(relation.getName());
        relationVO.setLabel(relation.getLabel());
        relationVO.setSolid(relation.isSolid());
        relationVO.setHighlighted(relation.isHighlighted());
        relationVO.setShown(relation.isShown());
        return relationVO;
    }

    public static Relation relationPO(RelationVO relationVO) {
        Relation relation = new Relation();
        relation.setGraphId(relationVO.getGraphId());
        relation.setName(relationVO.getName());
        relation.setLabel(relationVO.getLabel());
        relation.setFromId(relationVO.getSource());
        relation.setToId(relationVO.getTarget());
        relation.setSolid(relationVO.isSolid());
        relation.setHighlighted(relationVO.isHighlighted());
        relation.setShown(relationVO.isShown());
        if (relationVO.getId() != -1) relation.setId(relationVO.getId());
        return relation;
    }

    public static NodeVO nodeVO(Node node){
        NodeVO nodeVO = new NodeVO();
        String className = node.getClass().getName();
        nodeVO.setId(node.getId());
        nodeVO.setGraphId(node.getGraphId());
        nodeVO.setName( QuestionsToGraph(node.getName()));//丨 to '
        nodeVO.setLabel(node.getLabel());
        nodeVO.setColor(node.getColor());
        if (node.getShape()==null) node.setShape(NodeShape.CIRCLE);
        nodeVO.setShape(node.getShape().toString());
        nodeVO.setX(node.getX());
        nodeVO.setY(node.getY());
        nodeVO.setNode_size(node.getNode_size());
        nodeVO.setFont_size(node.getFont_size());
        nodeVO.setHighlighted(node.isHighlighted());
        nodeVO.setTypesetting_x(node.getTypesetting_x());
        nodeVO.setTypesetting_y(node.getTypesetting_y());
        nodeVO.setShown(node.isShown());

        return nodeVO;
    }

    public static Node nodePO(NodeVO nodeVO){
        Node node = new Node();
        //必填
        node.setName(QuestionsToDatabase(nodeVO.getName()));//' to 丨
        node.setLabel(nodeVO.getLabel());
        node.setGraphId(nodeVO.getGraphId());
        node.setX(nodeVO.getX());
        node.setY(nodeVO.getY());
        //可选
        node.setShape((nodeVO.getShape()!=null)? NodeShape.valueOf(nodeVO.getShape().toUpperCase()): NodeShape.CIRCLE);
        node.setColor((nodeVO.getColor()!=null)?nodeVO.getColor():"#58c5c7");
        node.setNode_size((nodeVO.getNode_size()!=-1)?nodeVO.getNode_size(): Size.DEFAULT_NODE_SIZE.getValue());
        node.setFont_size((nodeVO.getFont_size()!=-1)?nodeVO.getFont_size():Size.DEFAULT_FONT_SIZE.getValue());
        node.setTypesetting_x(nodeVO.getTypesetting_x());
        node.setTypesetting_y(nodeVO.getTypesetting_y());
        if(nodeVO.getId()!=-1) node.setId(nodeVO.getId());
        node.setShown(nodeVO.isShown());
        return node;
    }

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

    public static Graph graphPO(GraphVO graphVO){
        Graph graph = new Graph(graphVO.getName());
        if(graphVO.getId()!=-1) graph.setId(graphVO.getId());
        graph.setNodeLabelVisible(graphVO.isNodeLabelVisible());
        graph.setRelationLabelVisible(graphVO.isRelationLabelVisible());
        graph.setUrl(graphVO.getUrl());
        graph.setTime(graphVO.getTime());
        graph.setFavored(graphVO.getFavored());
        return graph;
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
