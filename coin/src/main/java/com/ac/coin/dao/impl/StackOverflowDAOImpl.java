package com.ac.coin.dao.impl;

import com.ac.coin.dao.NodeDAO;
import com.ac.coin.dao.StackOverflowDAO;
import com.ac.coin.dao.repository.NodeRepository;
import com.ac.coin.dao.repository.RelationRepository;
import com.ac.coin.po.Node;
import com.ac.coin.po.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StackOverflowDAOImpl implements StackOverflowDAO {

    static String default_question_1 = "Why is processing a sorted array faster than processing an unsorted array?";
    static String default_question_2 = "How do I undo the most recent local commits in Git?";
    static String default_question_3 = "How do I delete a Git branch locally and remotely?";

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private RelationRepository relationRepository;

    @Override
    public List<Node> findStackNodesLimit(){
        Set<Node> hashset = new HashSet<>();
        hashset.addAll(nodeRepository.findQTbyName(default_question_1));
        hashset.addAll(nodeRepository.findQTbyName(default_question_2));
        hashset.addAll(nodeRepository.findQTbyName(default_question_3));
        List<Node> nodeList = new ArrayList<>();
        nodeList.addAll(hashset);
        return nodeList;
    }

    @Override
    public List<Relation> findStackRelationsLimit(){
        List<Relation> relations=new ArrayList<>();
        relations.addAll(relationRepository.findQTRelationbyName(default_question_1));
        relations.addAll(relationRepository.findQTRelationbyName(default_question_2));
        relations.addAll(relationRepository.findQTRelationbyName(default_question_3));
        return relations;
    }

}
