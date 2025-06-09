package com.sumauma.dscommerce.dto;

import java.time.Instant;

public class BodyError {

	private Instant timeStamp;
	private Integer status;
	private String msgError;
	private String pathUri;
	
	public BodyError(Instant timeStamp, Integer status, String msgError, String pathUri) {
		super();
		this.timeStamp = timeStamp;
		this.status = status;
		this.msgError = msgError;
		this.pathUri = pathUri;
	}

	public Instant getTimeStamp() {
		return timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMsgError() {
		return msgError;
	}

	public String getPathUri() {
		return pathUri;
	}
	
		
}
