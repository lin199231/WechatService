package win.demonlegion.wechatservice.common;

/**
 * 接口返回码
 */
public enum HttpCode {
	/** 1 请求成功 */
	OK(1, "请求成功"),

    /** -1  请求出错 **/
    ERROR(-1, "请求出错"),

	/** 10001 调用微信服务失败 **/
	REQUEST_WECHAT_SERVICE_ERROR(10001, "调用微信服务失败"),

	/** 10002 传入字段不完整 **/
	NOT_COMPLETE_FIELD(10002, "传入字段不完整"),

	/** 10003 用户拒绝微信授权登录 **/
	WECHAT_USER_REFUSE(10003, "用户拒绝微信授权登录"),

	/** 10004 用户未绑定微信账号 **/
	USER_UNBIND_WECHAT(10004, "用户未绑定微信账号"),

	/** 10005 微信授权失败 **/
	WECHAT_AUTH_ERROR(10005, "微信授权失败"),

	/** 10006 用户未授权 **/
	USER_NOT_AUTH(10006, "用户未授权"),

	/** 10007 未传入微信登录获取凭据 **/
	EMPTY_WECHAT_STATE(10007, "未传入微信登录获取凭据"),

	/** 10008 state过期 **/
	STATE_EXPIRE(10008, "state过期");


	private final int value;
	private final String message;

	public int getValue() {
		return value;
	}

	public String getMessage() {
	    return message;
    }

    HttpCode(int value, String message) {
        this.value = value;
        this.message = message;
    }
}
