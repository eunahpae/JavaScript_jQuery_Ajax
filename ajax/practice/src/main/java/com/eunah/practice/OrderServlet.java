package com.eunah.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/icecreamOrder")
public class OrderServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("icecreamName");
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		int price = 1000;
		int totalPrice = price * cnt;

		JSONObject jsonResponse = new JSONObject();

		if (cnt > 0) {
			jsonResponse.put("name", name);
			jsonResponse.put("cnt", cnt);
			jsonResponse.put("totalPrice", totalPrice);
			response.setContentType("application/json");

			PrintWriter out = response.getWriter();
			out.print(jsonResponse);
		} else {
			response.sendError(404);
		}
	}
}