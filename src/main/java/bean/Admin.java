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

	/**
	 * コンストラクタ
	 * @param roomId ルームID
	 * @param userId ユーザID
	 */
	public Admin(String roomId, String userId) {
		this.roomId = roomId;
		this.userId = userId;
	}

	// 確認出力用
	@Override
	public String toString() {
		return "Admin [ roomId=" + roomId + ", userId" + userId + "]";
	}

	/** ゲッター */
	public String getRoomId() {
		return roomId;
	}

	/** セッター */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/** ゲッター */
	public String getUserId() {
		return userId;
	}

	/** セッター */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
