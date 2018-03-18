package com.qaiware.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestData {

	@NotNull
	private final String payload;

	@JsonCreator
    public RequestData(@JsonProperty("payload") String payload) {
        this.payload = payload;
    }
	
	public String getPayload() {
		return payload;
	}
}
