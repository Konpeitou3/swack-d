package model;

import java.util.List;

import bean.User;
import dao.JoinRoomDAO;
import exception.SwackException;

/**
 * ルームメンバーの操作に関するクラス
 */
public class JoinRoomModel {
	/**
	 * ルーム参加
	 * @param roomid ルームID
	 * @param userid ユーザID
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int joinRoom(String roomid, String userid) throws SwackException {
		JoinRoomDAO joinRoomDAO = new JoinRoomDAO();
		return joinRoomDAO.JoinRoom(roomid, userid);
	}

	/**
	 * ルーム退出
	 * @param roomid ルームID
	 * @param userid ユーザID
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int LeavingTheRoom(String roomid, String userid) throws SwackException {
		JoinRoomDAO joinRoomDAO = new JoinRoomDAO();
		return joinRoomDAO.LeavingTheRoom(roomid, userid);

	}

	/**
	 * 招待用ユーザーネームリスト取得
	 * @param roomid ルームID
	 * @param userid ユーザID
	 * @return OtherUsers 招待用ユーザーネームリスト
	 * @throws SwackException 独自エラー
	 */
	public List<User> getUserList(String roomid, String userid) throws SwackException {
		JoinRoomDAO joinRoomDAO = new JoinRoomDAO();
		System.out.println(roomid);
		System.out.println(userid);
		return joinRoomDAO.getUserList(roomid, userid);
	}

	/**
	 * 管理者権限未保有ルームメンバーリスト出力
	 * @param roomid ルームID
	 * @return NotAdminUsers 管理者権限未保有ルームメンバーリスト
	 * @throws SwackException 独自エラー
	 */
	public List<User> getNotAdminUserList(String roomid) throws SwackException {
		JoinRoomDAO joinRoomDAO = new JoinRoomDAO();
		return joinRoomDAO.getNotAdminUserList(roomid);

	}

}