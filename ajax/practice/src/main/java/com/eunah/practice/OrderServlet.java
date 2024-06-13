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

		// 요청으로부터 icecreamName과 cnt를 받아옴
		String name = request.getParameter("icecreamName");
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		int price = 1000;
		int totalPrice = price * cnt;

		// JSON 객체 생성
		JSONObject jsonResponse = new JSONObject();

		// 수량이 유효한 경우
		if (cnt > 0) {
			// JSON 객체에 데이터 추가
			jsonResponse.put("name", name);
			jsonResponse.put("cnt", cnt);
			jsonResponse.put("totalPrice", totalPrice);

			// 응답의 Content-Type 설정
			response.setContentType("application/json");

			// 응답으로 JSON 객체 전송
			PrintWriter out = response.getWriter();
			out.print(jsonResponse);
		} else {
			response.sendError(404);
		}
	}
}