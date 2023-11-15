package dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import bean.FailedLog;
import context.SetUpDBConnectionPool;
import exception.SwackException;

class FailedLogDAOTest {

	private static FailedLogDAO failedLogDAO;

	@BeforeAll
	static void setUpBeforeClass() throws SwackException {
		SetUpDBConnectionPool.setUp();

		failedLogDAO = new FailedLogDAO();
	}

	@Test
	void testLastLoginCheck() throws SwackException {
		List<FailedLog> lastLoginCheck = failedLogDAO.lastLoginCheck("U0001");
		System.out.println("testSelectMaxUserId():" + lastLoginCheck);
		assertNotNull(lastLoginCheck);
	}

}
