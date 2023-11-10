package model;

import java.util.List;

import bean.User;
import dao.JoinRoomDAO;
import exception.SwackException;

/**
 * ルームメンバーの操作を行うmodel
 *
 */
public class JoinRoomModel {
	/**
	 * ルーム参加
	 * @param roomid ルームID
	 * @param userid ユーザID
	 * @return
	 * @throws SwackException
	 */
	public int joinRoom(String roomid, String userid) throws SwackException {
		JoinRoomDAO joinRoomDAO = new JoinRoomDAO();
		return joinRoomDAO.JoinRoom(roomid, userid);
	}

	/**
	 * ルーム退出
	 * @param roomid ルームID
	 * @param userid ユーザID
	 * @return
	 * @throws SwackException
	 */
	public int LeavingTheRoom(String roomid, String userid) throws SwackException {
		JoinRoomDAO joinRoomDAO = new JoinRoomDAO();
		return joinRoomDAO.LeavingTheRoom(roomid, userid);

	}

	/**
	 * 未参加ユーザ取得
	 * @param roomid ルームID
	 * @param userid ユーザID
	 * @return
	 * @throws SwackException
	 */
	public List<User> getUserList(String roomid, String userid) throws SwackException {
		JoinRoomDAO joinRoomDAO = new JoinRoomDAO();
		System.out.println(roomid);
		System.out.println(userid);
		return joinRoomDAO.getUserList(roomid, userid);
	}

	/**
	 * 
	 * @param roomid ルームID
	 * @return
	 * @throws SwackException
	 */
	public List<User> getNotAdminUserList(String roomid) throws SwackException {
		JoinRoomDAO joinRoomDAO = new JoinRoomDAO();
		return joinRoomDAO.getNotAdminUserList(roomid);

	}

}