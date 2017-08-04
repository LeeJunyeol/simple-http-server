package com.jy;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseHandler {
	private static final Logger log = LoggerFactory.getLogger(ResponseHandler.class);
	
	public Response handle(String requestTarget, Socket clientSocket) throws IOException {
		Response response = new Response();

		String url = "webapp/" + requestTarget;
		
		File file = new File(url);
		if(file.exists()) {
			response.setStatusCode("200");
	        response.setReasonPhrase("OK");
	        log.trace(response.toString());
	        
		} else {
			response.setStatusCode("404");
	        response.setReasonPhrase("Not found.");
	        log.error(response.toString());
		}
        
        response.addHeader("Server: 127.0.0.1");
        
        response.setOutputStream(clientSocket.getOutputStream());
        
		return response;
	}

}
