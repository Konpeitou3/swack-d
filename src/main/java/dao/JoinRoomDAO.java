package dao;

import static parameter.Messages.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exception.SwackException;

public class JoinRoomDAO extends BaseDAO {
	public JoinRoomDAO() throws SwackException {
		super();
	}

	//ユーザーの追加
	public int JoinRoom(String roomid, String userid) throws SwackException {

		int result;
		String sql = "INSERT INTO joinroom (roomid, userid) VALUES(?,?);";
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

	//ルーム退室
	public int LeavingTheRoom(String roomid, String userid) throws SwackException {

		int result;
		String sql = "INSERT INTO joinroom (roomid, userid) VALUES(?,?);";
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

}
