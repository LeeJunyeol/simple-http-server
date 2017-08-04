package com.jy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultServlet {
	private static final Logger log = LoggerFactory.getLogger(DefaultServlet.class);

	public void service(Request req, Response res) {

		PrintWriter out = null;

		out = new PrintWriter(new OutputStreamWriter(res.getOutputStream()));

		BufferedReader br = null;
		try {
			String statusCode = res.getStatusCode();
			String url = "webapp/";
			if(statusCode.equals("200")) {
				url += req.getRequestTarget();
			} else if(res.getStatusCode().equals("404")){
				url += "404.html";
			}
			Path path = Paths.get(url);
			
			br = new BufferedReader(new FileReader(url));
			StringBuffer sb = new StringBuffer();

			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			out.println(res.getStatusLine());
			DateFormat dateFormat = new SimpleDateFormat("D, dd M yyyy HH:mm:ss.SSSX");
			out.println("Date: " + dateFormat.format(new Date()));
			// out.println("Date: Sat, 09 Oct 2010 14:28:02 GMT");
			res.addHeader("Content-type: " + Files.probeContentType(path));
			res.addHeader("Content-Length: " + sb.toString().length());
			out.println(res.getHeaderString());

			out.println(sb);
			out.flush();
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException: " + e);
		} catch (IOException e) {
			log.error("IOException: " + e);
		}

	}
	
	public void success() {
		
	}
	
	public void error() {
		
	}
	
}
