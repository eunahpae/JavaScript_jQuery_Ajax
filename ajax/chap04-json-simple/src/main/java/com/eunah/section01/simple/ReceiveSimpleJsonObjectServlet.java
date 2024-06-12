package com.eunah.section01.simple;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;

import com.eunah.model.dto.MemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/receive/simple")
public class ReceiveSimpleJsonObjectServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDTO member = new MemberDTO("get", "squirrel", "nam", 200);
		
		JSONObject json = new JSONObject();
		json.put("type", member.getType());
		json.put("firstname", member.getFirstName());
		json.put("lastname", member.getLastName());
		json.put("height", member.getHeight());
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
//		out.print(member);
//		out.print(json.toJSONString());
		out.print(json);
		
		out.close();
		
	}

}