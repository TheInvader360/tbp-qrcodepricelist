package qr;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Util {

  public static String generateRandomString(int length) {
    String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < length; i++) buffer.append(characters.charAt((int) (Math.random() * characters.length())));
    return buffer.toString();
  }
  
  public static String gbpFormat(BigDecimal amount, boolean displayPence) {
    NumberFormat gbpFormat = NumberFormat.getCurrencyInstance(Locale.UK);
    if (displayPence) {
      gbpFormat.setMinimumFractionDigits(2);
      gbpFormat.setMaximumFractionDigits(2);
    }
    else {
      gbpFormat.setMinimumFractionDigits(0);
      gbpFormat.setMaximumFractionDigits(0);
    }
    return gbpFormat.format(amount.doubleValue());
  }

}
