package qr.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import qr.model.Product;

public class ProductServiceImpl implements ProductService {
  
  List<Product> products;
  
  public ProductServiceImpl() {
    products = new ArrayList<Product>();
    products.add(new Product("BPBS", new BigDecimal(20), new BigDecimal(140), "The World of Peter Rabbit Collection"));
    products.add(new Product("IMPY", new BigDecimal(9), new BigDecimal(48.93), "Wimpy Kid Collection"));
    products.add(new Product("LRIY", new BigDecimal(30), new BigDecimal(217), "Ladybird Read It Yourself"));
    products.add(new Product("MENP", new BigDecimal(15), new BigDecimal(60), "Mr Men & Little Miss Collection"));
    products.add(new Product("ORPU", new BigDecimal(15), new BigDecimal(79.84), "Michael Morpurgo Classic Collection"));
    products.add(new Product("CCFK", new BigDecimal(4), new BigDecimal(8.99), "Computer Coding for Kids"));
    products.add(new Product("FDOM", new BigDecimal(3), new BigDecimal(9.99), "First Illustrated Maths Dictionary"));
    products.add(new Product("FISD", new BigDecimal(3), new BigDecimal(6.99), "First Illustrated Science Dictionary"));
    products.add(new Product("TODS", new BigDecimal(5), new BigDecimal(29.99), "Toddler Songs Audio Collection"));
    products.add(new Product("REDS", new BigDecimal(4), new BigDecimal(7.99), "Minecraft: The Official Redstone Handbook"));
    products.add(new Product("SWYC", new BigDecimal(5), new BigDecimal(14.99), "LEGO Star Wars the Yoda Chronicles"));
    products.add(new Product("SWJA", new BigDecimal(5), new BigDecimal(21), "Save with Jamie"));
    products.add(new Product("HDEL", new BigDecimal(5), new BigDecimal(17.99), "The Hairy Dieters: Eat for Life"));
    products.add(new Product("CIRP", new BigDecimal(4), new BigDecimal(7), "Circus Playing Card Set"));
    products.add(new Product("MFAP", new BigDecimal(3), new BigDecimal(9.99), "My First Art Pack"));
    products.add(new Product("ORIF", new BigDecimal(4), new BigDecimal(14.99), "Origami Farm"));
    products.add(new Product("GMMA", new BigDecimal(7), new BigDecimal(20), "Guy Martin My Autobiography"));
    products.add(new Product("AFMA", new BigDecimal(3), new BigDecimal(25), "Alex Ferguson My Autobiography"));
    products.add(new Product("GAME", new BigDecimal(5), new BigDecimal(20.97), "The Hunger Games Collection - 3 Books"));
    products.add(new Product("QIFS", new BigDecimal(4), new BigDecimal(9.99), "1339 QI Facts to Make Your Jaw Drop"));
  }
  
  @Override
  public List<Product> findAll() {
    return products;
  }

}
