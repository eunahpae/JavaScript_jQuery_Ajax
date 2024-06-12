package com.eunah.jqyeryajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/receive")
public class ReceiveAjaxTestServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String output = "I'm Get request! 요청 처리 완료~!";

		PrintWriter out = response.getWriter();
		out.print(output);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String message = request.getParameter("message");

		PrintWriter out = response.getWriter();
		out.print("전달 받은 message : " + message);
		out.close();

	}

}
