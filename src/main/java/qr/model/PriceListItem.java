package qr.model;

import java.math.BigDecimal;

public class PriceListItem {
  private String title;
  private BigDecimal rrp;
  private BigDecimal price;
  private String imageUrl;
  private byte[] QRBytes;
  
  public PriceListItem(){
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BigDecimal getRrp() {
    return rrp;
  }

  public void setRrp(BigDecimal rrp) {
    this.rrp = rrp;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public byte[] getQRBytes() {
    return QRBytes;
  }

  public void setQRBytes(byte[] QRBytes) {
    this.QRBytes = QRBytes;
  }

}
