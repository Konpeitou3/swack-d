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

public class FailedLogDAO extends BaseDAO {
	public FailedLogDAO() throws SwackException {
		super();
	}

	//ログイン失敗履歴出力
	public List<FailedLog> lastLoginCheck(String failUserId) throws SwackException {
		String sql = "SELECT * FROM FAILEDLOG F JOIN USERS U ON F.USERID = U.USERID WHERE F.USERID=?, AND U.LASTLOGIN_AT < FAILED_AT";

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

		return getLastLoginList;
	}

	//新規登録
	public int insert(String userId) throws SwackException {
		String sql = "INSERT INTO failedlog (failedlogid, userid, failed_at) VALUES(nextval('FAILEDLOGID_SEQ'),?,CURRENT_TIMESTAMP);";
		int rs;
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);

			rs = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		return rs;
	}
}