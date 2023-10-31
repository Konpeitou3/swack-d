
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

	//追加用ルーム用意
	public String maxRoomSelect() throws SwackException {
		String sql = "SELECT MAX(ROOMID) AS MAXROOMID FROM ROOMS ;";
		String userId = null;
		String newId = null;
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				userId = rs.getString("MAXROOMID");
			}

			// "U****" の部分を取得
			String numberPart = userId.substring(1); // "****" 部分を取得

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
		return newId;
	}

	//ルーム追加
	public int insert(String roomname, String createduserid, Boolean directed, Boolean privated)
			throws SwackException {

		//自動採番
		String roomid = maxRoomSelect();
		System.out.println("nextuserid:" + roomid);
		int rs;
		String sql = "INSERT INTO rooms (roomid, roomname,createduserid,directed,privated) VALUES(?,?,?,?,?);";
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, roomid);
			pStmt.setString(2, roomname);
			pStmt.setString(3, createduserid);
			pStmt.setBoolean(4, directed);
			pStmt.setBoolean(5, privated);

			rs = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		return rs;
	}

	//ダイレクトルーム追加
	public int DirectInsert(String roomname, String createduserid, Boolean directed, Boolean privated)
			throws SwackException {

		//自動採番
		String roomid = maxRoomSelect();
		System.out.println("nextuserid:" + roomid);
		int rs;

		String sql = "INSERT INTO rooms (roomid, roomname,createduserid,directed,privated) VALUES(?,?,?,?,?);";
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, roomid);
			pStmt.setString(2, roomname);
			pStmt.setString(3, createduserid);
			pStmt.setBoolean(4, directed);
			pStmt.setBoolean(5, privated);

			rs = pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		return rs;
	}

	//参加ルーム一覧の作成
	public List<Room> getOtherRoomList(String userid) throws SwackException {
		String sql = "SELECT ROOMNAME FROM ROOMS R JOIN JOINROOM J ON R.ROOMID = J.ROOMID WHERE R.ROOMID NOT IN(SELECT J.ROOMID FROM JOINROOM J WHERE J.USERID=?);";

		List<Room> getOtherRoomList = new ArrayList<Room>();
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userid);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String roomName = rs.getString("ROOMNAME");

				Room roomname = new Room(roomName); // Userオブジェクトを作成
				getOtherRoomList.add(roomname);
			}
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		return getOtherRoomList;
	}

}
