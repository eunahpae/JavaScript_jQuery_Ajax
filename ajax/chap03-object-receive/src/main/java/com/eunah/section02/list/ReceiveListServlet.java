package com.eunah.section02.list;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.eunah.model.dto.MemberDTO;

@WebServlet("/receive/list")
public class ReceiveListServlet extends HttpServlet {

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
		
		System.out.println(memberList);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(memberList);
		out.close();
		
	}

}