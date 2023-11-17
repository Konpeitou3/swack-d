package dao;

import static parameter.Messages.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.ChatLog;
import bean.Room;
import exception.SwackException;

/**
 * チャット機能に関するDBアクセスを行う.
 */
public class ChatDAO extends BaseDAO {
	public ChatDAO() throws SwackException {
		super();
	}

	/**
	 * チャット履歴取得
	 * @param roomId ルームID
	 * @return chatLogList チャット履歴
	 * @throws SwackException 独自エラー
	 */
	public List<ChatLog> getChatlogList(String roomId) throws SwackException {
		String sql = "SELECT CHATLOGID, U.USERID AS USERID, U.USERNAME AS USERNAME, MESSAGE, CREATED_AT "
				+ "FROM CHATLOG C JOIN USERS U ON C.USERID = U.USERID WHERE ROOMID = ? " + "ORDER BY CREATED_AT ASC";

		//チャット履歴リスト作成
		List<ChatLog> chatLogList = new ArrayList<ChatLog>();
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, roomId);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				int chatLogId = rs.getInt("CHATLOGID");
				String userId = rs.getString("USERID");
				String userName = rs.getString("USERNAME");
				String message = rs.getString("MESSAGE");
				Timestamp createdAt = rs.getTimestamp("CREATED_AT");

				ChatLog chatLog = new ChatLog(chatLogId, roomId, userId, userName, message, createdAt);
				chatLogList.add(chatLog);
			}
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		//チャット履歴を返す
		return chatLogList;
	}

	/**
	 * 参加ルーム取得
	 * @param roomId ルームID
	 * @param userId ユーザID
	 * @return room 参加ルーム
	 * @throws SwackException 独自エラー
	 */
	public Room getRoom(String roomId, String userId) throws SwackException {
		String sqlGetRoom = "SELECT R.ROOMID, R.ROOMNAME, COUNT(*) AS MEMBER_COUNT, R.DIRECTED"
				+ " FROM ROOMS R JOIN JOINROOM J ON R.ROOMID = J.ROOMID" + " WHERE R.ROOMID = ?"
				+ " GROUP BY R.ROOMID, R.ROOMNAME, R.DIRECTED";
		String sqlGetDirectRoom = "SELECT U.USERNAME AS ROOMNAME FROM JOINROOM R"
				+ " JOIN USERS U ON R.USERID = U.USERID" + " WHERE R.USERID <> ? AND ROOMID = ?";

		boolean directed = false;
		String roomName = "";
		int memberCount = 0;

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sqlGetRoom);
			pStmt.setString(1, roomId);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				directed = rs.getBoolean("DIRECTED");
				roomName = rs.getString("ROOMNAME");
				memberCount = rs.getInt("MEMBER_COUNT");
			}

			// for Direct
			if (directed) {
				PreparedStatement pStmt2 = conn.prepareStatement(sqlGetDirectRoom);
				pStmt2.setString(1, userId);
				pStmt2.setString(2, roomId);

				ResultSet rs2 = pStmt2.executeQuery();
				if (rs2.next()) {
					roomName = rs2.getString("ROOMNAME");
					memberCount = 2;
				}
			}
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		Room room = new Room(roomId, roomName, memberCount, directed);
		//参加しているルームを返す
		return room;
	}

	/**
	 * ルーム一覧取得
	 * @param userId ユーザID
	 * @return roomlist ルーム一覧
	 * @throws SwackException 独自エラー
	 */
	public ArrayList<Room> getRoomList(String userId) throws SwackException {
		String sql = "SELECT R.ROOMID, R.ROOMNAME FROM JOINROOM J JOIN ROOMS R ON J.ROOMID = R.ROOMID "
				+ "WHERE J.USERID = ? AND R.DIRECTED = false ORDER BY R.ROOMNAME ASC";
		//ルーム一覧リスト作成
		ArrayList<Room> roomlist = new ArrayList<Room>();

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userId);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String roomId = rs.getString("ROOMID");
				String roomName = rs.getString("ROOMNAME");
				roomlist.add(new Room(roomId, roomName));
			}

		} catch (Exception e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		//ルーム一覧を返す
		return roomlist;

	}

	/**
	 * ダイレクトルーム一覧取得
	 * @param userId ユーザID
	 * @return roomlist ダイレクトルーム一覧
	 * @throws SwackException 独自エラー
	 */
	public ArrayList<Room> getDirectList(String userId) throws SwackException {
		String sql = "SELECT R.ROOMID, U.USERNAME AS ROOMNAME FROM JOINROOM R " + "JOIN USERS U ON R.USERID = U.USERID "
				+ "WHERE R.USERID <> ? AND ROOMID IN "
				+ "(SELECT R.ROOMID FROM JOINROOM J JOIN ROOMS R ON J.ROOMID = R.ROOMID "
				+ "WHERE J.USERID = ? AND R.DIRECTED = TRUE) " + "ORDER BY R.USERID";
		//ダイレクトルーム一覧を作成
		ArrayList<Room> roomlist = new ArrayList<Room>();

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userId);
			pst.setString(2, userId);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String roomId = rs.getString("ROOMID");
				String roomName = rs.getString("ROOMNAME");
				roomlist.add(new Room(roomId, roomName));
			}

		} catch (Exception e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		//ダイレクトルーム一覧を返す
		return roomlist;

	}

	/**
	 * チャット投稿
	 * @param roomId ルームID
	 * @param userId ユーザID
	 * @param message メッセージ
	 * @throws SwackException 独自エラー
	 */
	public void saveChatlog(String roomId, String userId, String message) throws SwackException {
		String sql = "INSERT INTO CHATLOG (CHATLOGID, ROOMID, USERID, MESSAGE, CREATED_AT)"
				+ " VALUES (nextval('CHATLOGID_SEQ'), ?, ?, ?, CURRENT_TIMESTAMP)";

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, roomId);
			pStmt.setString(2, userId);
			pStmt.setString(3, message);

			pStmt.executeUpdate();
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
	}

	/**
	 * チャット削除
	 * @param chatlogId チャット履歴ID
	 * @throws SwackException 独自エラー
	 */
	public void deleteChatlog(String chatlogId) throws SwackException {
		String sql = "DELETE FROM CHATLOG WHERE CHATLOGID =?";

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, chatlogId);

			pStmt.executeUpdate();
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
	}

	/**
	 * メッセージ編集
	 * @param massage メッセージ
	 * @param chatlogId チャット履歴ID
	 * @throws SwackException 独自エラー
	 */
	public void updateChatlog(String massage, String chatlogId) throws SwackException {
		String sql = "UPDATE CHATLOG SET MESSAGE=?, CREATED_AT= CURRENT_TIMESTAMP WHERE CHATLOGID= ?";

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, massage);
			pStmt.setString(2, chatlogId);

			pStmt.executeUpdate();
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
	}

}
