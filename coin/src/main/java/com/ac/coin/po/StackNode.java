package com.ac.coin.po;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

@org.springframework.data.neo4j.core.schema.Node
public abstract class StackNode {
    @Id
    @GeneratedValue
    private Long id;

    private Long graphId;

    private String name;

    private String color;

    private int node_size;

    private int font_size;

    private String description;

    public void setId(Long id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getFont_size() {
        return font_size;
    }

    public int getNode_size() {
        return node_size;
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

    public void setFont_size(int font_size) {
        this.font_size = font_size;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNode_size(int node_size) {
        this.node_size = node_size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

