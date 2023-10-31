package model;

import dao.JoinRoomDAO;
import exception.SwackException;

public class JoinRoomModel {
	public int joinRoom(String roomid, String userid) throws SwackException {
		return new JoinRoomDAO().JoinRoom(roomid, userid);
	}
}