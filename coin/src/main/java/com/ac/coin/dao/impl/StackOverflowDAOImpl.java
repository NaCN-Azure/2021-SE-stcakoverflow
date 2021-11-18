package com.ac.coin.dao.impl;

import com.ac.coin.dao.StackOverflowDAO;
import com.ac.coin.dao.repository.NodeRepository;
import com.ac.coin.dao.repository.RelationRepository;
import com.ac.coin.dao.repository.StackNodeRepository;
import com.ac.coin.dao.repository.StackRelationRepository;
import com.ac.coin.po.*;
import com.ac.coin.util.Transform;
import com.ac.coin.vo.RelationVO;
import org.neo4j.driver.internal.value.RelationshipValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.*;

@Repository
public class StackOverflowDAOImpl implements StackOverflowDAO {

    @Autowired
    StackNodeRepository stackNodeRepository;

    @Autowired
    StackRelationRepository stackRelationRepository;

    @Override
    public List<Tags> getTagsByQuestionName(String question){
        return stackNodeRepository.findTagsByQuestionName(question);
    }

    @Override
    public List<Replies> getRepliesByQuestionName(String question){
        return stackNodeRepository.findRepliesByQuestionName(question);
    }

    @Override
    public Optional<Questions> getQuestion(String question){
        return stackNodeRepository.findQuestion(question);
    }

    @Override
    public List<Relation> getBelongsByQuestion(String question){
        //todo
        List<Relation> x = new ArrayList<>();
        List<RelationshipValue> relationshipValues=stackRelationRepository.findBelongsByQuestionName(question);
        for(RelationshipValue relationshipValue:relationshipValues){
            x.add(Transform.relationshipValueToRelation(relationshipValue));
        }
        return x;
    }

    @Override
    public List<Relation> getAnswersByQuestion(String question){
        List<Relation> x = new ArrayList<>();
        List<RelationshipValue> relationshipValues=stackRelationRepository.findAnswersByQuestionName(question);
        for(RelationshipValue relationshipValue:relationshipValues){
            x.add(Transform.relationshipValueToRelation(relationshipValue));
        }
        return x;
    }

}
