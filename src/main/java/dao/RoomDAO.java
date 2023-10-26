
package dao;

import static parameter.Messages.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;
import exception.SwackException;

public class RoomDAO extends BaseDAO {
	public RoomDAO() throws SwackException {
		super();
	}

	public String maxRoomSelect() throws SwackException {
		String sql = "SELECT MAX(USERID) AS MAXID FROM USERS ;";
		String userId = null;
		String newId = null;
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				userId = rs.getString("MAXID");
			}

			// "U****" の部分を取得
			String numberPart = userId.substring(1); // "****" 部分を取得

			try {
				// "****" 部分を整数に変換してプラス1し、文字列に戻す
				int number = Integer.parseInt(numberPart) + 1;
				String newNumberPart = String.format("%04d", number); // 4桁の数字にフォーマット

				// 新しい文字列を生成
				newId = "U" + newNumberPart;

			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new SwackException(ERR_USERID_ADD, e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		return newId;
	}

	public User insert(String username, String mailAddress, String password) throws SwackException {

		//自動採番
		String userid = maxRoomSelect();
		System.out.println("nextuserid:" + userid);

		String sql = "INSERT INTO users (userid, username,mailaddress, password) VALUES(?,?,?,?);";
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userid);
			pStmt.setString(2, username);
			pStmt.setString(3, mailAddress);
			pStmt.setString(4, password);

			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		return null;
	}

}
