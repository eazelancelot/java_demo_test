package com.example.java_demo_test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

public class IOTest {
	
	@Test
	public void fileOutputStreamTest() {
		try {
			FileOutputStream fos = new FileOutputStream("0705.txt");
			String str = "今天早餐吃 egg!!";
			fos.write(str.getBytes());
			fos.close();
			System.out.println("檔案寫出成功!!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// try-with-resource
	@Test
	public void fileOutputStreamTest1() {
		try(FileOutputStream fos = new FileOutputStream("0705.txt")) {			
			String str = "今天早餐吃 egg!!";
			fos.write(str.getBytes());
//			fos.close();
			System.out.println("檔案寫出成功!!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void fileOutputStreamTest2() {
		try(FileOutputStream fos = new FileOutputStream("D:\\java_project\\0705.txt")) {			
			String str = "今天早餐吃 egg!!";
			fos.write(str.getBytes());
//			fos.close();
			System.out.println("檔案寫出成功!!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void fileOutputStreamTest3() {
		try(FileOutputStream fos = new FileOutputStream("0705.txt", true)) {
			String str = "今天早餐吃 egg!!\n";
			fos.write(str.getBytes());
			fos.write("70".getBytes());
			System.out.println("檔案寫出成功!!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 解決中文亂碼
	@Test
	public void fileOutputStreamTest4() {
		try (FileOutputStream fos = new FileOutputStream("0705.txt", true);
				OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);){
			//使用 OutputStreamWriter 指定寫入內容的編碼(解決中文亂碼)
			//指定編碼格式可用 "UTF-8" 或是 StandardCharsets.UTF_8
			//OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF_8");			
			String str = "今天早餐吃 egg!!\n";
			osw.append(str);//將內容追加到原文的後面
			System.out.println("檔案寫出成功!!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void fileInputStreamTest() {
		try(FileInputStream fis = new FileInputStream("D:\\ABC.jpg");
				FileOutputStream fos = new FileOutputStream("AAA.jpg")){
			//每次讀取多少 bytes
			byte[] buffer = new byte[512];
			while(fis.available() > 0) {
				fis.read(buffer); // 從 fis 讀取 512 bytes 並存入到 buffer 此位元陣列中
				fos.write(buffer);// 將 buffer 陣列中的位元寫入到 fos
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Test
	public void bufferedOutputStreamTest() {
		try(FileOutputStream fos = new FileOutputStream("D:\\java_project\\0705.txt");
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {			
			String str = "今天早餐吃 eggssss!!";
			bos.write(str.getBytes());
			bos.flush();
			System.out.println("檔案寫出成功!!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void bufferInputStreamTest() {
		try(FileInputStream fis = new FileInputStream("D:\\ABC.jpg");
				BufferedInputStream bis = new BufferedInputStream(fis);
				FileOutputStream fos = new FileOutputStream("AAA.jpg");
				BufferedOutputStream bos = new BufferedOutputStream(fos)){
			//每次讀取多少 bytes
			byte[] buffer = new byte[512];
			while(bis.available() > 0) {
				bis.read(buffer); // 從緩衝區 bis 讀取 512 bytes 並存入到 buffer 此位元陣列中
				bos.write(buffer);// 將 buffer 陣列中的位元寫入到緩衝區 bos 中
				bos.flush();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
