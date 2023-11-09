package model;

import java.util.List;

import bean.User;
import dao.UsersDAO;
import exception.SwackException;

public class UserModel {

	//ユーザー検索
	/**
	 * 
	 * @param mailAddress メールアドレス
	 * @param password	パスワード
	 * @return	
	 * @throws SwackException
	 */
	public User select(String mailAddress, String password) throws SwackException {
		return new UsersDAO().select(mailAddress, password);
	}

	//新規登録
	public int insert(String username, String mailAddress, String password) throws SwackException {
		return new UsersDAO().insert(username, mailAddress, password);
	}

	//自分以外のユーザーIDリスト
	public List<User> getUserList(String MyUserId) throws SwackException {
		return new UsersDAO().getUserList(MyUserId);
	}

	//登録済みメールアドレスリスト
	public List<User> getMailAddressList() throws SwackException {
		return new UsersDAO().getMailAddressList();
	}

	//アカウント削除
	public int delete(String userid) throws SwackException {
		return new UsersDAO().delete(userid);
	}

}
