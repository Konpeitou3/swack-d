package dao;

import static org.junit.Assert.*;

import java.util.List;

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

	////	@Test
	//	void testSelectFailure() throws SwackException {
	//		User userF = usersDAO.select("taro@swack.com", "password");
	//		assertNull(userF);
	//	}
	//
	//	//		@Test
	//	void testInsertSuccess() throws SwackException {
	//		User userT = usersDAO.insert("saburo@swack.com", "swack0001");
	//		System.out.println("testSelectSuccess():" + userT.toString());
	//		assertNotNull(userT);
	//	}

	//		@Test
	void testInsertFailure() throws SwackException {
		User userF = usersDAO.select("taro@swack.com", "password");
		assertNull(userF);
	}

	//@Test
	void testExists() {
		fail("まだ実装されていません");
	}

	//@Test
	void testSelectMaxUserId() throws SwackException {
		String userId = usersDAO.maxSelect();
		System.out.println("testSelectMaxUserId():" + userId);
	}

	//@Test
	void testgetUserList() throws SwackException {
		List<User> user = usersDAO.getUserList("U0001");
		System.out.println("testgetUserList():" + user);
		assertNotNull(user);
	}

	@Test
	void testgetMailAddressList() throws SwackException {
		List<User> user = usersDAO.getMailAddressList();
		System.out.println("testgetMailAddressList():" + user);
		assertNotNull(user);
	}

	@Test
	void testgetAllUserList() throws SwackException {
		List<User> user = usersDAO.getAllUserList();
		System.out.println("testgetAllUserList():" + user);
		assertNotNull(user);
	}

	@Test
	void testgetUserinfoList() throws SwackException {
		List<User> user = usersDAO.getUserinfoList();
		System.out.println("testgetUserinfoList():" + user);
		assertNotNull(user);
	}

	@Test
	void testmailAddressCheckSuccess() throws SwackException {
		User user = usersDAO.mailAddressCheck("taro@swack.com");
		System.out.println("testmailAddressCheckSuccess():" + user);
		assertNotNull(user);
	}

	@Test
	void testmailAddressCheckFailure() throws SwackException {
		User user = usersDAO.mailAddressCheck("taroswacktest@swack.com");
		assertNull(user);
	}

}
