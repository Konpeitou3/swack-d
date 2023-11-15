
package parameter;

/**
 * システムメッセージ定義クラス
 */
public class Messages {

	/** システム全体 */
	public static final String ERR_SYSTEM = "システムエラーが発生しました";

	/** DB */
	public static final String ERR_DB_CONNECT = "データベースへの接続時にエラーが発生しました";
	public static final String ERR_DB_PROCESS = "データベースの処理中にエラーが発生しました";
	public static final String ERR_DB_CLOSE = "データベースからの切断時にエラーが発生しました";

	/**処理内容 */
	public static final String ERR_USERID_ADD = "データベースからの切断時にエラーが発生しました";

	/** ログイン */
	public static final String ERR_SESSION_TIMEOUT = "ログイン情報の取得に失敗しました。再度ログインしてください。";
	public static final String ERR_LOGIN_PARAM_MISTAKE = "メールアドレス、またはパスワードに誤りがあります。入力項目を確認し、再度ログインしてください。";
	public static final String INFO_BIGIN = "ワークスペースに参加済みの方はログインしてください。初めての方はワークスペースに参加してください。";
	public static final String ACCOUNT_LOCKED = "アカウントにロックがかかっています。";

	/** ユーザ管理 */
	public static final String ERR_USERS_ISREGISTERED = "このユーザは登録済みです。入力項目を確認し、登録し直してください。";
	public static final String ERR_USERS_PARAM_MISTAKE = "ユーザ情報に誤りがあります。入力項目を確認し、再度登録してください。";
	public static final String INFO_USERS_ENTRY_SUCCESS = "ワークスペースに参加しました。ログインしてください。";
	public static final String NEW_USERS_CREATE_SUCCSESS = "新規ユーザーアカウントの登録が完了しました。";
	public static final String NEW_USERS_SELECT_MISTAKE = "存在しないメールアドレスです。";

	/** 新規ルーム作成*/
	public static final String CREATE_ROOM_SUCCESS = "ルームを作成しました。";
	public static final String CREATE_ROOM_ERROR = "ルームを作成できませんでした。";

	/** パスワード管理*/
	public static final String PASSWORD_UPDATE_ERROR = "新しいパスワードを入力してください。";

}