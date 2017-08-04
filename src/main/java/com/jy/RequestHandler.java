package com.jy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestHandler {
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

	Request handle(Socket clientSocket) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			log.error("IOException: " + e);
		}

		String header = null;
		Request request = new Request();
		String requestLine = null;
		try {
			if ((requestLine = in.readLine()) != null) {
				log.trace("requestLine : " + requestLine);
				request.parseRequestLine(requestLine);
			}
		} catch (IOException e) {
			log.error("IOException: " + e);
		}
		try {
			header = in.readLine();
			while (!"".equals(header)) {
				log.trace("readLine : " + header);
				request.addHeader(header);

				header = in.readLine();
			}

			request.setBodyInput(clientSocket.getInputStream());
			
		} catch (IOException e) {
			log.error("IOException: " + e);
		}

		log.trace(request.toString());

		return request;
	}

}
