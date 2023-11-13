package bean;

import java.io.Serializable;

/**
 * 管理者権限情報を管理するBean
 */
public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ルームID */
	private String roomId;
	/** ユーザID */
	private String userId;

	public Admin() {
		// for JSP
	}

	public Admin(String roomId, String userId) {
		this.roomId = roomId;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Admin [ roomId=" + roomId + ", userId" + userId + "]";
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
