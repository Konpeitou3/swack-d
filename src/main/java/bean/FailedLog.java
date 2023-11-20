package bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * チャットログ情報を管理するBean
 */
public class FailedLog implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ログイン失敗ログID */
	private int failedLogId;
	/** ユーザID */
	private String userId;
	/** 失敗日時 */
	private Timestamp failedAt;

	public FailedLog() {
		// for JSP
	}

	/**
	 * リファクタリング
	 * @param failedLogId ログイン失敗ログID
	 * @param userId ユーザID
	 * @param failedAt 失敗日時
	 */
	public FailedLog(int failedLogId, String userId, Timestamp failedAt) {
		this.failedLogId = failedLogId;
		this.userId = userId;
		this.failedAt = failedAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/** ゲッター */
	public int getFailedLogId() {
		return failedLogId;
	}

	/** ゲッター */
	public String getUserId() {
		return userId;
	}

	/** ゲッター */
	public Timestamp getFailedAt() {
		return failedAt;
	}

	// 結果出力用
	@Override
	public String toString() {
		return "FailedLog [failedLogId=" + failedLogId + ", userId=" + userId + ", failedAt=" + failedAt + "]";
	}

}
