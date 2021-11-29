package com.ac.coin.po;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

@org.springframework.data.neo4j.core.schema.Node
public class Tags {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String count_2020;

    private String count_2021;

    private String count_2019;

    private String count_2018;

    private String count_2017;

    private String count_2016;

    private String count_2015;

    private String count;

    private String color;

    private int node_size;

    private int font_size;

    private String excerpt;

    private Long graphId;

    public String getName() {
        return name;
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

    public int getCount() {
        return Integer.parseInt(count);
    }

    public int getCount_2021() {
        return Integer.parseInt(count_2021);
    }

    public int getCount_2020() {
        return Integer.parseInt(count_2020);
    }

    public int getCount_2019() {
        return Integer.parseInt(count_2019);
    }

    public int getCount_2018() {
        return Integer.parseInt(count_2018);
    }

    public int getCount_2017() {
        return Integer.parseInt(count_2017);
    }

    public int getCount_2016() {
        return Integer.parseInt(count_2016);
    }

    public int getCount_2015() {
        return Integer.parseInt(count_2015);
    }

    public String getExcerpt() {
        return excerpt;
    }

    public int getCountYear(int year){
        if (year == 2015) {
            return Integer.parseInt(count_2015);
        }
        else if (year == 2016) {
            return Integer.parseInt(count_2016);
        }
        else if (year == 2017) {
            return Integer.parseInt(count_2017);
        }
        else if (year == 2018) {
            return Integer.parseInt(count_2018);
        }
        else if (year == 2019) {
            return Integer.parseInt(count_2019);
        }
        else if (year == 2020) {
            return Integer.parseInt(count_2020);
        }
        else if (year == 2021) {
            return Integer.parseInt(count_2020);
        }
        return Integer.parseInt(count);
    }

}

