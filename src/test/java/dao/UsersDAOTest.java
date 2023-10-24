package dao;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import bean.User;
import context.SetUpDBConnectionPool;
import exception.SwackException;

class UsersDAOTest {

	private static UsersDAO usersDAO;

	@BeforeAll
	static void setUpBeforeClass() throws SwackException {
		SetUpDBConnectionPool.setUp();

		usersDAO = new UsersDAO();
	}

	@Test
	void testSelectSuccess() throws SwackException {
		User userT = usersDAO.select("taro@swack.com", "swack0001");
		System.out.println("testSelectSuccess():" + userT.toString());
		assertNotNull(userT);
	}

	@Test
	void testSelectFailure() throws SwackException {
		User userF = usersDAO.select("taro@swack.com", "password");
		assertNull(userF);
	}

	//@Test
	void testExists() {
		fail("まだ実装されていません");
	}

	//@Test
	void testSelectMaxUserId() {
		fail("まだ実装されていません");
	}

	//@Test
	void testInsert() {
		fail("まだ実装されていません");
	}

}
