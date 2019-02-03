package io.javabrains.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.javabrains.dto.TopicDTO;
import io.javabrains.entity.Topic;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Long> {
/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called ITopicRepository 
 * This includes Create, Read, Update, Delete
 */
	
	public Topic getTopicByName(String name);
	public Topic getTopicByIdAndName(Long id, String name);
	public TopicDTO getTopicNameAndDescriptionByTopicId(Long id);
}
