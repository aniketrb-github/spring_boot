package io.javabrains.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javabrains.dto.TopicDTO;
import io.javabrains.entity.Topic;
import io.javabrains.exception.TopicException;
import io.javabrains.repos.ITopicRepository;

@Service
public class TopicService {

	private static Logger log = LoggerFactory.getLogger(TopicService.class);

	@Autowired
	private ITopicRepository topicRepo;

	/**
	 * @return List of all topic objects present in DB
	 */
	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
		
		// LambdaExpression : adds all elements to the list by iterating on it
		// topicRepo.findAll().forEach(t -> topics.add(t) );
		
		// MethodReference : adds all elements to the list by iterating on it
		topicRepo.findAll().forEach(topics :: add);
		return topics;
	}

	/**
	 * updates an existing topic object based on the given topic id 
	 * @param topic
	 * @param id
	 * @return result String with some meaningful message
	 * @throws TopicException
	 */
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

	/**
	 * deletes a Topic object if it exists in DB based on the topic id
	 * @param id
	 * @return
	 * @throws TopicException
	 */
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

	/**
	 * creates & saves topic object in DB & returns a result String
	 * @param topic
	 * @return
	 */
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

	/**
	 * returns Topic object based on topic id
	 * @param id
	 * @return
	 * @throws TopicException
	 */
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
	
	/**
	 * Returns a Topic object based on the topic name
	 * @param name
	 * @return
	 * @throws TopicException
	 */
	public Topic getTopicByName (String name) throws TopicException {
		if(null != name && !name.isEmpty()) {
			return topicRepo.getTopicByName(name);
		} else 
			throw new TopicException("Topic name cannot be null or empty!. \nPlease enter a valid Topic name.");
	}
	
	/**
	 * Here we get Topic object based on name & id
	 * @param name
	 * @param id
	 * @return
	 * @throws TopicException
	 */
	public Topic getTopicByIdAndName(String name, Long id) throws TopicException {
		if (null != name && !name.isEmpty() && id > 0 && null != id) {
			return topicRepo.getTopicByIdAndName(id, name);
		} else
			throw new TopicException(
					"Topic name or id cannot be null or empty!. \nPlease enter a valid Topic name & id");
	}

	/**
	 * returns some custom data in a DTO object based in topic id
	 * @param id
	 * @return
	 * @throws TopicException
	 */
	public TopicDTO getTopicNameAndDescriptionByTopicId(Long id) throws TopicException {
		if(null != id && id > 0) {
			return topicRepo.getTopicNameAndDescriptionByTopicId(id);
		} else
			throw new TopicException("Topic id cannot be null or empty!. \nPlease enter a valid id");
	}
}
