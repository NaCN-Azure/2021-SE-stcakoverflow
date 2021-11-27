package com.ac.coin.dao.impl;

import com.ac.coin.dao.StackOverflowDAO;
import com.ac.coin.dao.repository.StackRepository;
import com.ac.coin.dao.repository.TagsRepository;
import com.ac.coin.po.Tags;
import org.apache.commons.collections.list.AbstractLinkedList;
import org.neo4j.driver.internal.value.RelationshipValue;
import org.neo4j.driver.types.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StackOverflowDAOImpl implements StackOverflowDAO {

    @Autowired
    TagsRepository tagsRepository;

    @Autowired
    StackRepository stackRepository;

    @Override
    public List<Tags> findHottestTags(int year){
        List<Tags> result = new ArrayList<>();
        if(year==0){
            List<Tags> tags=tagsRepository.findHottestTags();
            result.addAll(tags);
            for(Tags t:tags){
                List<Tags> subTags=tagsRepository.findRelatedHottestSubTags(t.getId());
                result.addAll(subTags);
            }
            return result;
        }
        else {
            List<Tags> tags=tagsRepository.findHottestTagsByYear(year);
            result.addAll(tags);
            for(Tags t:tags){
                List<Tags> subTags=tagsRepository.findRelatedHottestSubTagsByYear(t.getId(),year);
                result.addAll(subTags);
            }
            return result;
        }
    }

    @Override
    public List<RelationshipValue> findHottestRelations(int year){
        List<RelationshipValue> result = new ArrayList<>();
        if(year==0){
            List<Tags> tags=tagsRepository.findHottestTags();
            for(Tags t:tags){
                List<RelationshipValue> subTags=stackRepository.findRelatedHottestSubTags(t.getId());
                result.addAll(subTags);
            }
            return result;
        }
        else {
            List<Tags> tags=tagsRepository.findHottestTagsByYear(year);
            for(Tags t:tags){
                List<RelationshipValue> subTags=stackRepository.findRelatedHottestSubTagsByYear(t.getId(),year);
                result.addAll(subTags);
            }
            return result;
        }
    }

}
