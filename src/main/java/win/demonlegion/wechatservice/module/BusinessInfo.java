package win.demonlegion.wechatservice.module;

import java.io.Serializable;

/**
 * 授权方功能开通状况
 */
public class BusinessInfo implements Serializable {
    private static final long serialVersionUID = 1259823746517064889L;

    private int open_store;
    private int open_scan;
    private int open_pay;
    private int open_card;
    private int open_shake;

    public int getOpen_store() {
        return open_store;
    }

    public void setOpen_store(int open_store) {
        this.open_store = open_store;
    }

    public int getOpen_scan() {
        return open_scan;
    }

    public void setOpen_scan(int open_scan) {
        this.open_scan = open_scan;
    }

    public int getOpen_pay() {
        return open_pay;
    }

    public void setOpen_pay(int open_pay) {
        this.open_pay = open_pay;
    }

    public int getOpen_card() {
        return open_card;
    }

    public void setOpen_card(int open_card) {
        this.open_card = open_card;
    }

    public int getOpen_shake() {
        return open_shake;
    }

    public void setOpen_shake(int open_shake) {
        this.open_shake = open_shake;
    }
}
