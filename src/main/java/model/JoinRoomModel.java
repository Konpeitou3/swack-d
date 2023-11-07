package model;

import java.util.List;

import bean.User;
import dao.JoinRoomDAO;
import exception.SwackException;

public class JoinRoomModel {
	public int joinRoom(String roomid, String userid) throws SwackException {
		return new JoinRoomDAO().JoinRoom(roomid, userid);
	}

	public List<User> getUserList(String roomid, String userid) throws SwackException {
		return new JoinRoomDAO().getUserList(roomid, userid);
	}

}