package com.ac.coin.service;

import com.ac.coin.po.Tags;
import com.ac.coin.vo.NodeVO;
import com.ac.coin.vo.GraphVO;
import com.ac.coin.vo.RelationVO;
import com.ac.coin.vo.ResponseVO;
import org.neo4j.driver.internal.value.RelationshipValue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StackOverflowService {
    List<NodeVO> findDefaultStackNode(int year);
    List<RelationVO> findDefaultStackRelation(int year);
    Tags findTargetTag(String name);
    List<NodeVO> findTargetSubTag(String name);
    List<RelationVO> findTargetSubRelation(String name);
}
