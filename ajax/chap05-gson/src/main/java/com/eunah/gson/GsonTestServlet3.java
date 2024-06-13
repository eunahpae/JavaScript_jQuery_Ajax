package com.eunah.gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.eunah.model.dto.MemberDTO;

@WebServlet("/gson/test3")
public class GsonTestServlet3 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jsonString = request.getParameter("jsonString");
		System.out.println(jsonString);
		
//		Gson gson = new Gson();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
		
		String dateTest = gson.toJson(new java.sql.Date(System.currentTimeMillis()));
		System.out.println("[dateTest] " + dateTest);
		
		MemberDTO member = gson.fromJson(jsonString, MemberDTO.class);
		System.out.println(member);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(member));
		out.close();
		
	}

}
