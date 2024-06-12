package com.eunah.secsion02.array;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.eunah.model.dto.MemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/receive/jsonarray")
public class ReceiveJsonArrayServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MemberDTO member1 = new MemberDTO("get", "squirrel", "nam", 200);
		MemberDTO member2 = new MemberDTO("get", "pig", "jung", 300);
		MemberDTO member3 = new MemberDTO("get", "tiger", "kim", 500);
		MemberDTO member4 = new MemberDTO("get", "sheep", "woo", 170);
		
		List<MemberDTO> memberList = new ArrayList<>();
		memberList.add(member1);
		memberList.add(member2);
		memberList.add(member3);
		memberList.add(member4);
		
		JSONArray jsonArray = new JSONArray();
		for(MemberDTO member : memberList) {
			JSONObject json = new JSONObject();
			json.put("type", member.getType());
			json.put("firstname", member.getFirstName());
			json.put("lastname", member.getLastName());
			json.put("height", member.getHeight());
			
			jsonArray.add(json);
		}
		
		System.out.println(memberList);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toJSONString());
		out.close();
		
	}

}