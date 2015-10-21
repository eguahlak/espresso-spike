package dk.kalhauge.util;

import java.util.Collection;
import java.util.function.Function;

public class Tool {
  
  public static String joinWith(String delimiter, String... parts) {
    String result = null;
    for (String part : parts) {
      if (result == null) result = part;
      else result += delimiter+part;
      }
    return result;
    }
  
  public static <T> String joinWith(String delimiter, Function<T, String> f, Collection<T> parts) {
    String result = null;
    for (T part : parts) {
      if (result == null) result = f.apply(part);
      else result += delimiter+f.apply(part);
      }
    return result;
    }
  
  public static String camelize(String start, String... parts) {
    for (String part : parts) {
      start += part.substring(0, 1).toUpperCase()+part.substring(1);
      }
    return start;
    }
  
  public static<T> String camelize(String start, Function<T, String> f, Collection<T> parts) {
    for (T part : parts) {
      String text = f.apply(part);
      start += text.substring(0, 1).toUpperCase()+text.substring(1);
      }
    return start;
    }
  
  }
