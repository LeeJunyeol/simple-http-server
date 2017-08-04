package com.jy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	private static final Logger log = LoggerFactory.getLogger(HttpServer.class);
	private static final int port = 8080;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			log.error("IOException: " + e);
		}
		try {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				HttpServer httpThread = new HttpServer(clientSocket);
				httpThread.start();
			}
		} catch (Exception e) {
			log.error("Exception: " + e);
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				log.error("IOException: " + e);
			}
		}
	}
}
