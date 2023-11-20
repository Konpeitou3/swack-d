package bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ユーザ情報を管理するBean
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ユーザID */
	private String userId;
	/** ユーザ名 */
	private String userName;
	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;
	/** 最終ログイン日時 */
	private Timestamp lastloginAt;
	/** アカウントロック判断 */
	private boolean locked;

	public User() {
		// for JSP
	}

	/**
	 * リファクタリング
	 * @param userId ユーザID
	 * @param userName ユーザ名
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @param lastloginAt 最終ログイン日時
	 * @param locked アカウントロック判断
	 */
	public User(String userId, String userName, String mailAddress, String password, Timestamp lastloginAt,
			Boolean locked) {
		this.userId = userId;
		this.userName = userName;
		this.mailAddress = mailAddress;
		this.password = password;
		this.lastloginAt = lastloginAt;
		this.locked = locked;
	}

	/**
	 * リファクタリング
	 * @param userId ユーザID
	 * @param userName ユーザ名
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 */
	public User(String userId, String userName, String mailAddress, String password) {
		this.userId = userId;
		this.userName = userName;
		this.mailAddress = mailAddress;
		this.password = password;
	}

	/**
	 * リファクタリング
	 * @param userId ユーザID
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 */
	public User(String userId, String mailAddress, String password) {
		this.userId = userId;
		this.mailAddress = mailAddress;
		this.password = password;
	}

	/**
	 * リファクタリング
	 * @param userId ユーザID
	 * @param userName ユーザ名
	 * @param lastloginAt 最終ログイン日時
	 * @param locked アカウントロック判断
	 */
	public User(String userId, String userName, Timestamp lastloginAt, Boolean locked) {
		this.userId = userId;
		this.userName = userName;
		this.lastloginAt = lastloginAt;
		this.locked = locked;
	}

	/**
	 * リファクタリング
	 * @param userId ユーザID
	 * @param userName ユーザ名
	 */
	public User(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	/**
	 * リファクタリング
	 * @param mailAddress メールアドレス
	 */
	public User(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * リファクタリング
	 * @param userId ユーザID
	 * @param locked アカウントロック判断
	 */
	public User(String userId, Boolean locked) {
		this.userId = userId;
		this.locked = locked;
	}

	/**
	 * リファクタリング
	 * @param userName ユーザ名
	 */
	public void OtherUsers(String userName) {
		this.userName = userName;
	}

	/**
	 * リファクタリング
	 * @param userId ユーザID
	 */
	public void AllUsersId(String userId) {
		this.userId = userId;
	}

	/** ゲッター */
	public String getUserId() {
		return userId;
	}

	/** ゲッター */
	public String getUserName() {
		return userName;
	}

	/** ゲッター */
	public String getMailAddress() {
		return mailAddress;
	}

	/** ゲッター */
	public String getPassword() {
		return password;
	}

	/** ゲッター */
	public Timestamp getLastloginAt() {
		return lastloginAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/** ゲッター */
	public boolean isLocked() {
		return locked;
	}

	//結果確認用
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", mailAddress=" + mailAddress + ", password="
				+ password + ", lastloginAt=" + lastloginAt + ", locked=" + locked + "]";
	}

}

//public class AllUsersIdList implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	private String userId;
//
//	public void AllUsersId(String userId) {
//		this.userId = userId;
//	}
//}
