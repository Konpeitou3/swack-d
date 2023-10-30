package model;

import java.util.List;

import bean.OtherUsers;
import bean.User;
import dao.UsersDAO;
import exception.SwackException;

public class UserModel {

	public User select(String mailAddress, String password) throws SwackException {
		return new UsersDAO().select(mailAddress, password);
	}

	public int insert(String username, String mailAddress, String password) throws SwackException {
		return new UsersDAO().insert(username, mailAddress, password);
	}

	public List<OtherUsers> getUserList(String MyUserId) throws SwackException {
		return new UsersDAO().getUserList(MyUserId);
	}

	public List<User> getMailAddressList() throws SwackException {
		return new UsersDAO().getMailAddressList();
	}

}
