package model;

import bean.Admin;
import dao.RoomAdminDAO;
import exception.SwackException;

/**
 * 管理者権限を操作するクラス
 */
public class RoomAdminModel {
	/**
	 * 管理者権限を付与する
	 * @param roomid ルームID
	 * @param userid ユーザID
	 * @return 
	 */
	public int joinRoomAdmin(String roomid, String userid) throws SwackException {
		return new RoomAdminDAO().JoinRoomAdmin(roomid, userid);
	}

	/**
	 * 管理者権限を削除する
	 * @param roomid ルームID
	 * @param userid ユーザID
	 * @return
	 */
	public int LeavingTheRoomAdmin(String roomid, String userid) throws SwackException {
		return new RoomAdminDAO().LeavingTheRoomAdmin(roomid, userid);
	}

	/**
	 * 管理者権限情報を取得する
	 * @param roomid ルームID
	 * @param userid ユーザID
	 * @return 管理者権限情報
	 */
	public Admin getRoomAdmin(String roomid, String userid) throws SwackException {
		System.out.println(roomid);
		System.out.println(userid);
		return new RoomAdminDAO().getRoomAdmin(roomid, userid);
	}

}