package dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import bean.Room;
import context.SetUpDBConnectionPool;
import exception.SwackException;

public class RoomDAOTest {

	private static RoomDAO roomDAO;

	@BeforeAll
	static void setUpBeforeClass() throws SwackException {
		SetUpDBConnectionPool.setUp();

		roomDAO = new RoomDAO();
	}

	//インサートテスト
	@Test
	void testInsertT() throws SwackException {
		int userT = roomDAO.insert("testyou2", "U9997", false, false);
		System.out.println(userT);
		assertNotNull(userT);
	}

	//	@Test
	//	void testInsertF() throws SwackException {
	//		int userF = roomDAO.insert("testyou", "U9998", false, false);
	//		System.out.println(userF);
	//		assertNull(userF);
	//	}

	//自分が参加していないroomid,roomname表示テスト
	@Test
	void getOtherRoomList() throws SwackException {
		List<Room> userT = roomDAO.getOtherRoomList("U0001");
		System.out.println(userT);
		assertNotNull(userT);
	}

}
