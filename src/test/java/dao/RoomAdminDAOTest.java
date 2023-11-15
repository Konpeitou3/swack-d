package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import bean.Admin;
import context.SetUpDBConnectionPool;
import exception.SwackException;

class RoomAdminDAOTest {

	private static RoomAdminDAO roomAdminDAO;

	@BeforeAll
	static void setUpBeforeClass() throws SwackException {
		SetUpDBConnectionPool.setUp();

		roomAdminDAO = new RoomAdminDAO();
	}

	@Test
	void testJoinAdminRoomT() throws SwackException {
		int userT = roomAdminDAO.JoinRoomAdmin("R0002", "U0002");
		System.out.println(userT);
		assertNotNull(userT);
	}

	//	@Test
	//	void testJoinRoomF() throws SwackException {
	//		int userF = roomAdminDAO.JoinRoom("R0002", "U0001");
	//		System.out.println(userF);
	//		assertNull(userF);
	//	}

	@Test
	void testGetRoomAdminList() throws SwackException {
		List<Admin> adminT = roomAdminDAO.getRoomAdminList();
		System.out.println(adminT);
		assertNotNull(adminT);
	}

}
