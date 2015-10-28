package dk.kalhauge.tokenizer;

import dk.kalhauge.source.Source;
import java.io.IOException;

public class StringToken extends Token {
  private final int quote;
  private String value = "";
  
  private static boolean isQuote(int ch) {
    return ch == '\'' || ch == '"';
    }
  
  public static boolean understands(Source input) throws IOException {
    return isQuote(input.peek());
    }

  public StringToken(Source source) throws IOException {
    super(source);
    quote = source.pop();
    int ch = source.pop();
    while (ch != quote) {
      if (ch == '\\') {
        throw new RuntimeException("Escape sequence found in '"+value+"', escape sequences are not implemented");
        }
      value += (char)ch;
      ch = source.pop();
      }
    }

  public String getValue() { return value; }

  public String getQuote() { return ""+(char)quote; }

  @Override
  public <T extends Token> boolean is(Class<T> type, String... values) {
    return type == StringToken.class && (values.length == 0 || value.equals(values[0]));
    }

  @Override
  public boolean is(String... values) {
    // matching literals does not make sense at this point
    return false;
    }

  @Override
  public String getText() {
    return ""+(char)quote+value+(char)quote;
    }
  
  }
