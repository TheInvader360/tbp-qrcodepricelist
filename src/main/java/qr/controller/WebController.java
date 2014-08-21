package qr.controller;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import qr.EmptyListException;
import qr.model.FormBackingBean;
import qr.model.PriceList;
import qr.model.PriceListItem;
import qr.model.Product;
import qr.service.ProductService;
import qr.service.QRCodeService;

@Controller
@Component
@SessionAttributes("fbb")
public class WebController {

  private QRCodeService qrService;
  private ProductService prodService;

  @Autowired
  public void setQRCodeService(QRCodeService service) {
    this.qrService = service;
  }

  @Autowired
  public void setProductService(ProductService service) {
    this.prodService = service;
  }
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView viewHome(Model model) {
    FormBackingBean fbb = new FormBackingBean();
    fbb.setProducts(prodService.findAll());
    model.addAttribute("fbb", fbb);
    return new ModelAndView("home", "model", model);
  }
  
  @RequestMapping(value = "/form", method = RequestMethod.POST)
  public ModelAndView processForm(@ModelAttribute("fbb") FormBackingBean fbb) throws Exception {
    PriceList priceList = new PriceList();
    for (Product product: fbb.getProducts()) {
      if (product.getSelected()) {
        priceList.addItem(buildPriceListItem(fbb.getWarehouse(), product.getSku(), product.getPrice(), product.getRrp(), product.getName()));
      }
    }
    return new ModelAndView("priceListPdf", "priceList", priceList);
  }
  
  private PriceListItem buildPriceListItem(String warehouse, String sku, BigDecimal price, BigDecimal rrp, String title) throws Exception {
    PriceListItem item = new PriceListItem();
    item.setTitle(title);
    item.setRrp(rrp);
    item.setPrice(price);
    item.setImageUrl("http://commondatastorage.googleapis.com/tbpstatic/1/books/medium/" + sku + ".jpg");
    item.setQRBytes(qrService.getBytes("http://www.tbp.co.uk/store/" + warehouse + "/product/" + sku, 200));
    return item;
  }

  @ExceptionHandler(EmptyListException.class)
  public ModelAndView handleError(HttpServletRequest req, Exception exception) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("message", exception.getMessage());
    mav.setViewName("error");
    return mav;
  }
  
}
