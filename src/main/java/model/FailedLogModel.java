package model;

import dao.FailedLogDAO;
import exception.SwackException;

public class FailedLogModel {
	/**
	 * 新規登録
	 * @param userId
	 * @return
	 * @throws SwackException
	 */
	public int insert(String userId) throws SwackException {
		FailedLogDAO failedLogDAO = new FailedLogDAO();
		return failedLogDAO.insert(userId);
	}

}
