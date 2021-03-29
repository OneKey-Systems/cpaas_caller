package mx.onekey.dev.cpaas.caller.database.codec;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HexEncoderTest {

    @Test
    public void hexToStringTest() {
        byte[] testBytes = {14};
        assert HexEncoder.bytesToHex(testBytes).equals("0e");

        testBytes = new byte[]{127, 15, 32};
        assert HexEncoder.bytesToHex(testBytes).equals("7f0f20");
    }
}
