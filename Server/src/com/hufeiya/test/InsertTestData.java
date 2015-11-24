package com.hufeiya.test;
import java.util.Set;

import com.hufeiya.entity.*;
import com.hufeiya.DAO.*;
public class InsertTestData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user  = new User("hufeiya", "4242", "201226630205", "18258254022",true, null,null);
		UserDAO userDao = new UserDAO();
		System.out.print(userDao.findById(1));
	}

}
