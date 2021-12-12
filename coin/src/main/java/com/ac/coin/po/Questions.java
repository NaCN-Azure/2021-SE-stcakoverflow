package com.ac.coin.po;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@org.springframework.data.neo4j.core.schema.Node
public class Questions {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String color;

    private int node_size;

    private int font_size;

    private Long graphId;

    private String answers;

    private String description;

    private String votes;

    private String views;

    private String create_date;

    public int getAnswers() {
        return Integer.parseInt(answers);
    }

    public int getVotes(){
        return Integer.parseInt(votes);
    }

    public int getViews(){
        return Integer.parseInt(views);
    }

    public int getCreateDate()  {
        try {
            return Integer.parseInt(create_date.substring(0, 4));
        }
        catch (Exception e){
            return 2021;
        }
    }

    public String getName() {
        return name.replace('丨','\'').replace('亖','\\');
    }

    public Long getId() {
        return id;
    }

    public int getNode_size() {
        return node_size;
    }

    public String getColor() {
        return color;
    }

    public int getFont_size() {
        return font_size;
    }

    public Long getGraphId() {
        return graphId;
    }


}

