package model;

import java.util.List;

import bean.User;
import dao.UsersDAO;
import exception.SwackException;

public class UserModel {

	//ユーザー検索
	public User select(String mailAddress, String password) throws SwackException {
		return new UsersDAO().select(mailAddress, password);
	}

	//新規登録
	public int insert(String username, String mailAddress, String password) throws SwackException {
		return new UsersDAO().insert(username, mailAddress, password);
	}

	//
	public List<User> getUserList(String MyUserId) throws SwackException {
		return new UsersDAO().getUserList(MyUserId);
	}

	public List<User> getMailAddressList() throws SwackException {
		return new UsersDAO().getMailAddressList();
	}

}
