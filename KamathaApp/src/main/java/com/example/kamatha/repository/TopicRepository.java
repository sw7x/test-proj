package com.example.kamatha.repository;

import com.example.kamatha.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 *  2020-11-10
 * @author SHAN
 *
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Long countTopicsByUser_Id(Long id);

    Topic findTopicById(Long id);

    List<Topic> findTopicsByCategoryOrderByCreatedDateDesc(String category);
    List<Topic> findTopicsByUser_IdOrderByCreatedDateDesc(Long id);
}
