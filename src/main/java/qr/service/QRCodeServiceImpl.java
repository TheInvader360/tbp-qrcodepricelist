package qr.service;

import java.io.ByteArrayOutputStream;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeServiceImpl implements QRCodeService {

  private static final String FORMAT = "png";

  @Override
  public byte[] getBytes(String content, int size) throws Exception {
    BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    MatrixToImageWriter.writeToStream(bitMatrix, FORMAT, out);
    return out.toByteArray();
  }

}
