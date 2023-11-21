package model;

import java.util.List;

import bean.User;
import dao.RoomDAO;
import dao.UsersDAO;
import exception.SwackException;

/**
 * 新規ルーム作成を行うModel
 */
public class CreateRoomModel {

	/**
	 * 招待用名前リスト取得用
	 * @param userId ユーザID
	 * @return userList 招待用名前リスト
	 * @throws SwackException 独自エラー
	 */
	public List<User> getMember(String userId) throws SwackException {
		UsersDAO usersDAO = new UsersDAO();
		List<User> userList = usersDAO.getUserList(userId);
		return userList;
	}

	/**
	 * 新規ルーム作成
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int createRoom(String roomname, String createduserid, Boolean directed, Boolean privated)
			throws SwackException {
		RoomDAO roomDAO = new RoomDAO();
		int result = roomDAO.insert(roomname, createduserid, directed, privated);
		return result;
	}

	/**
	 * 最後に登録されたルームIDを取得
	 * @return result ルームID
	 * @throws SwackException
	 */
	public String maxRoomSelect()
			throws SwackException {
		RoomDAO roomDAO = new RoomDAO();
		String result = roomDAO.maxRoomSelect();
		return result;
	}

}
