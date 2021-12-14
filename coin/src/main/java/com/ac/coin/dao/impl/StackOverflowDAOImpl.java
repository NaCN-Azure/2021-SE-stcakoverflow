package com.ac.coin.dao.impl;

import com.ac.coin.dao.StackOverflowDAO;
import com.ac.coin.dao.repository.ChartRepository;
import com.ac.coin.dao.repository.QuesRepository;
import com.ac.coin.dao.repository.StackRepository;
import com.ac.coin.dao.repository.TagsRepository;
import com.ac.coin.po.Questions;
import com.ac.coin.po.tagTrend;
import com.ac.coin.po.Tags;
import org.neo4j.driver.internal.value.RelationshipValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class StackOverflowDAOImpl implements StackOverflowDAO {

    @Autowired
    TagsRepository tagsRepository;

    @Autowired
    StackRepository stackRepository;

    @Autowired
    QuesRepository quesRepository;

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
        return chartRepository.findByTagName(name);
    }

    @Override
    public List<Questions> findQuestions(String name)  {
        List<Questions> checklist = quesRepository.findQuestions(name);
        List<Questions> results = new ArrayList<>();
        if(checklist.size()==0){
            return results;
        }
        HashMap<Questions,Double> hashMap = new HashMap<>();
        for(Questions q:checklist){
            int view = q.getViews();
            int answers = q.getAnswers();
            int votes = q.getVotes();
            int year = 2021;
            int create = q.getCreateDate();
            Double rate =  ((Math.log10(view))*4+answers*votes/5)/(Math.pow((double) (year+create)/2,1.5));
            hashMap.put(q,rate);
        }
        List<Map.Entry<Questions,Double>> list = new ArrayList<>(hashMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Questions, Double>>() {
            @Override
            public int compare(Map.Entry<Questions, Double> o1, Map.Entry<Questions, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        int x= Math.min(list.size()-1, 4);
        for(int i=0;i<=x;i++){
            results.add(list.get(i).getKey());
        }
        return results;
    }

    @Override
    public  List<String> findNodesFuzzy(String name){
        return tagsRepository.findByNameContains(name);
    }

}
