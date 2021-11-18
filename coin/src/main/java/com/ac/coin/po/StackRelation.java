package com.ac.coin.po;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

public abstract class StackRelation {
    @Id
    @GeneratedValue
    private Long id;

    private Long graphId;

    private Long from_id;

    private Long to_id;

    private String name;

    public Long getFrom_id() {
        return from_id;
    }

    public Long getTo_id() {
        return to_id;
    }

    public void setFrom_id(Long from_id) {
        this.from_id = from_id;
    }

    public void setTo_id(Long to_id) {
        this.to_id = to_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getGraphId() {
        return graphId;
    }

    public String getName() {
        return name;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public void setName(String name) {
        this.name = name;
    }

}

