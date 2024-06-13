package com.eunah.fileio.section01.commonsio;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import jakarta.servlet.http.HttpServletRequest;

public class CommonsFileUploadModule {

	public int saveFile(HttpServletRequest request, String rootLocation, int maxFileSize, String encodingType) {

		int result = 0;

		String fileUploadDirectory = rootLocation + "/commons";

		File directory = new File(fileUploadDirectory);

		/* exists() : 해당 경로가 존재하는지 확인 (존재하면 true, 아니면 false) */
		if (!directory.exists()) {

			/* mkdirs() : 상위 폴더를 포함한 모든 경로를 한번에 생성 */
			/* (참고) mkdir() : 해당하는 폴더 한 개만 생성 */
			System.out.println("폴더 생성 여부 : " + directory.mkdirs());			
		}

		/* request의 파일 데이터 정보를 담을 List와 그외 폼데이터 정보를 담을 Map 생성 */
		Map<String, String> parameter = new HashMap<>();
		List<Map<String, String>> fileList = new ArrayList<>();

		/* 파일 업로드 경로 및 최대 크기 등을 지정하기 위한 인스턴스 생성 */
		DiskFileItemFactory.Builder fileItemFactory = new DiskFileItemFactory.Builder();
		fileItemFactory.setPath(new File(fileUploadDirectory).getPath());
		fileItemFactory.setBufferSize(maxFileSize);

		JakartaServletFileUpload fileUpload = new JakartaServletFileUpload(fileItemFactory.get());

		try {

			/* request를 파싱하여 데이터 하나 하나를 FileItem 인스턴스로 반환 -> List 반환 */
			List<FileItem> fileItems = fileUpload.parseRequest(request);

			for (FileItem item : fileItems) {
				/* 파일 데이터는 isFormField 속성이 false, 그외 폼 데이터는 true */
				System.out.println(item);
			}

			for (int i = 0; i < fileItems.size(); i++) {
				FileItem item = fileItems.get(i);

				if (!item.isFormField()) {
					/* 파일 데이터의 경우 */

					if (item.getSize() > 0) {
						/*
						 * 파일의 사이즈 0보다 커야 전송된 파일이 있다는 의미이므로, 전송된 파일이 있는 경우에만 처리하도록 로직 작성
						 */

						String fieldName = item.getFieldName();
						String originFileName = item.getName();

						int dot = originFileName.lastIndexOf(".");
						String ext = originFileName.substring(dot);

						String randomFileName = UUID.randomUUID().toString().replace("-", "") + ext;

						/* 저장할 파일 정보를 담은 인스턴스 생성 */
						File storeFile = new File(fileUploadDirectory + "/" + randomFileName);

						/* 파일 저장 */
						item.write(storeFile.toPath());

						/* DB에 저장할 정보를 Map에 담음 */
						Map<String, String> fileMap = new HashMap<>();
						fileMap.put("fieldName", fieldName);
						fileMap.put("originFileName", originFileName);
						fileMap.put("savedFileName", randomFileName);
						fileMap.put("savePath", fileUploadDirectory);

						fileList.add(fileMap);

						result++;
					}

				} else {
					/* 폼 데이터인 경우 */

//                    System.out.println(item.getString());

					/*
					 * 전송된 폼의 name은 getFiledName()으로, 해당 필드의 value는 getString()으로 받음 하지만 인코딩 설정을
					 * 하더라도 전송되는 파라미터는 ISO-8859-1로 처리되므로, ISO-8859-1 방식으로 해석된 한글을 UTF-8 방식으로 변경해 깨지지
					 * 않도록 해야 함
					 */
					parameter.put(item.getFieldName(),
							new String(item.getString().getBytes("ISO-8859-1"), encodingType));
				}
			}

			System.out.println("parameter : " + parameter);
			System.out.println("fileList : " + fileList);

		} catch (Exception e) {

			/* 어떤 종류의 Exception이 발생하든 실패 시 파일 삭제 */

			int cnt = 0;
			for (int i = 0; i < fileList.size(); i++) {
				Map<String, String> file = fileList.get(i);

				File deleteFile = new File(fileUploadDirectory + "/" + file.get("savedFileName"));
				boolean isDeleted = deleteFile.delete();

				if (isDeleted) {
					cnt++;
				}

				if (cnt == fileList.size()) {
					System.out.println("업로드에 실패한 모든 사진 삭제 완료!");
				} else {
					e.printStackTrace();
				}
			}
			result = 0;
		}
		return result;
	}
}
