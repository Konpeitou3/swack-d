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
	 * @return room 参加ルーム
	 * @throws SwackException 独自エラー
	 */
	public Room getRoom(String roomId, String userId) throws SwackException {
		return new ChatDAO().getRoom(roomId, userId);
	}

	/**
	 * ルーム一覧取得
	 * @param userId ユーザID
	 * @return roomlist ルーム一覧
	 * @throws SwackException 独自エラー
	 */
	public ArrayList<Room> getRoomList(String userId) throws SwackException {
		return new ChatDAO().getRoomList(userId);
	}

	/**
	 * ダイレクトルーム一覧取得
	 * @param userId ユーザID
	 * @return roomlist ダイレクトルーム一覧
	 * @throws SwackException 独自エラー
	 */
	public ArrayList<Room> getDirectList(String userId) throws SwackException {
		return new ChatDAO().getDirectList(userId);
	}

	/**
	 * チャット履歴取得
	 * @param roomId ルームID
	 * @return chatLogList チャット履歴
	 * @throws SwackException 独自エラー
	 */
	public List<ChatLog> getChatlogList(String roomId) throws SwackException {
		return new ChatDAO().getChatlogList(roomId);
	}

	/**
	 * チャット投稿
	 * @param roomId ルームID
	 * @param userId ユーザID
	 * @throws SwackException 独自エラー
	 */
	public void saveChatLog(String roomId, String userId, String message) throws SwackException {
		new ChatDAO().saveChatlog(roomId, userId, message);
	}

	/**
	 * チャット削除
	 * @param chatlogId チャット履歴ID
	 * @throws SwackException 独自エラー
	 */
	public void deleteChatlog(String chatlogId) throws SwackException {
		new ChatDAO().deleteChatlog(chatlogId);
	}

	/**
	 * メッセージ編集
	 * @param massage メッセージ
	 * @param chatlogId チャット履歴ID
	 * @throws SwackException 独自エラー
	 */
	public void updateChatlog(String massage, String chatlogId) throws SwackException {
		new ChatDAO().updateChatlog(massage, chatlogId);
	}

	//	/**
	//	 * スターメッセージ一覧取得
	//	 * @return starMessageList スターメッセージ一覧
	//	 * @throws SwackException 独自エラー
	//	 */
	//	public ArrayList<ChatLog> starMessageList() throws SwackException {
	//		return new ChatDAO().starMessageList();;
	//
	//	}
}