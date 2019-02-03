package io.javabrains.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javabrains.entity.Topic;
import io.javabrains.exception.TopicException;
import io.javabrains.repos.ITopicRepository;

@Service
public class TopicService {

	private static Logger log = LoggerFactory.getLogger(TopicService.class);

	@Autowired
	private ITopicRepository topicRepo;

	// getAllTopics
	public List<Topic> getAllTopics() {
		return topicRepo.findAll();
	}

	// updateTopic
	public String updateTopic(Topic topic, Long id) throws TopicException {
		if (null != topic && null != id && id > 0) {
			Optional<Topic> topicWrapper = topicRepo.findById(id);
			if (topicWrapper.isPresent()) {
				Topic topicDb = topicWrapper.get();
				topicDb.setId(topic.getId());
				topicDb.setName(topic.getName());
				topicDb.setDescription(topic.getDescription());
				topicRepo.save(topicDb);
				return "Requested Topic Object with id:" + topic.getId() + " updated successully in DB.";
			} else
				throw new TopicException("The requested Topic object for topicId: " + id + " not found id DB.");
		} else {
			return "The Topic Object to be updated in DB cannot be null.";
		}
	}

	// deleteTopic
	public String deleteTopicById(Long id) throws TopicException {
		if (null != id && id > 0) {
			Optional<Topic> topicWrapper = topicRepo.findById(id);
			if (topicWrapper.isPresent()) {
				topicRepo.delete(topicWrapper.get());
				return "The requested topic with topicId: " + id + " deleted successfully from DB.";
			} else
				throw new TopicException("The requested Topic object for topicId: " + id + " not found id DB.");
		} else
			return "The Topic Object to be saved in DB cannot be null.";
	}

	// createTopic
	public String createTopic(Topic topic) {
		log.info(" ----- executing TopicService: createTopic()");

		Topic savedEntity = null;
		if (null != topic) {
			savedEntity = topicRepo.save(topic);

			if (null != savedEntity)
				return "Topic with id: " + savedEntity.getId() + " has been successfully created in DB !";
			else
				return "DB error occured while saving the Topic with id: " + savedEntity.getId() + "  object in DB.";
		}

		return "The Topic Object to be saved in DB cannot be null.";
	}

	// getTopic
	public Topic getTopicById(Long id) throws TopicException {
		if (null != id && id > 0) {
			Optional<Topic> topicWrapper = topicRepo.findById(id);
			if (topicWrapper.isPresent()) {
				return topicWrapper.get();
			} else {
				throw new TopicException("Topic for id: " + id + " not found in DB!");
			}
		} else
			return null;
	}
}
