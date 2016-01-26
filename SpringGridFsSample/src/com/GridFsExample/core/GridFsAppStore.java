package com.GridFsExample.core;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

import com.GridFsExample.config.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class GridFsAppStore {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
				
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		GridFsOperations gridOperations = (GridFsOperations) ctx.getBean("gridFsTemplate");
		
		DBObject metaData = new BasicDBObject();
		metaData.put("extra1", "anything 1");
		metaData.put("extra2", "anything 2");

		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("C:\\logo_en.png");
			gridOperations.store(inputStream, "logo_en.png", "image/png", metaData);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");

	}

}
