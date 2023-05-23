package com.BirthdayTracker;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.BirthdayTracker.Dao.IUserDao;
import com.BirthdayTracker.Entities.User;

@SpringBootApplication 

public class BirthdayTrackerApplication {

	public static void main(String[] args) {
 	SpringApplication.run(BirthdayTrackerApplication.class, args);
		//IUserDao userdao = context.getBean(IUserDao.class);
		
		
		//Creating ob of user
//		User u=new User(1, "Nehal","31/01/2000" , "Jalgaon", "nehalchaudhari1999@gmail.com", "nehal_chaudhari31",
//				"Nehal Chaudhari"); 
//		userdao.save(u);
	}

}
