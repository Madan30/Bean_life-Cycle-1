package com.NepalCode.BeanLife;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LBEFTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	/*	LBEFCollegeDao collegeDao = new LBEFCollegeDao();
		collegeDao.selectAllRows();
		collegeDao.deleteRecord(1);
		
		*/
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		LBEFCollegeDao lbefcollege = context.getBean("lbefcollegeDao", LBEFCollegeDao.class);
		lbefcollege.selectAllRows();
		((ClassPathXmlApplicationContext) context).close();

	}

}
