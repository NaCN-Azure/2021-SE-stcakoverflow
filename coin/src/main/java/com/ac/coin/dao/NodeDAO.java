package com.ac.coin.dao;

import com.ac.coin.po.Node;

import java.util.List;
import java.util.Optional;

public interface NodeDAO {
    Long addNode(Node node);
    void deleteNodeById(Long nodeId);
    Node editNode(Node node);
    Optional<Node> findNodeById(Long nodeId);
    List<Node> findAllNodesByGraphId(Long graphId);
    List<Node> findToByFromId(Long fromId);
}
