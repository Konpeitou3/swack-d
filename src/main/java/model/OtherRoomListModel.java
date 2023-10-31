package model;

import java.util.List;

import bean.Room;
import dao.RoomDAO;
import exception.SwackException;

public class OtherRoomListModel {
	public List<Room> getOtherRoomList(String userid) throws SwackException {
		return new RoomDAO().getOtherRoomList(userid);
	}

}
