package mx.onekey.dev.cpaas.caller.database.codec;

public class HexEncoder {

    public static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();

        for (byte currentByte : bytes) {
            builder.append(Character.forDigit((currentByte & 0xF0) >> 4, 16));
            builder.append(Character.forDigit(currentByte & 0x0F, 16));
        }

        return builder.toString();
    }
}
