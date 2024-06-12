package com.eunah.json.section02.array;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/receive/array")
public class ReceiveArrayStringServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.print("[{\"type\":\"get\", \"firstname\":\"squirrel\", \"lastname\":\"pae\", \"heigh\":177}," +
				"{\"type\":\"get\", \"firstname\":\"pig\", \"lastname\":\"pae\", \"heigh\":177}," +
				"{\"type\":\"get\", \"firstname\":\"tiger\", \"lastname\":\"pae\", \"heigh\":177}," +
				"{\"type\":\"get\", \"firstname\":\"sheep\", \"lastname\":\"pae\", \"heigh\":177}]");
		out.close();
		
	}

}
