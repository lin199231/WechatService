package win.demonlegion.wechatservice.module;

import java.io.Serializable;

/**
 * 微信公众号授权信息
 */
public class WechatFunctionInfo implements Serializable {
    private static final long serialVersionUID = 2656093362783679020L;

    private FunctionScopeCategory funcscope_category;

    public FunctionScopeCategory getFuncscope_category() {
        return funcscope_category;
    }

    public void setFuncscope_category(FunctionScopeCategory funcscope_category) {
        this.funcscope_category = funcscope_category;
    }
}
