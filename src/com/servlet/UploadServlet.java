package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.util.IOutil;



public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			//1.��������
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(100*1024);
			factory.setRepository(new File(this.getServletContext().getRealPath("WEB-INF/temp")));
			//2.�����ļ��ϴ�������
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			
			//--����Ƿ�����ȷ�ı�����
			if(!fileUpload.isMultipartContent(request))
				throw new RuntimeException("��ʹ����ȷ�ı������ϴ�");
			//--�����ļ��ϴ��Ĵ�С����
			fileUpload.setFileSizeMax(1024*1024*10);//�����ļ�������10M
			fileUpload.setSizeMax(1024*1024*100);//���ļ�������100M
			//--���ñ��뼯������ϴ��ļ�������������
			fileUpload.setHeaderEncoding("utf-8");
			fileUpload.setProgressListener(new ProgressListener(){
				Long beginTime = System.currentTimeMillis();
				public void update(long bytesRead, long contentLength, int items) {
					BigDecimal br = new BigDecimal(bytesRead).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP);
					BigDecimal cl = new BigDecimal(contentLength).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP);
					System.out.print("��ǰ��ȡ���ǵ�"+items+"���ϴ���,�ܴ�С"+cl+"KB,�Ѿ���ȡ"+br+"KB");
					//ʣ���ֽ���
					BigDecimal ll = cl.subtract(br);
					System.out.print("ʣ��"+ll+"KB");
					//�ϴ��ٷֱ�
					BigDecimal per = br.multiply(new BigDecimal(100)).divide(cl,2,BigDecimal.ROUND_HALF_UP);
					System.out.print("�Ѿ����"+per+"%");
					//�ϴ���ʱ
					Long nowTime = System.currentTimeMillis();
					Long useTime = (nowTime - beginTime)/1000;
					System.out.print("�Ѿ���ʱ"+useTime+"��");
					//�ϴ��ٶ�
					BigDecimal speed = new BigDecimal(0);
					if(useTime!=0){
						speed = br.divide(new BigDecimal(useTime),2,BigDecimal.ROUND_HALF_UP);
					}
					System.out.print("�ϴ��ٶ�Ϊ"+speed+"KB/S");
					//����ʣ��ʱ��
					BigDecimal ltime = new BigDecimal(0);
					if(!speed.equals(new BigDecimal(0))){
						ltime = ll.divide(speed,0,BigDecimal.ROUND_HALF_UP);
					}
					System.out.print("����ʣ��ʱ��Ϊ"+ltime+"��");
					
					System.out.println();
				}
				}
				
			);
			//3.�����ļ��ϴ����������request
			List<FileItem> list = fileUpload.parseRequest(request);
			//4.�������е�FileItem
			for(FileItem item : list)
				if(item.isFormField()){
					//��ǰ�Ǹ���ͨ�ֶ�
					String name =item.getFieldName();
					String value=item.getString();
					System.out.println(name+":"+value);
				}else{
					//��ǰ��һ���ļ��ϴ���
					String filename=item.getName();
					String uuidName = UUID.randomUUID().toString()+"_"+filename;
					
					int hs = uuidName.hashCode();
					String hashstr = Integer.toHexString(hs);
					char hss[]=hashstr.toCharArray();
					String path = this.getServletContext().getRealPath("WEB-INF/upload");
					for(char c : hss)
						path+="/"+hss;
					new File(path).mkdirs();
					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(path+uuidName);
				    IOutil.IntoOut(in, out);
				    IOutil.close(in, out);
				    
				    //ɾ����ʱ�ļ�
				    item.delete();
				}
			}
			catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);}
			}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
