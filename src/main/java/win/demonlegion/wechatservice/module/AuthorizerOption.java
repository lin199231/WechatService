package win.demonlegion.wechatservice.module;

import java.io.Serializable;

/**
 * 授权方的选项设置信息
 */
public class AuthorizerOption implements Serializable {
    private static final long serialVersionUID = -8177249092487113423L;

    public static final String OPTION_NAME_LOCATION_REPORT = "location_report";
    public static final String OPTION_NAME_VOICE_RECOGNIZE = "voice_recognize";
    public static final String OPTION_NAME_CUSTOMER_SERVICE = "customer_service";

    public static final String[] OPTION_LOCATION_REPORT_DETAIL = {"无上报", "进入会话时上报", "每5s上报"};
    public static final String[] OPTION_VOICE_RECOGNIZE_DETAIL = {"关闭语音识别", "开启语音识别"};
    public static final String[] OPTION_CUSTOMER_SERVICE_DETAIL = {"关闭多客服", "开启多客服"};

    public static final int OPTION_CLOSE_LOCATION_REPORT = 0;
    public static final int OPTION_ONCE_LOCATION_REPORT = 1;
    public static final int OPTION_TIMELY_LOCATION_REPORT = 2;
    public static final int OPTION_CLOSE_VOICE_RECOGNIZE = 0;
    public static final int OPTION_OPEN_VOICE_RECOGNIZE = 1;
    public static final int OPTION_CLOSE_CUSTOMER_SERVICE = 0;
    public static final int OPTION_OPEN_CUSTOMER_SERVICE = 1;

    private String option_name;
    private int option_value;
    private String option_detail;

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }

    public int getOption_value() {
        return option_value;
    }

    public void setOption_value(int option_value) {
        this.option_value = option_value;
        switch (option_name != null ? option_name : "") {
            case OPTION_NAME_LOCATION_REPORT:
                if(option_value < OPTION_LOCATION_REPORT_DETAIL.length)
                    this.option_detail = OPTION_LOCATION_REPORT_DETAIL[option_value];
                break;
            case OPTION_NAME_VOICE_RECOGNIZE:
                if(option_value < OPTION_VOICE_RECOGNIZE_DETAIL.length)
                    this.option_detail = OPTION_VOICE_RECOGNIZE_DETAIL[option_value];
                break;
            case OPTION_NAME_CUSTOMER_SERVICE:
                if(option_value < OPTION_CUSTOMER_SERVICE_DETAIL.length)
                    this.option_detail = OPTION_CUSTOMER_SERVICE_DETAIL[option_value];
                break;
        }
    }

    public String getOption_detail() {
        return option_detail;
    }

    public void setOption_detail(String option_detail) {
        this.option_detail = option_detail;
    }
}
