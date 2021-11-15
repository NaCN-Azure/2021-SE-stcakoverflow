package com.ac.coin.service;

import com.ac.coin.vo.NodeVO;
import com.ac.coin.vo.GraphVO;
import com.ac.coin.vo.RelationVO;
import com.ac.coin.vo.ResponseVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StackOverflowService {
    List<NodeVO> findStackNode();
    List<RelationVO> findStackRelation();
}
