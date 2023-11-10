package model;

import java.util.List;

import bean.Room;
import dao.RoomDAO;
import exception.SwackException;

/**
 * 参加ルームを取得するmodel
 *
 */
public class OtherRoomListModel {
	/**
	 * 参加しているルーム一覧取得
	 * @param userid ユーザID
	 * @return
	 * @throws SwackException
	 */
	public List<Room> getOtherRoomList(String userid) throws SwackException {
		return new RoomDAO().getOtherRoomList(userid);
	}

}
