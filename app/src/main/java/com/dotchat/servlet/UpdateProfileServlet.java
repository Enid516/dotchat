package com.dotchat.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProfileServlet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);


	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//从request中获取流信息
		InputStream inputStream = req.getInputStream();
		String tempFileName = "E:/tempFile";
		//tempFile 指定临时文件
		File tempFile = new File(tempFileName);
		//outputStream 文件输出流指向临时文件
		FileOutputStream outputStream = new FileOutputStream(tempFile);
		
		byte[] buffer = new byte[1024];
		int n;
		while((n = inputStream.read(buffer)) != -1){
			outputStream.write(buffer, 0, n);
		}
		
		//关闭输出输入流
		outputStream.close();
		inputStream.close();
		
		//获取上传文件的名称
		RandomAccessFile randomFile = new RandomAccessFile(tempFile, "r");
		randomFile.readLine();
		String str = randomFile.readLine();
		int beginIndex = str.lastIndexOf("\\") + 1;
		int endIndex = str.lastIndexOf("\"");
		String filename = str.substring(beginIndex, endIndex);
		System.out.println("filename: " + filename);
		
		//重新定位文件指针到文件头
		randomFile.seek(0);
		long startPosition = 0;
		int i = 1;
		//获取文件内容开始位置
		while((n = randomFile.readByte()) != -1 && i <= 4){//循环读取四行
			if(n == '\n'){
				startPosition = randomFile.getFilePointer();
				i++;
			}
		}
		//换行符已经读取完了,randomFile.getFilePointer()已经指向下一个字符，它指向的值比这里的startPosition大1，所以也可以不减一
		startPosition = randomFile.getFilePointer() - 1;
		System.out.println("startPosition: " + startPosition);
		
		//获取文件内容结束位置
		randomFile.seek(randomFile.length());
		long endPosition = randomFile.getFilePointer();
		int j = 1;
		while(endPosition >= 0 && j <= 2){
			endPosition--;
			randomFile.seek(endPosition);
			if (randomFile.readByte() == '\n') { 
				j++;
			}
		}
		endPosition = endPosition - 1;
		System.out.println("endPosition: " + endPosition);
		
		//设置保存上传文件的路径
		String realPath = getServletContext().getRealPath("/") + "images";
		File fileUpload = new File(realPath);
		System.out.println("realpath: " + realPath);
		if(!fileUpload.exists()){
			fileUpload.mkdir();
		}
		File saveFile = new File(realPath,filename);
		RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile, "rw");
		//从临时文件中读取文件内容（根据起始位置获取）
		randomFile.seek(startPosition);
		while (startPosition < endPosition) {
			randomAccessFile.write(randomFile.readByte());
			startPosition = randomFile.getFilePointer();
		}
		//关闭输入输出流，删除临时文件
		randomAccessFile.close();
		randomFile.close();
		tempFile.delete();
		//重新指定页面，返回结果
		resp.getWriter().write("上传成功");
	}
	
}
