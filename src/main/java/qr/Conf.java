package qr;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.XmlViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import qr.service.ProductService;
import qr.service.ProductServiceImpl;
import qr.service.QRCodeService;
import qr.service.QRCodeServiceImpl;

@Configuration
@EnableWebMvc
public class Conf {
  
  @Autowired
  private ServletContext servletContext;

  @Bean
  public QRCodeService getQRCodeService() {
    return new QRCodeServiceImpl();
  }
  
  @Bean
  public ProductService getProductService() {
    return new ProductServiceImpl();
  }
  
  @Bean
  public ViewResolver htmlResolver() {
    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    resolver.setTemplateEngine(templateEngine());
    resolver.setOrder(0);
    resolver.setViewNames(new String[]{"*"});
    resolver.setExcludedViewNames(new String[]{"*Pdf"});
    resolver.setCache(false);
    return resolver;
  }

  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.setTemplateResolver(templateResolver());
    return engine;
  }

  @Bean
  public TemplateResolver templateResolver() {
    ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
    resolver.setPrefix("WEB-INF/templates/");
    resolver.setSuffix(".html");
    resolver.setTemplateMode("HTML5");
    resolver.setCacheable(false);
    return resolver;
  }

  @Bean public ViewResolver pdfResolver() {
    XmlViewResolver resolver = new XmlViewResolver();
    resolver.setLocation(new ServletContextResource(servletContext, "WEB-INF/spring-pdf-views.xml"));
    resolver.setOrder(1);
    resolver.setCache(false);
    return resolver;
  }

}
