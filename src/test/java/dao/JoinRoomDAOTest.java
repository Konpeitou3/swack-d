package dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import bean.User;
import context.SetUpDBConnectionPool;
import exception.SwackException;

public class JoinRoomDAOTest {

	private static JoinRoomDAO joinRoomDAO;

	@BeforeAll
	static void setUpBeforeClass() throws SwackException {
		SetUpDBConnectionPool.setUp();

		joinRoomDAO = new JoinRoomDAO();
	}

	@Test
	void testJoinRoomT() throws SwackException {
		int userT = joinRoomDAO.JoinRoom("R0006", "U0002");
		System.out.println(userT);
		assertNotNull(userT);
	}

	//	@Test
	//	void testJoinRoomF() throws SwackException {
	//		int userF = joinRoomDAO.JoinRoom("R0005", "U0001");
	//		System.out.println(userF);
	//		assertNull(userF);
	//	}

	@Test
	void getUserList() throws SwackException {
		List<User> userT = joinRoomDAO.getUserList("R0002", "U0004");
		System.out.println(userT);
		assertNotNull(userT);
	}

}
