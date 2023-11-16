package dao;

import static parameter.Messages.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;
import exception.SwackException;

/**
 * ルームメンバーの操作に関するDBアクセスを行う.
 */
public class JoinRoomDAO extends BaseDAO {
	public JoinRoomDAO() throws SwackException {
		super();
	}

	/**
	 * ルーム参加
	 * @param roomid ルームID
	 * @param userid ユーザID
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int JoinRoom(String roomid, String userid) throws SwackException {

		//結果用
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
		//成功の場合1を返す
		return result;
	}

	/**
	 * ルーム退出
	 * @param roomid ルームID
	 * @param userid ユーザID
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int LeavingTheRoom(String roomid, String userid) throws SwackException {

		//結果用
		int result;
		String sql = "DELETE FROM joinroom WHERE roomid=? AND userid = ?;";
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

	/**
	 * 招待用ユーザーネームリスト取得
	 * @param roomid ルームID
	 * @param userid ユーザID
	 * @return OtherUsers 招待用ユーザーネームリスト
	 * @throws SwackException 独自エラー
	 */
	public List<User> getUserList(String roomid, String userid) throws SwackException {
		String sql = "select distinct u.userid ,username from users u join joinroom j on u.userid = j.userid where u.userid not in(select j.userid from joinroom j where roomid=?) AND u.userid<>?;";
		//招待用ユーザーネームリスト作成
		List<User> OtherUsers = new ArrayList<User>();
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, roomid);
			pStmt.setString(2, userid);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String userId = rs.getString("USERID");
				String userName = rs.getString("USERNAME");

				User user = new User(userId, userName); // Userオブジェクトを作成
				OtherUsers.add(user);
			}
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		//招待用ユーザーネームリストを返す
		return OtherUsers;
	}

	/**
	 * 管理者権限未保有ルームメンバーリスト出力
	 * @param roomid ルームID
	 * @return OtherUsers 管理者権限未保有ルームメンバーリスト
	 * @throws SwackException 独自エラー
	 */
	public List<User> getNotAdminUserList(String roomid) throws SwackException {
		String sql = "select distinct u.userid ,username from users u join joinroom j on u.userid = j.userid where roomid= ? AND u.userid not in(select a.userid from roomadmins a where roomid=?) ORDER BY userid;";
		//管理者権限未保有ルームメンバーリスト作成
		List<User> NotAdminUsers = new ArrayList<User>();
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, roomid);
			pStmt.setString(2, roomid);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String userId = rs.getString("USERID");
				String userName = rs.getString("USERNAME");

				User user = new User(userId, userName); // Userオブジェクトを作成
				NotAdminUsers.add(user);
			}
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}

		//管理者権限未保有ルームメンバーリストを返す
		return NotAdminUsers;
	}

}
