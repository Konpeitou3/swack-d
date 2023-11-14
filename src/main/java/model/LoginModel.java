package model;

import java.util.List;

import bean.FailedLog;
import bean.User;
import dao.FailedLogDAO;
import dao.UsersDAO;
import exception.SwackException;

/**
 * ログイン認証を実行するクラス
 */
public class LoginModel {

	/**
	 * ログイン認証を行う
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return ユーザ情報(ログインできなかった場合はnull)
	 */
	public User checkLogin(String mailAddress, String password) throws SwackException {
		UsersDAO usersDAO = new UsersDAO();
		User user = usersDAO.select(mailAddress, password);
		return user;
	}

	public List<FailedLog> lastLoginCheck(String userId) throws SwackException {
		FailedLogDAO failedLogDAO = new FailedLogDAO();
		return failedLogDAO.lastLoginCheck(userId);
	}

}
