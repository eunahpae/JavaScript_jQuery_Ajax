package com.eunah.gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import com.google.gson.Gson;
import com.eunah.model.dto.MemberDTO;

@WebServlet("/gson/test2")
public class GsonTestServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberCode = request.getParameter("memberCode");
		String name = request.getParameter("name");
		char gender = request.getParameter("gender").charAt(0);
		int age = Integer.parseInt(request.getParameter("age"));
		Date enrollDate = Date.valueOf(request.getParameter("enrollDate"));
		
		MemberDTO member = new MemberDTO(memberCode, name, gender, age, enrollDate);
		String jsonString = new Gson().toJson(member);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		out.close();

	}

}