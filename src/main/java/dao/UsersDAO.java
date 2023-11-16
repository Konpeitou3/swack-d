package dao;

import static parameter.Messages.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.User;
import exception.SwackException;

/**
 * ユーザに関するDBアクセスを行う.
 */
public class UsersDAO extends BaseDAO {
	public UsersDAO() throws SwackException {
		super();
	}

	/**
	 * ユーザー検索
	 * @param mailAddress メールアドレス
	 * @param password	パスワード
	 * @return	user ユーザ情報
	 * @throws SwackException 独自エラー
	 */
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
		//ユーザ情報を返す
		return user;
	}

	/**
	 * 新規ユーザーID選択
	 * @return newId 新規登録でつけるID
	 * @throws SwackException 独自エラー
	 */
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
		//新規登録でつけるIDを返す
		return newId;
	}

	/**
	 * 新規登録
	 * @param username ユーザ名
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int insert(String username, String mailAddress, String password) throws SwackException {

		//自動採番
		String userid = maxSelect();
		System.out.println("nextuserid:" + userid);
		//結果用
		int result;

		String sql = "INSERT INTO users (userid, username,mailaddress, password) VALUES(?,?,?,?);";
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userid);
			pStmt.setString(2, username);
			pStmt.setString(3, mailAddress);
			pStmt.setString(4, password);

			result = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}

		String sql2 = "INSERT INTO joinroom (roomid, userid) VALUES('R0000',?);";
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);
			pStmt2.setString(1, userid);

			result = pStmt2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}

		// 成功の場合1を返す
		return result;
	}

	/**
	 * 招待用名前リスト取得用
	 * @param MyUserId 自分のユーザID
	 * @return userList 招待用名前リスト
	 * @throws SwackException 独自エラー
	 */
	public List<User> getUserList(String MyUserId) throws SwackException {
		String sql = "SELECT USERID ,USERNAME FROM USERS WHERE USERID <> ?;";
		//招待用名前リストを作成
		List<User> OtherUsers = new ArrayList<User>();
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, MyUserId);

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
		//招待用名前リストを返す
		return OtherUsers;
	}

	/**
	 * 登録済みメールアドレスリスト
	 * @return AllMailAddressList 登録済みメールアドレスリスト
	 * @throws SwackException 独自エラー
	 */
	public List<User> getMailAddressList() throws SwackException {
		String sql = "SELECT MAILADDRESS FROM USERS ;";
		//登録済みメールアドレスリスト作成
		List<User> AllMailAddressList = new ArrayList<User>();
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String mailaddress = rs.getString("MAILADDRESS");
				User mailAddress = new User(mailaddress);
				AllMailAddressList.add(mailAddress);
			}
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		//登録済みメールアドレスリストを返す
		return AllMailAddressList;

	}

	/**
	 * アカウント削除
	 * @param userid ユーザID
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int delete(String userid) throws SwackException {
		//結果用
		int result;

		String sql = "DELETE FROM USERS WHERE USERID = ?";
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userid);

			result = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}

		// 成功の場合1を返す
		return result;
	}

	/**
	 * 既存ユーザ情報取得
	 * @return AllUsers 既存ユーザ情報
	 * @throws SwackException 独自エラー
	 */
	public List<User> getAllUserList() throws SwackException {
		String sql = "SELECT USERID, MAILADDRESS, PASSWORD FROM USERS ;";
		// 既存ユーザ情報リスト作成
		List<User> AllUsers = new ArrayList<User>();
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String userId = rs.getString("USERID");
				String mailAddress = rs.getString("MAILADDRESS");
				String password = rs.getString("PASSWORD");
				User user = new User(userId, mailAddress, password); // Userオブジェクトを作成
				AllUsers.add(user);
			}
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		//既存ユーザ情報を返す
		return AllUsers;
	}

	/**
	 * ユーザ情報取得
	 * @return AllUsersInfo ユーザ情報
	 * @throws SwackException 独自エラー
	 */
	public List<User> getUserinfoList() throws SwackException {
		String sql = "SELECT USERID, USERNAME, LASTLOGIN_AT, LOCKED FROM USERS ;";
		//ユーザ情報リスト作成
		List<User> AllUsersInfo = new ArrayList<User>();
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String userId = rs.getString("USERID");
				String userName = rs.getString("USERNAME");
				Timestamp lastlogin_at = rs.getTimestamp("LASTLOGIN_AT");
				Boolean locked = rs.getBoolean("LOCKED");
				User user = new User(userId, userName, lastlogin_at, locked); // Userオブジェクトを作成
				AllUsersInfo.add(user);
			}
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		//ユーザ情報を返す
		return AllUsersInfo;
	}

	/**
	 * パスワード変更
	 * @param password	パスワード
	 * @param mailAddress メールアドレス
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int updatePassword(String password, String mailAddress) throws SwackException {
		//結果用
		int result;

		String sql = "UPDATE USERS SET PASSWORD = ? WHERE MAILADDRESS = ?";
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, password);
			pStmt.setString(2, mailAddress);

			result = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}

		// 成功の場合1を返す
		return result;
	}

	/**
	 * 最終ログイン時間更新
	 * @param userId ユーザID
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int updateLastLogin(String userId) throws SwackException {
		//結果用
		int result;

		String sql = "UPDATE USERS SET LASTLOGIN_AT = CURRENT_TIMESTAMP WHERE USERID = ?";
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

	/**
	 * アカウントロック設定
	 * @param userId ユーザID
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int updateLockedTrue(String userId) throws SwackException {
		//結果用
		int result;

		String sql = "UPDATE USERS SET LOCKED = TRUE WHERE USERID = ?";
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

	/**
	 * アカウントロック解除
	 * @param userId ユーザID
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int updateLockedFalse(String userId) throws SwackException {
		//結果用
		int result;

		String sql = "UPDATE USERS SET LOCKED = FALSE WHERE USERID = ?";
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

	/**
	 * メールアドレスチェック
	 * @param mailAddress メールアドレス
	 * @return user ユーザ情報
	 * @throws SwackException 独自エラー
	 */
	public User mailAddressCheck(String mailAddress) throws SwackException {
		String sql = "SELECT USERID, LOCKED FROM USERS WHERE MAILADDRESS = ?;";
		User user = null;
		String userId = null;
		Boolean locked = false;
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mailAddress);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				userId = rs.getString("USERID");
				locked = rs.getBoolean("LOCKED");
			}
			user = new User(userId, locked);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		return user;
	}

	/**
	 * アカウントロックされているユーザのリスト取得
	 * @param mailAddress メールアドレス
	 * @return lockedUserList アカウントロックされているユーザのリスト
	 * @throws SwackException 独自エラー
	 */
	public List<User> lockedUserList() throws SwackException {
		String sql = "SELECT UESRNAME, USERID FROM USERS WHERE LOCKED = TRUE;";
		//アカウントロックされているユーザのリスト作成
		List<User> lockedUserList = new ArrayList<User>();
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String userName = rs.getString("USERNAME");
				String userId = rs.getString("USERID");
				User user = new User(userName, userId);
				lockedUserList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		//アカウントロックされているユーザのリストを返す
		return lockedUserList;
	}
}
