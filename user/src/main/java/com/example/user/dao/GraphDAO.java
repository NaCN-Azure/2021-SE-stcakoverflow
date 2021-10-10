package com.example.user.dao;

//import com.ac.coin.po.Graph;
import com.example.user.po.Graph;

import java.util.List;
import java.util.Optional;

public interface GraphDAO {
    Long addGraph(Graph graph, Long userId);
    void deleteGraphById(Long graphId);
    void deleteAllById(Long graphId);
    void deleteAllGraphs();
    Graph editGraph(Graph graph);
    Optional<Graph> findGraphById(Long graphId);
    Long findGraphByName(String name);
    List<Graph> findAllGraphs();
    Long setGraphName(String name);
}
