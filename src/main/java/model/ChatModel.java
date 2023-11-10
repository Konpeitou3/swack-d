package model;

import java.util.ArrayList;
import java.util.List;

import bean.ChatLog;
import bean.Room;
import dao.ChatDAO;
import exception.SwackException;

/**
 * チャット機能を実行するクラス
 */
public class ChatModel {
	/**
	 * 参加ルーム取得
	 * @param roomId ルームID
	 * @param userId ユーザID
	 * @return
	 * @throws SwackException
	 */
	public Room getRoom(String roomId, String userId) throws SwackException {
		return new ChatDAO().getRoom(roomId, userId);
	}

	/**
	 * ルーム一覧取得
	 * @param userId ユーザID
	 * @return
	 * @throws SwackException
	 */
	public ArrayList<Room> getRoomList(String userId) throws SwackException {
		return new ChatDAO().getRoomList(userId);
	}

	/**
	 * ダイレクトルーム一覧取得
	 * @param userId ユーザID
	 * @return
	 * @throws SwackException
	 */
	public ArrayList<Room> getDirectList(String userId) throws SwackException {
		return new ChatDAO().getDirectList(userId);
	}

	/**
	 * チャット履歴取得
	 * @param roomId ルームID
	 * @return
	 * @throws SwackException
	 */
	public List<ChatLog> getChatlogList(String roomId) throws SwackException {
		return new ChatDAO().getChatlogList(roomId);
	}

	/**
	 * 
	 * @param roomId ルームID
	 * @param userId ユーザID
	 * @param message メッセージ
	 * @throws SwackException
	 */
	public void saveChatLog(String roomId, String userId, String message) throws SwackException {
		new ChatDAO().saveChatlog(roomId, userId, message);
	}
}