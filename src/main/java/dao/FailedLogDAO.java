package dao;

import static parameter.Messages.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.FailedLog;
import exception.SwackException;

/**
 * ログイン失敗失敗履歴に関するDBアクセスを行う.
 */
public class FailedLogDAO extends BaseDAO {
	public FailedLogDAO() throws SwackException {
		super();
	}

	/**
	 * ログイン失敗履歴出力
	 * @param failUserId 失敗したユーザのID
	 * @return getLastLoginList 指定されたユーザのログイン失敗履歴リスト
	 * @throws SwackException 独自エラー
	 */
	public List<FailedLog> lastLoginCheck(String failUserId) throws SwackException {
		String sql = "SELECT * FROM FAILEDLOG F JOIN USERS U ON F.USERID = U.USERID WHERE F.USERID=? AND U.LASTLOGIN_AT < FAILED_AT";

		// 指定されたユーザのログイン失敗履歴リストを作成する
		List<FailedLog> getLastLoginList = new ArrayList<FailedLog>();
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, failUserId);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				int failedlogId = rs.getInt("FAILEDLOGID");
				String userId = rs.getString("USERID");
				Timestamp failed_at = rs.getTimestamp("FAILED_AT");
				FailedLog failedlog = new FailedLog(failedlogId, userId, failed_at); // Userオブジェクトを作成
				getLastLoginList.add(failedlog);
			}
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}

		// 指定されたユーザのログイン失敗履歴リストを返す
		return getLastLoginList;
	}

	/**
	 * 新規登録
	 * @param userId ユーザID
	 * @throws SwackException 独自エラー
	 */
	public int insert(String userId) throws SwackException {
		String sql = "INSERT INTO failedlog (failedlogid, userid, failed_at) VALUES(nextval('FAILEDLOGID_SEQ'),?,CURRENT_TIMESTAMP);";

		//結果用
		int result;

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);

			result = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		// 成功の場合1を返す
		return result;
	}
}
