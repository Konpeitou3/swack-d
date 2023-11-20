
package bean;

import java.io.Serializable;

/**
 * ルーム情報を管理するBean
 */
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ルームID */
	private String roomId;
	/** ルーム名 */
	private String roomName;
	/** 参加メンバー数 */
	private int memberCount;
	/** ダイレクトチャットか */
	private boolean directed;
	/** プライベートチャットか */
	private boolean privated;

	/**
	 * リファクタリング
	 * @param roomId ルームID
	 * @param roomName ルーム名
	 * @param privated プライベートチャットか
	 */
	public Room(String roomId, String roomName, Boolean privated) {
		this.roomId = roomId;
		this.roomName = roomName;
		this.privated = privated;
	}

	/**
	 * リファクタリング
	 * @param roomId ルームID
	 * @param roomName ルーム名
	 * @param memberCount 参加メンバー数
	 * @param directed ダイレクトチャットか
	 */
	public Room(String roomId, String roomName, int memberCount, boolean directed) {
		this.roomId = roomId;
		this.roomName = roomName;
		this.memberCount = memberCount;
		this.directed = directed;
	}

	/**
	 * リファクタリング
	 * @param roomName ルーム名
	 */
	public Room(String roomName) {
		this.roomName = roomName;
	}

	// 一覧表示用(参加メンバー数0名,ダイレクトはfalseで固定)
	public Room(String roomId, String roomName) {
		this(roomId, roomName, 0, false);
	}

	/** ゲッター */
	public String getRoomId() {
		return roomId;
	}

	/** ゲッター */
	public String getRoomName() {
		return roomName;
	}

	/** ゲッター */
	public int getMemberCount() {
		return memberCount;
	}

	/** ゲッター */
	public boolean isDirected() {
		return directed;
	}

	/** ゲッター */
	public boolean isPrivated() {
		return privated;
	}

}
