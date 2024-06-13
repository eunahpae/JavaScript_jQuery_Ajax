package com.eunah.fileio.section01.commonsio;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/commons")
public class CommonsFileUploadServlet extends HttpServlet {

	private String rootLocation;
	private int maxFileSize;
	private String encodingType;

	@Override
	public void init() throws ServletException {
		ServletContext context = getServletContext();

		rootLocation = context.getInitParameter("upload-location");
		maxFileSize = Integer.parseInt(context.getInitParameter("max-file-size"));
		encodingType = context.getInitParameter("encoding-type");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int resultFg = 0;
		
		System.out.println(JakartaServletFileUpload.isMultipartContent(request));

		// multipart/form-data 요청인지 확인
		if (JakartaServletFileUpload.isMultipartContent(request)) {
			resultFg = new CommonsFileUploadModule().saveFile(request, rootLocation, maxFileSize, encodingType);
		}

		String responseMsg = "";
		if (resultFg > 0) {
			responseMsg = "파일 등록에 성공하였습니다 :) ";

		} else {
			responseMsg = "파일 등록에 실패하였습니다 :( ";
		}

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1>" + responseMsg + "</h1>");
		out.print("</body>");
		out.print("</html>");

		out.close();
	}
}
