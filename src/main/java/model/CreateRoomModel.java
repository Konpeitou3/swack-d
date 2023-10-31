package model;

import java.util.List;

import bean.OtherUsers;
import dao.RoomDAO;
import dao.UsersDAO;
import exception.SwackException;

/**
 * 新規ルーム作成を行うModel
 * @author s20213108
 *
 */
public class CreateRoomModel {

	/**
	 * 招待用名前リスト取得用
	 * @param userId
	 * @return
	 * @throws SwackException
	 */
	public List<OtherUsers> getMember(String userId) throws SwackException {
		UsersDAO usersDAO = new UsersDAO();
		List<OtherUsers> userList = usersDAO.getUserList(userId);
		return userList;
	}

	/**
	 * 新規ルーム作成
	 * @param mailAddress
	 * @param password
	 * @return
	 * @throws SwackException
	 */

	public int createRoom(String roomname, String createduserid, Boolean directed, Boolean privated)
			throws SwackException {
		RoomDAO roomDAO = new RoomDAO();
		int result = roomDAO.insert(roomname, createduserid, directed, privated);
		return result;
	}

}
