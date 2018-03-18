package com.qaiware.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.qaiware.model.Message;
import com.qaiware.model.RequestData;
import com.qaiware.service.MessageService;
import com.qaiware.validation.PayloadValidator;

@RestController
public class ChatController {
	
    @Autowired
    private MessageService messageService;

    private final PayloadValidator payloadValidator;

    @Autowired
    public ChatController(PayloadValidator stringValueValidator) {
        this.payloadValidator = stringValueValidator;
    }
	
	@RequestMapping(value = "/messages/{type}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
    public ResponseEntity<?> send(
    		@PathVariable("type") String type,
    		@RequestBody RequestData body, Errors errors) {
		
		payloadValidator.validate(type, body, errors);
        if (errors.hasErrors()) {
        	return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
        
        messageService.create(new Message(type, body.getPayload()));
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/messages/{type}", method = GET)
    public String dump(
    		@PathVariable("type") String type) {
		
        return "Listing messages for @type=" + type;
        
    }
	
}
