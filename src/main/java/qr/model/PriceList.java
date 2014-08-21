package qr.model;

import java.util.ArrayList;
import java.util.List;

public class PriceList {
  
  List<PriceListItem> items;

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

}
