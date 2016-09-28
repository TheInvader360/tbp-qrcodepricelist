package qr.model;

import java.util.ArrayList;
import java.util.List;

public class PriceList {
  
  List<PriceListItem> items;
  private byte[] QRBytes;

  public PriceList(){
    items = new ArrayList<PriceListItem>();
  }
  
  public List<PriceListItem> getItems() {
    return items;
  }

  public void setItems(List<PriceListItem> items) {
    this.items = items;
  }
  
  public void addItem(PriceListItem item) {
    items.add(item);
  }
  
  public byte[] getQRBytes() {
    return QRBytes;
  }

  public void setQRBytes(byte[] QRBytes) {
    this.QRBytes = QRBytes;
  }

}
