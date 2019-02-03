package io.javabrains.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.javabrains.entity.Topic;
import io.javabrains.exception.TopicException;
import io.javabrains.service.TopicService;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/topics") // This means URL's start with /topics (after Application path)
public class TopicController {
	
	private static Logger log = LoggerFactory.getLogger(TopicController.class);

	@Autowired
	private TopicService topicService;
	
	@GetMapping
	public @ResponseBody List<Topic> getAllTopics() {
		
		log.info(" ----- executing TopicController: getAllTopics() "); 
		// @ResponseBody means the returned List is the response, not a view name
		return topicService.getAllTopics();
	}
	
	@PostMapping
	public @ResponseBody String createTopic(@RequestBody Topic topic) {
		log.info(" ----- executing TopicController: createTopic() ");
		// @RequestBody means it is a input parameter from the POST request body
		return topicService.createTopic(topic);
	}
	
	@GetMapping(path = "/{id}")
	public @ResponseBody Topic getTopicById(@PathVariable Long id) throws TopicException {
		// @PathVariable means it is a parameter from the GET Request
		return topicService.getTopicById(id);
	}
	
	@PutMapping(path = "/{id}")
	public @ResponseBody String updateTopic(@RequestBody Topic topic, @PathVariable Long id) throws TopicException {
		return topicService.updateTopic(topic, id);
	}
	
	@DeleteMapping(path = "/{id}")
	public @ResponseBody String deleteTopicById(@PathVariable Long id) throws TopicException {
		return topicService.deleteTopicById(id);
	}
	
	@GetMapping(path = "/name/{name}")
	public @ResponseBody Topic getTopicByName(@PathVariable String name) throws TopicException {
		return topicService.getTopicByName(name);
	}
	
	@GetMapping(path = "/{id}/name/{name}")
	public @ResponseBody Topic getTopicByIdAndName(@PathVariable Long id, @PathVariable String name)
			throws TopicException {
		return topicService.getTopicByIdAndName(name, id);
	}
}
