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

	public void JoinRoom(String roomid, String userid) throws SwackException {

		String sql = "INSERT INTO joinroom (roomid, userid) VALUES(?,?);";
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, roomid);
			pStmt.setString(2, userid);

			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		return;
	}
}
