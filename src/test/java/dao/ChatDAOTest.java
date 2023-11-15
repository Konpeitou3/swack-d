package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import bean.ChatLog;
import bean.Room;
import context.SetUpDBConnectionPool;
import exception.SwackException;

class ChatDAOTest {

	private static ChatDAO chatDAO;

	@BeforeAll
	static void setUpBeforeClass() throws SwackException {
		SetUpDBConnectionPool.setUp();

		chatDAO = new ChatDAO();
	}

	@Test
	void testGetChatlogListSuccess() throws SwackException {
		List<ChatLog> chatLogList = chatDAO.getChatlogList("R0000");

		for (ChatLog log : chatLogList) {
			System.out.println(log);
		}
		assertNotNull(chatLogList);
	}

	@Test
	void testGetRoom() throws SwackException {
		Room getRoom = chatDAO.getRoom("R0000", "U0001");
		System.out.println(getRoom);
		assertNotNull(getRoom);
	}

	@Test
	void testGetRoomList() throws SwackException {
		ArrayList<Room> getRoomList = chatDAO.getRoomList("U0001");
		System.out.println(getRoomList);
		assertNotNull(getRoomList);
	}

	@Test
	void testGetDirectList() throws SwackException {
		ArrayList<Room> getDirectList = chatDAO.getDirectList("U0001");
		System.out.println(getDirectList);
		assertNotNull(getDirectList);
	}

}
