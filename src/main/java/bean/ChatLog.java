package bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * チャットログ情報を管理するBean
 */
public class ChatLog implements Serializable {
	private static final long serialVersionUID = 1L;

	/** チャットログID */
	private int chatLogId;
	/** ルームID */
	private String roomId;
	/** ユーザID */
	private String userId;
	/** ユーザ名 */
	private String userName;
	/** メッセージ */
	private String message;
	/** 投稿日時 */
	private Timestamp createdAt;

	public ChatLog() {
		// for JSP
	}

	/**
	 * コンストラクタ
	 * @param chatLogId チャットログID
	 * @param roomId ルームID
	 * @param userId ユーザID
	 * @param userName ユーザ名
	 * @param message メッセージ
	 * @param createdAt 投稿日時
	 */
	public ChatLog(int chatLogId, String roomId, String userId, String userName, String message, Timestamp createdAt) {
		this.chatLogId = chatLogId;
		this.roomId = roomId;
		this.userId = userId;
		this.userName = userName;
		this.message = message;
		this.createdAt = createdAt;
	}

	/** ゲッター */
	public int getChatLogId() {
		return chatLogId;
	}

	/** ゲッター */
	public String getRoomId() {
		return roomId;
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
	public String getMessage() {
		return message;
	}

	/** ゲッター */
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	// 結果出力用
	@Override
	public String toString() {
		return "ChatLog [chatLogId=" + chatLogId + ", roomId=" + roomId + ", userId=" + userId + ", userName="
				+ userName + ", message=" + message + ", createdAt=" + createdAt + "]";
	}

}
