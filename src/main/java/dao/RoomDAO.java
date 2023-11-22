
package dao;

import static parameter.Messages.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Room;
import exception.SwackException;

/**
 * ルームに関するDBアクセスを行う.
 */
public class RoomDAO extends BaseDAO {
	public RoomDAO() throws SwackException {
		super();
	}
	//ルームselect
	//	public Room select(String roomid) throws SwackException {
	//		String sql = "SELECT ROOMID,ROOMNAME,CREATEDUSERID,DIRECTED,PRIVATED FROM ROOMS WHERE ROOMID=? ;";
	//		User user = null;
	//		try (Connection conn = dataSource.getConnection()) {
	//			PreparedStatement pStmt = conn.prepareStatement(sql);
	//			pStmt.setString(1, roomid);
	//
	//			ResultSet rs = pStmt.executeQuery();
	//			if (rs.next()) {
	//				String userId = rs.getString("USERID");
	//				String userName = rs.getString("USERNAME");
	//				// mask password
	//				user = new User(userId, userName, mailAddress, "********");
	//			}
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//			throw new SwackException(ERR_DB_PROCESS, e);
	//		}
	//		return null;
	//	}

	/**
	 * 新規ルームID選択
	 * @return newId 新規ルームでつけるID
	 * @throws SwackException 独自エラー
	 */
	public String maxRoomSelect() throws SwackException {
		String sql = "SELECT MAX(ROOMID) AS MAXROOMID FROM ROOMS ;";
		String roomId = null;
		String newId = null;
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				roomId = rs.getString("MAXROOMID");
			}

			// "R****" の部分を取得
			String numberPart = roomId.substring(1); // "****" 部分を取得

			try {
				// "****" 部分を整数に変換してプラス1し、文字列に戻す
				int number = Integer.parseInt(numberPart) + 1;
				String newNumberPart = String.format("%04d", number); // 4桁の数字にフォーマット

				// 新しい文字列を生成
				newId = "R" + newNumberPart;

			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new SwackException(ERR_USERID_ADD, e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		//新規ルームでつけるIDを返す
		return newId;
	}

	/**
	 * 新規ルーム作成
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int insert(String roomname, String createduserid, Boolean directed, Boolean privated)
			throws SwackException {

		//自動採番
		String roomid = maxRoomSelect();
		System.out.println("nextroomid:" + roomid);
		//結果用
		int result;

		String sql = "INSERT INTO rooms (roomid, roomname,createduserid,directed,privated) VALUES(?,?,?,?,?);";
		try (Connection conn2 = dataSource.getConnection()) {
			PreparedStatement pStmt2 = conn2.prepareStatement(sql);
			pStmt2.setString(1, roomid);
			pStmt2.setString(2, roomname);
			pStmt2.setString(3, createduserid);
			pStmt2.setBoolean(4, directed);
			pStmt2.setBoolean(5, privated);

			result = pStmt2.executeUpdate();
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e2);
		}

		//ルーム作成者のインサート
		sql = "INSERT INTO joinroom (roomid, userid) VALUES(?,?);";
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, roomid);
			pStmt.setString(2, createduserid);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		//成功の場合1を返す
		return result;
	}

	/**
	 * 新規ダイレクトルーム作成
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return result 成功の場合1を返す
	 * @throws SwackException 独自エラー
	 */
	public int DirectInsert(String createduserid, Boolean directed, Boolean privated)
			throws SwackException {

		//自動採番
		String roomid = maxRoomSelect();
		System.out.println("nextroomid:" + roomid);
		//実行用
		int result;

		String roomname = directRoomnameCreate();
		System.out.println("roomname:" + roomname);

		//ルーム作成者のインサート
		String sql = "INSERT INTO joinroom (roomid, userid) VALUES(?,?);";
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, roomid);
			pStmt.setString(2, createduserid);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}

		String sql2 = "INSERT INTO rooms (roomid, roomname,createduserid,directed,privated) VALUES(?,PU0001,U0002,?,?,?,?);";
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql2);
			pStmt.setString(1, roomid);
			pStmt.setString(2, roomname);
			pStmt.setString(3, createduserid);
			pStmt.setBoolean(4, directed);
			pStmt.setBoolean(5, privated);

			result = pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		//成功の場合1を返す
		return result;
	}

	private String directRoomnameCreate() {
		// TODO P+そのダイレクトチャットの参加ユーザID1+,+そのダイレクトチャットの参加ユーザID2を作成
		return null;
	}

	/**
	 * 参加しているルーム一覧取得
	 * @param userid ユーザID
	 * @return OtherRoomList 参加しているルーム一覧
	 * @throws SwackException 独自エラー
	 */
	public List<Room> getOtherRoomList(String userid) throws SwackException {
		String sql = "SELECT DISTINCT ROOMNAME ,R.ROOMID FROM ROOMS R JOIN JOINROOM J ON R.ROOMID = J.ROOMID WHERE R.ROOMID NOT IN(SELECT J.ROOMID FROM JOINROOM J WHERE J.USERID=?) AND R.PRIVATED='FALSE';";
		//参加しているルーム一覧を作成
		List<Room> OtherRoomList = new ArrayList<Room>();
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userid);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String roomId = rs.getString("ROOMID");
				String roomName = rs.getString("ROOMNAME");
				Room room = new Room(roomId, roomName); // Userオブジェクトを作成
				OtherRoomList.add(room);
			}
		} catch (SQLException e) {
		}
		//参加しているルーム一覧を返す
		return OtherRoomList;
	}

}
