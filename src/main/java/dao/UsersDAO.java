package dao;

import static parameter.Messages.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bean.OtherUsers;
import bean.User;
import exception.SwackException;

public class UsersDAO extends BaseDAO {
	public UsersDAO() throws SwackException {
		super();
	}

	public User select(String mailAddress, String password) throws SwackException {
		String sql = "SELECT USERID, USERNAME FROM USERS WHERE MAILADDRESS = ? AND PASSWORD = ?";
		User user = null;
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mailAddress);
			pStmt.setString(2, password);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				String userId = rs.getString("USERID");
				String userName = rs.getString("USERNAME");
				// mask password
				user = new User(userId, userName, mailAddress, "********");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		return user;
	}

	public String maxSelect() throws SwackException {
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

	public boolean insert(String username, String mailAddress, String password) throws SwackException {

		//自動採番
		String userid = maxSelect();
		System.out.println("nextuserid:" + userid);

		String sql = "INSERT INTO users (userid, username,mailaddress, password) VALUES(?,?,?,?);";
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userid);
			pStmt.setString(2, username);
			pStmt.setString(3, mailAddress);
			pStmt.setString(4, password);

			int rs = pStmt.executeUpdate();
			System.out.println(rs);

			if (Objects.nonNull(rs)) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		return false;
	}

	//自分以外のuseridリスト
	public List<OtherUsers> getUserList(String MyUserId) throws SwackException {
		String sql = "SELECT USERID ,USERNAME FROM USERS WHERE USERID <> ?;";

		List<OtherUsers> OtherUsers = new ArrayList<OtherUsers>();
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, MyUserId);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String userName = rs.getString("USERNAME");

				OtherUsers user = new OtherUsers(userName); // Userオブジェクトを作成
				OtherUsers.add(user);
			}
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		return OtherUsers;
	}

}
