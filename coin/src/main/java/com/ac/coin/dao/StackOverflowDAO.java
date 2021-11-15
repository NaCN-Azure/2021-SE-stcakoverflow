package com.ac.coin.dao;

import com.ac.coin.po.Node;
import com.ac.coin.po.Relation;

import java.util.List;
import java.util.Optional;

public interface StackOverflowDAO {

    List<Node> findStackNodesLimit();
    List<Relation> findStackRelationsLimit();
}
