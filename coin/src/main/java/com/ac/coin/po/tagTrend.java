package com.ac.coin.po;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document("tagTrend")
public class tagTrend {
    @Indexed
    @Field("_id")
    private Long id;
    @Field("TagName")
    private String tagName;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Integer> getYear2015() {
        return year2015;
    }

    public List<Integer> getYear2016() {
        return year2016;
    }

    public List<Integer> getYear2017() {
        return year2017;
    }

    public List<Integer> getYear2018() {
        return year2018;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setYear2015(List<Integer> year2015) {
        this.year2015 = year2015;
    }

    public void setYear2016(List<Integer> year2016) {
        this.year2016 = year2016;
    }

    public List<Integer> getYear2019() {
        return year2019;
    }

    public List<Integer> getYear2020() {
        return year2020;
    }

    public void setYear2017(List<Integer> year2017) {
        this.year2017 = year2017;
    }

    public void setYear2018(List<Integer> year2018) {
        this.year2018 = year2018;
    }

    public List<Integer> getYear2021() {
        return year2021;
    }

    public void setYear2019(List<Integer> year2019) {
        this.year2019 = year2019;
    }

    public void setYear2020(List<Integer> year2020) {
        this.year2020 = year2020;
    }

    public void setYear2021(List<Integer> year2021) {
        this.year2021 = year2021;
    }
}
