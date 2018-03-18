package com.qaiware.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.qaiware.model.RequestData;

@Component
public class PayloadValidator {
	
	public void validate(String type, RequestData request, Errors errors) {
    
		String payload = request.getPayload();
		if("".equals(payload)) {
			errors.reject("payload");
		}
		
		if ("send_text".equals(type)) {
            if (payload.length() > 160) {
                errors.reject("payload");
            }
        }
        else if ("send_emotion".equals(type)) {
        	
        	if (payload.length() > 10) {
                errors.reject("payload");
            }
        	 
        	Pattern p = Pattern.compile("\\d");
        	Matcher m = p.matcher(payload);
        	 
        	if (m.find()) {
        		errors.reject("payload");
        	}
        }
        
    }
	
}
