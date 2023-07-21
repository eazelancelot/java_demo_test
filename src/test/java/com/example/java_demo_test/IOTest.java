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
			String str = "���Ѧ��\�Y egg!!";
			fos.write(str.getBytes());
			fos.close();
			System.out.println("�ɮ׼g�X���\!!");
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
			String str = "���Ѧ��\�Y egg!!";
			fos.write(str.getBytes());
//			fos.close();
			System.out.println("�ɮ׼g�X���\!!");
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
			String str = "���Ѧ��\�Y egg!!";
			fos.write(str.getBytes());
//			fos.close();
			System.out.println("�ɮ׼g�X���\!!");
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
			String str = "���Ѧ��\�Y egg!!\n";
			fos.write(str.getBytes());
			fos.write("70".getBytes());
			System.out.println("�ɮ׼g�X���\!!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// �ѨM����ýX
	@Test
	public void fileOutputStreamTest4() {
		try (FileOutputStream fos = new FileOutputStream("0705.txt", true);
				OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);){
			//�ϥ� OutputStreamWriter ���w�g�J���e���s�X(�ѨM����ýX)
			//���w�s�X�榡�i�� "UTF-8" �άO StandardCharsets.UTF_8
			//OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF_8");			
			String str = "���Ѧ��\�Y egg!!\n";
			osw.append(str);//�N���e�l�[���媺�᭱
			System.out.println("�ɮ׼g�X���\!!");
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
			//�C��Ū���h�� bytes
			byte[] buffer = new byte[512];
			while(fis.available() > 0) {
				fis.read(buffer); // �q fis Ū�� 512 bytes �æs�J�� buffer ���줸�}�C��
				fos.write(buffer);// �N buffer �}�C�����줸�g�J�� fos
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
			String str = "���Ѧ��\�Y eggssss!!";
			bos.write(str.getBytes());
			bos.flush();
			System.out.println("�ɮ׼g�X���\!!");
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
			//�C��Ū���h�� bytes
			byte[] buffer = new byte[512];
			while(bis.available() > 0) {
				bis.read(buffer); // �q�w�İ� bis Ū�� 512 bytes �æs�J�� buffer ���줸�}�C��
				bos.write(buffer);// �N buffer �}�C�����줸�g�J��w�İ� bos ��
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
