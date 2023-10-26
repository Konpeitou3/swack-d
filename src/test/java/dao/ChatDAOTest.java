package dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import bean.ChatLog;
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
	void testSelectSuccess() throws SwackException {
		List<ChatLog> chatLogList = chatDAO.getChatlogList("R0000");

		for (ChatLog log : chatLogList) {
			System.out.println(log);
		}
		assertNotNull(chatLogList);
	}

}
