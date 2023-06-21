package by.fpmibsu.ozi;

import by.fpmibsu.ozi.dao.*;
import by.fpmibsu.ozi.entity.User;
import by.fpmibsu.ozi.services.RegistrationPageService;

import java.sql.Date;


public class OziApplication {

	public static void main(String[] args) {
		ShowMessages();
	}

	public static void ShowMessages()
	{
		UserDao userDao = new UserDao();
		try {
			System.out.println(userDao.findById(19));
		} catch (DaoException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
