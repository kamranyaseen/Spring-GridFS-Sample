package com.GridFsExample.core;

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

import com.mongodb.gridfs.GridFSDBFile;

public class GridFsAppRead {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
		GridFsOperations gridOperations = (GridFsOperations) ctx.getBean("gridFsTemplate");

		List<GridFSDBFile> result = gridOperations.find(new Query().addCriteria(Criteria.where(
				"filename").is("1.png")));
		for (GridFSDBFile file : result) {
			try {
				System.out.println(file.getFilename());
				System.out.println(file.getContentType());
				file.writeTo("/Users/Administrator/Downloads/1.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Done");

	}

}
