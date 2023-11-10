package model;

import java.util.List;

import bean.Admin;
import dao.RoomAdminDAO;
import exception.SwackException;

public class RoomAdminModel {
	public int joinRoomAdmin(String roomid, String userid) throws SwackException {
		return new RoomAdminDAO().JoinRoomAdmin(roomid, userid);
	}

	public int LeavingTheRoomAdmin(String roomid, String userid) throws SwackException {
		return new RoomAdminDAO().LeavingTheRoomAdmin(roomid, userid);
	}

	public List<Admin> getRoomAdminList(String roomid, String userid) throws SwackException {
		System.out.println(roomid);
		System.out.println(userid);
		return new RoomAdminDAO().getRoomAdminList();
	}

}