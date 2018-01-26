package win.demonlegion.wechatservice.crypt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ByteGroup implements Serializable {
    private static final long serialVersionUID = -7862688751687109988L;

    private List<Byte> byteContainer = new ArrayList<>();

    public byte[] toBytes() {
        byte[] bytes = new byte[byteContainer.size()];
        for (int i = 0; i < byteContainer.size(); i++) {
            bytes[i] = byteContainer.get(i);
        }
        return bytes;
    }

    public ByteGroup addBytes(byte[] bytes) {
        for (byte b : bytes) {
            byteContainer.add(b);
        }
        return this;
    }

    public int size() {
        return byteContainer.size();
    }
}
