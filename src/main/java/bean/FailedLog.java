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

	public FailedLog(int failedLogId, String userId, Timestamp failedAt) {
		this.failedLogId = failedLogId;
		this.userId = userId;
		this.failedAt = failedAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getFailedLogId() {
		return failedLogId;
	}

	public String getUserId() {
		return userId;
	}

	public Timestamp getFailedAt() {
		return failedAt;
	}

	@Override
	public String toString() {
		return "FailedLog [failedLogId=" + failedLogId + ", userId=" + userId + ", failedAt=" + failedAt + "]";
	}

}
