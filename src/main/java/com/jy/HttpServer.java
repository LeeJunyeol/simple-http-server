package com.jy;

import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer extends Thread {
	private static final Logger log = LoggerFactory.getLogger(HttpServer.class);

	RequestHandler requestHandler;
	ResponseHandler responseHandler;

	Socket clientSocket;

	public HttpServer(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void run() {
		try {
			requestHandler = new RequestHandler();
			Request request = requestHandler.handle(clientSocket);

			responseHandler = new ResponseHandler();
			Response response = responseHandler.handle(request.getRequestTarget(), clientSocket);

			DefaultServlet defaultServlet = new DefaultServlet();
			defaultServlet.service(request, response);

		} catch (IOException e) {
			log.error("IOException error: " + e);
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				log.error("IOException error: " + e);
			}
		}

	}

}