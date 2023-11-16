package model;

import java.util.List;

import bean.User;
import dao.UsersDAO;
import exception.SwackException;

/**
 * ユーザに関する動作を実行するクラス
 */
public class UserModel {

	/**
	 * ユーザー検索
	 * @param mailAddress メールアドレス
	 * @param password	パスワード
	 * @return	成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public User select(String mailAddress, String password) throws SwackException {
		return new UsersDAO().select(mailAddress, password);
	}

	/**
	 * 新規登録
	 * @param username ユーザ名
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int insert(String username, String mailAddress, String password) throws SwackException {
		return new UsersDAO().insert(username, mailAddress, password);
	}

	/**
	 * 招待用名前リスト取得用
	 * @param MyuserId 自分のユーザID
	 * @return userList 招待用名前リスト
	 * @throws SwackException 独自エラー
	 */
	public List<User> getUserList(String MyUserId) throws SwackException {
		return new UsersDAO().getUserList(MyUserId);
	}

	/**
	 * 登録済みメールアドレスリスト
	 * @return AllMailAddressList 登録済みメールアドレスリスト
	 * @throws SwackException 独自エラー
	 */
	public List<User> getMailAddressList() throws SwackException {
		return new UsersDAO().getMailAddressList();
	}

	/**
	 * アカウント削除
	 * @param userid ユーザID
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int delete(String userid) throws SwackException {
		return new UsersDAO().delete(userid);
	}

	/**
	 * 既存ユーザ情報取得
	 * @return AllUsers 既存ユーザ情報
	 * @throws SwackException 独自エラー
	 */
	public List<User> getAllUserList() throws SwackException {
		return new UsersDAO().getAllUserList();
	}

	/**
	 * ユーザ情報取得
	 * @return AllUsersInfo ユーザ情報
	 * @throws SwackException 独自エラー
	 */
	public List<User> getUserinfoList() throws SwackException {
		return new UsersDAO().getUserinfoList();
	}

	/**
	 * パスワード変更
	 * @param password	パスワード
	 * @param mailAddress メールアドレス
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int updatePassword(String password, String mailAddress) throws SwackException {
		return new UsersDAO().updatePassword(password, mailAddress);
	}

	/**
	 * 最終ログイン時間更新
	 * @param userId ユーザID
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int updateLastLogin(String userId) throws SwackException {
		return new UsersDAO().updateLastLogin(userId);
	}

	/**
	 * アカウントロック設定
	 * @param userId ユーザID
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int updateLockedTrue(String userId) throws SwackException {
		return new UsersDAO().updateLockedTrue(userId);
	}

	/**
	 * アカウントロック解除
	 * @param userId ユーザID
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int updateLockedFalse(String userId) throws SwackException {
		return new UsersDAO().updateLockedFalse(userId);
	}

	/**
	 * アカウントロックされているユーザのリスト取得
	 * @param mailAddress メールアドレス
	 * @return lockedUserList アカウントロックされているユーザのリスト
	 * @throws SwackException 独自エラー
	 */
	public List<User> lockedUserList() throws SwackException {
		return new UsersDAO().lockedUserList();
	}

}
