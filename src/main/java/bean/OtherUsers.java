package bean;

import java.io.Serializable;

/**
 * ユーザ情報を管理するBean
 */
public class OtherUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ユーザ名 */
	private String userName;
	/** ユーザID */
	private String userId;

	public OtherUsers() {
		// for JSP
	}

	public OtherUsers(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public String toString() {
		return "User [ userName=" + userName + "]";
	}

}
