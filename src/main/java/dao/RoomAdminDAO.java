package dao;

import static parameter.Messages.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Admin;
import exception.SwackException;

public class RoomAdminDAO extends BaseDAO {
	public RoomAdminDAO() throws SwackException {
		super();
	}

	//ルームの管理者権限追加
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
		//成功の場合1を返す
		return result;
	}

	//管理者権限削除
	public int LeavingTheRoomAdmin(String roomid, String userid) throws SwackException {

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
		return result;
	}

	//管理者リスト取得
	public List<Admin> getRoomAdminList() throws SwackException {
		String sql = "SELECT * FROM roomadmins;";

		List<Admin> AdminUsers = new ArrayList<Admin>();
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String roomId = rs.getString("ROOMID");
				String userId = rs.getString("USERID");

				Admin admin = new Admin(roomId, userId); // Adminオブジェクトを作成
				AdminUsers.add(admin);
			}
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		return AdminUsers;
	}

}
