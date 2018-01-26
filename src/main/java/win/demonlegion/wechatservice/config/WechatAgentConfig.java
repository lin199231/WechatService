package win.demonlegion.wechatservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@ConfigurationProperties("wechat-agent")
@Component
public class WechatAgentConfig implements Serializable {
    private static final long serialVersionUID = 4015060929685191605L;

    private String authorizationRedirectUri;
    private String authorizationUrl;
    private String getAccessTokenUrl;
    private String refreshAccessTokenUrl;

    public String getAuthorizationRedirectUri() {
        return authorizationRedirectUri;
    }

    public void setAuthorizationRedirectUri(String authorizationRedirectUri) {
        this.authorizationRedirectUri = authorizationRedirectUri;
    }

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public void setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    public String getGetAccessTokenUrl() {
        return getAccessTokenUrl;
    }

    public void setGetAccessTokenUrl(String getAccessTokenUrl) {
        this.getAccessTokenUrl = getAccessTokenUrl;
    }

    public String getRefreshAccessTokenUrl() {
        return refreshAccessTokenUrl;
    }

    public void setRefreshAccessTokenUrl(String refreshAccessTokenUrl) {
        this.refreshAccessTokenUrl = refreshAccessTokenUrl;
    }
}
