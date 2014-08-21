package qr.model;

import java.math.BigDecimal;

public class Product {

  private String sku;
  private BigDecimal price;
  private BigDecimal rrp;
  private String name;
  private boolean selected;
  
  public Product() {
  }
  
  public Product(String sku, BigDecimal price, BigDecimal rrp, String name) {
    this.sku = sku;
    this.price = price;
    this.rrp = rrp;
    this.name = name;
    this.selected = true;
  }
  
  public String getSku() {
    return sku;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getRrp() {
    return rrp;
  }

  public String getName() {
    return name;
  }
  
  public boolean getSelected() {
    return selected;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public void setRrp(BigDecimal rrp) {
    this.rrp = rrp;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }
  
}
