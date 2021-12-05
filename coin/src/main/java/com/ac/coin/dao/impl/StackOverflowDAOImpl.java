package com.ac.coin.dao.impl;

import com.ac.coin.dao.StackOverflowDAO;
import com.ac.coin.dao.repository.ChartRepository;
import com.ac.coin.dao.repository.StackRepository;
import com.ac.coin.dao.repository.TagsRepository;
import com.ac.coin.po.tagTrend;
import com.ac.coin.po.Tags;
import org.neo4j.driver.internal.value.RelationshipValue;
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

    @Autowired
    ChartRepository chartRepository;

    private List<Tags> findYears(int year){
        if(year==0){
            return tagsRepository.findHottestTags();
        }
        else if(year==2015){
            return tagsRepository.findHottestTags2015();
        }
        else if(year==2016){
            return tagsRepository.findHottestTags2016();
        }
        else if(year==2017){
            return tagsRepository.findHottestTags2017();
        }
        else if(year==2018){
            return tagsRepository.findHottestTags2018();
        }
        else if(year==2019){
            return tagsRepository.findHottestTags2019();
        }
        else if(year==2020){
            return tagsRepository.findHottestTags2020();
        }
        else if(year==2021){
            return tagsRepository.findHottestTags2021();
        }
        return tagsRepository.findHottestTags();

    }

    @Override
    public List<Tags> findHottestTags(int year){
        List<Tags> result = new ArrayList<>();
        List<Tags> tags=findYears(year);
        result.addAll(tags);
        for(Tags t:tags){
            List<Tags> subTags=tagsRepository.findRelatedHottestSubTags(t.getId());
            result.addAll(subTags);
        }
        return result;
    }

    @Override
    public List<RelationshipValue> findHottestRelations(int year){
        List<RelationshipValue> result = new ArrayList<>();
        List<Tags> tags=findYears(year);
        for(Tags t:tags){
            List<RelationshipValue> subTags=stackRepository.findRelatedHottestSubTags(t.getId());
            result.addAll(subTags);
        }
        return result;
    }

    @Override
    public Tags findTargetTag(String name){
        return tagsRepository.findTargetNode(name);
    }

    @Override
    public List<Tags> findTargetSubTarget(String name){
        Tags father = tagsRepository.findTargetNode(name);
        List<Tags> son = tagsRepository.findTargetSubNodesAll(father.getId());
        son.add(0,father);
        return son;
    }

    @Override
    public List<RelationshipValue> findTargetSubRelation(String name){
        Tags father = tagsRepository.findTargetNode(name);
        return stackRepository.findTargetRelatedSubTags(father.getId());
    }

    @Override
    public tagTrend findTargetNodesChart(String name){
        return chartRepository.findAll().get(0);//我接不上了...
    }
}
