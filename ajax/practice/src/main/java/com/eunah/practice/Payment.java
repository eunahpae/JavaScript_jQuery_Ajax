package com.eunah.practice;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderComplete")
public class Payment extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("카드 결제진행중~");
		String cardName = request.getParameter("cardName");
		String cardNumber = request.getParameter("cardNumber");
		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));

		switch (cardName) {
		case "lotte":
			cardName = "롯데";
			totalPrice =(int)(totalPrice * 0.9);
			break;
		case "hyundai":
			cardName = "현대";
			totalPrice = (int)(totalPrice * 0.8);
			break;
		case "samsung":
			cardName = "삼성";
			totalPrice = (int)(totalPrice * 0.7);
			break;
		case "kb":
			cardName = "국민";
			totalPrice = (int)(totalPrice * 0.6);
			break;
		}
		
		JSONObject jsonResponse = new JSONObject();
		
		jsonResponse.put("cardName", cardName);
		jsonResponse.put("cardNumber", cardNumber);
		jsonResponse.put("totalPrice", totalPrice);
		
		
		response.setContentType("application/json");

		// 응답으로 JSON 객체 전송
		PrintWriter out = response.getWriter();
		out.print(jsonResponse);
	}
}