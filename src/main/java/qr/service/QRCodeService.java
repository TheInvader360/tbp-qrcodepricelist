package qr.service;

public interface QRCodeService {
  byte[] getBytes(String content, int size) throws Exception;
}
