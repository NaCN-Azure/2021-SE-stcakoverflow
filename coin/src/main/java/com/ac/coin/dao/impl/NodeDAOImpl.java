package com.ac.coin.dao.impl;

import com.ac.coin.dao.NodeDAO;
import com.ac.coin.dao.repository.NodeRepository;
import com.ac.coin.po.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NodeDAOImpl implements NodeDAO {

    @Autowired
    private NodeRepository nodeRepository;

    @Override
    public Long addNode(Node node) {
        nodeRepository.save(node);
        return node.getId();
    }

    @Override
    public void deleteNodeById(Long nodeId) {
        nodeRepository.deleteById(nodeId);
    }

    @Override
    public Node editNode(Node node) {
        nodeRepository.save(node);
        return node;
    }

    @Override
    public Optional<Node> findNodeById(Long nodeId) {
        return nodeRepository.findById(nodeId);
    }

    @Override
    public List<Node> findAllNodesByGraphId(Long graphId) {
        return nodeRepository.findAllNodesByGraphId(graphId);
    }

    @Override
    public List<Node> findToByFromId(Long fromId) {
        return nodeRepository.findToByFromId(fromId);
    }

}
