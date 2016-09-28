package qr.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import qr.EmptyListException;
import qr.Util;
import qr.model.PriceList;
import qr.model.PriceListItem;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PriceListPdfView extends AbstractPdfView {

  private static final double ABSOLUTE_MAX_ITEMS_PER_PAGE = 8;
  private static final int HEADER_ROW_HEIGHT_MULIPLIER = 2;
  private static final Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, Color.BLACK);
  private static final Font rrpLabelFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.GRAY);
  private static final Font rrpValueFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.STRIKETHRU, Color.GRAY);
  private static final Font priceLabelFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
  private static final Font priceValueFont = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, Color.BLACK);
  private float rowHeight;
  private Boolean altRow;

  @Override
  protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
    PriceList priceList = (PriceList) model.get("priceList");
    if (priceList.getItems().size() == 0) throw new EmptyListException("Cannot create PriceListPdfView for empty PriceList");

    List<PriceListItem> itemsToAdd = new ArrayList<PriceListItem>(priceList.getItems());
    double pageCount = Math.ceil(itemsToAdd.size()/ABSOLUTE_MAX_ITEMS_PER_PAGE);
    double calculatedMaxItemsPerPage = Math.ceil(itemsToAdd.size()/pageCount);
    
    for (int i=1; i<=pageCount; i++) {
      int itemCount = (int) Math.min(calculatedMaxItemsPerPage, itemsToAdd.size());
      List<PriceListItem> pageItems = itemsToAdd.subList(0, itemCount);
      createPage(doc, priceList, pageItems);
      itemsToAdd.subList(0, itemCount).clear();
    }
  }
  
  private void createPage(Document doc, PriceList priceList, List<PriceListItem> pageItems) throws DocumentException {
    PdfPTable table = new PdfPTable(5);
    table.setWidthPercentage(100.0f);
    table.setWidths(new float[] { 5.0f, 15.0f, 5.0f, 5.0f, 5.0f });
    
    rowHeight = calculateRowHeight(doc, pageItems.size() + HEADER_ROW_HEIGHT_MULIPLIER);

    altRow = false;
    addHeaderRow(table, priceList);
    
    altRow = !altRow;
    for (PriceListItem item : pageItems) {
      addPriceListRow(table, item);
      altRow = !altRow;
    }
    
    doc.add(table);
    doc.newPage();
  }
  
  private void addHeaderRow(PdfPTable table, PriceList priceList) {
    PdfPCell cell = buildQRCell(priceList.getQRBytes());
    cell.setColspan(5);
    cell.setFixedHeight(rowHeight * HEADER_ROW_HEIGHT_MULIPLIER);
    table.addCell(cell);
  }

  private void addPriceListRow(PdfPTable table, PriceListItem item) {
    table.addCell(buildImageCell(item));
    table.addCell(buildTitleCell(item));
    table.addCell(buildRrpCell(item));
    table.addCell(buildPriceCell(item));
    table.addCell(buildQRCell(item.getQRBytes()));
  }

  private PdfPCell buildImageCell(PriceListItem item) {
    Image image = null;
    try {image = Image.getInstance(item.getImageUrl());} catch (Exception e) {}
    return formattedImageCell(image);
  }

  private PdfPCell buildTitleCell(PriceListItem item) {
    PdfPCell cell = newCell();
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setPhrase(new Phrase(item.getTitle(), titleFont));
    return cell;
  }

  private PdfPCell buildRrpCell(PriceListItem item) {
    PdfPCell cell = newCell();
    Paragraph label = new Paragraph("RRP", rrpLabelFont);
    label.setAlignment(Element.ALIGN_CENTER);
    Paragraph value = new Paragraph(Util.gbpFormat(item.getRrp(), true), rrpValueFont);
    value.setAlignment(Element.ALIGN_CENTER);
    cell.addElement(label);
    cell.addElement(value);
    return cell;
  }

  private PdfPCell buildPriceCell(PriceListItem item) {
    PdfPCell cell = newCell();
    Paragraph label = new Paragraph("Our Price", priceLabelFont);
    label.setAlignment(Element.ALIGN_CENTER);
    Paragraph value = new Paragraph(Util.gbpFormat(item.getPrice(), false), priceValueFont);
    value.setAlignment(Element.ALIGN_CENTER);
    cell.addElement(label);
    cell.addElement(value);
    return cell;
  }
  
  private PdfPCell buildQRCell(byte[] QRBytes) {
    Image image = null;
    try {image = Image.getInstance(QRBytes);} catch (Exception e) {}
    return formattedImageCell(image);
  }
  
  private PdfPCell newCell() {
    Color color;
    if (altRow) color = Color.LIGHT_GRAY;
    else color = Color.WHITE;
    
    PdfPCell cell = new PdfPCell();
    cell.setBorderColor(color);
    cell.setBackgroundColor(color);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    cell.setPadding(5);
    cell.setFixedHeight(rowHeight);
    return cell;
  }
  
  private PdfPCell formattedImageCell(Image image) {
    PdfPCell cell = newCell();
    if (image != null) {
      image.setBorder(Rectangle.BOX);
      image.setBorderWidth(2);
      cell.setImage(image);
    }
    return cell;
  }
  
  private float calculateRowHeight(Document doc, int itemCount) {
    float docHeight = doc.top() - doc.bottom();
    return docHeight/itemCount;
  }

}
