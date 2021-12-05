package com.ac.coin.po;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document("tagTrend")
public class tagTrend {
    @Indexed(unique = true)
    @Field("_id")
    private Integer id;
    @Field("TagName")
    private String TagName;
    @Field("2015")
    private List<Integer> year2015;
    @Field("2016")
    private List<Integer> year2016;
    @Field("2017")
    private List<Integer> year2017;
    @Field("2018")
    private List<Integer> year2018;
    @Field("2019")
    private List<Integer> year2019;
    @Field("2020")
    private List<Integer> year2020;
    @Field("2021")
    private List<Integer> year2021;

}
