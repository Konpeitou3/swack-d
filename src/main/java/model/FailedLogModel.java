package model;

import dao.FailedLogDAO;
import exception.SwackException;

/**
 * ログイン失敗ログを動作させるクラス
 */
public class FailedLogModel {
	/**
	 * ログイン失敗ログ追加
	 * @param userId ユーザID
	 * @throws SwackException 独自エラー
	 */
	public int insert(String userId) throws SwackException {
		FailedLogDAO failedLogDAO = new FailedLogDAO();
		return failedLogDAO.insert(userId);
	}

}
