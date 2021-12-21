package com.ac.coin.po;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

public class contains {
    @Id
    @GeneratedValue
    private Long id;

    private Long fromId;

    private Long toId;

    private Long graphId;

    private String name;

    private String count;

    private String count_2021;

    private String count_2020;

    private String count_2019;

    private String count_2018;

    private String count_2017;

    private String count_2016;

    private String count_2015;

    public contains() {
        //不开放初始化时自定义

    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCount_2015() {
        return count_2015;
    }

    public String getCount_2016() {
        return count_2016;
    }

    public String getCount_2017() {
        return count_2017;
    }

    public String getCount_2018() {
        return count_2018;
    }

    public String getCount_2019() {
        return count_2019;
    }

    public String getCount_2020() {
        return count_2020;
    }

    public String getCount_2021() {
        return count_2021;
    }

    public void setCount_2015(String count_2015) {
        this.count_2015 = count_2015;
    }

    public void setCount_2016(String count_2016) {
        this.count_2016 = count_2016;
    }

    public void setCount_2017(String count_2017) {
        this.count_2017 = count_2017;
    }

    public void setCount_2018(String count_2018) {
        this.count_2018 = count_2018;
    }

    public void setCount_2019(String count_2019) {
        this.count_2019 = count_2019;
    }

    public void setCount_2020(String count_2020) {
        this.count_2020 = count_2020;
    }

    public void setCount_2021(String count_2021) {
        this.count_2021 = count_2021;
    }
}


