package com.ac.coin.po;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;



public class Question {


    private String create_date;

    private String votes;

    private String answers;

    private String views;

    private String questions;

    private String links;

    private double rate;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public int getCreate_date() {
        try {
            return Integer.parseInt(create_date.substring(0, 4));
        }
        catch (Exception e){
            return 2021;
        }
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
}
