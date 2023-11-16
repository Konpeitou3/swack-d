package dao;

import static parameter.Messages.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Admin;
import exception.SwackException;

/**
 * 管理者権限を操作するDAO
 */
public class RoomAdminDAO extends BaseDAO {
	public RoomAdminDAO() throws SwackException {
		super();
	}

	/**
	 * ルームの管理者権限追加
	 * @param roomid ルームID
	 * @param userid ユーザID
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int JoinRoomAdmin(String roomid, String userid) throws SwackException {

		//結果用
		int result;

		String sql = "INSERT INTO roomadmins (roomid, userid) VALUES(?,?);";
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, roomid);
			pStmt.setString(2, userid);

			result = pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		// 成功の場合1を返す
		return result;
	}

	/**
	 * ルームの管理者権限削除
	 * @param roomid ルームID
	 * @param userid ユーザID
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int LeavingTheRoomAdmin(String roomid, String userid) throws SwackException {

		//結果用
		int result;

		String sql = "DELETE FROM roomadmins WHERE roomid=? AND userid = ?;";
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, roomid);
			pStmt.setString(2, userid);

			result = pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		//成功の場合1を返す
		return result;
	}

	//
	/**
	 * 管理者情報取得
	 * @param roomid ルームID
	 * @param userid ユーザID
	 * @return AdminUser 管理者情報
	 * @throws SwackException 独自エラー
	 */
	public Admin getRoomAdmin(String roomid, String userid) throws SwackException {
		String sql = "SELECT * FROM roomadmins WHERE roomid=? AND userid=?;";

		// Adminオブジェクトを作成
		Admin AdminUser = new Admin();

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, roomid);
			pStmt.setString(2, userid);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String roomId = rs.getString("ROOMID");
				String userId = rs.getString("USERID");

				AdminUser = new Admin(roomId, userId);
			}
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}

		//管理者情報を返す
		return AdminUser;
	}

}
