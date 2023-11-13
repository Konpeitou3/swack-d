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

	public User(String userId, String userName, String mailAddress, String password, Timestamp lastloginAt,
			Boolean locked) {
		this.userId = userId;
		this.userName = userName;
		this.mailAddress = mailAddress;
		this.password = password;
		this.lastloginAt = lastloginAt;
		this.locked = locked;
	}

	public User(String userId, String userName, String mailAddress, String password) {
		this.userId = userId;
		this.userName = userName;
		this.mailAddress = mailAddress;
		this.password = password;
	}

	public User(String userId, String mailAddress, String password) {
		this.userId = userId;
		this.mailAddress = mailAddress;
		this.password = password;
	}

	public User(String userId, Timestamp lastloginAt, Boolean locked) {
		this.userId = userId;
		this.lastloginAt = lastloginAt;
		this.locked = locked;
	}

	public User(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	public User(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public void OtherUsers(String userName) {
		this.userName = userName;
	}

	public void AllUsersId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public Timestamp getLastloginAt() {
		return lastloginAt;
	}

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
