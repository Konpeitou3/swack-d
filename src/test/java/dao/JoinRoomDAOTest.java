package dao;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
		int userT = joinRoomDAO.JoinRoom("R0005", "U0001");
		System.out.println(userT);
		assertNotNull(userT);
	}

	//	@Test
	//	void testJoinRoomF() throws SwackException {
	//		int userF = joinRoomDAO.JoinRoom("R0005", "U0001");
	//		System.out.println(userF);
	//		assertNull(userF);
	//	}

}
