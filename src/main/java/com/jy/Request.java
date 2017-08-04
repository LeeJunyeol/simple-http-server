package com.jy;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Request {
	public final static String httpVersion = "HTTP/1.1";
	private Map<String, String> headers = new HashMap<String, String>();

	private InputStream bodyInput;
	private String requestTarget;
	private InputStream inputStream;
	private String method;

	public void setBodyInput(InputStream bodyInput) {
		this.bodyInput = bodyInput;
	}

	public String getRequestTarget() {
		return requestTarget;
	}

	public void setRequestTarget(String requestTarget) {
		if (requestTarget.equals("/")) {
			requestTarget = "/index.html";
		}
		this.requestTarget = requestTarget;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void parseRequestLine(String requestLine) {
		String[] splittedLine = requestLine.split(" ");
		setMethod(splittedLine[0].trim());
		setRequestTarget(splittedLine[1].trim());
	}

	public void addHeader(String header) {
		int end = header.indexOf(":");
		String key = header.substring(0, end);
		String value = header.substring(end + 1);

		headers.put(key, value);
	}

	public String getHeader(String headerName) {
		return headers.get(headerName);
	}

	@Override
	public String toString() {
		return "Request [method=" + getMethod() + ", requestTarget=" + getRequestTarget() + ", bodyInput="
				+ bodyInput.toString() + ", headers=[" + headers.toString() + "]]";
	}
}
