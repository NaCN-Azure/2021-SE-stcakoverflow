package com.ac.coin.dao;

import com.ac.coin.po.*;
import com.ac.coin.vo.RelationVO;

import java.util.List;
import java.util.Optional;

public interface StackOverflowDAO {

    List<Tags> getTagsByQuestionName(String question);

    List<Replies> getRepliesByQuestionName(String question);

    Optional<Questions> getQuestion(String question);

    List<Relation> getBelongsByQuestion(String question);

    List<Relation> getAnswersByQuestion(String question);
}
