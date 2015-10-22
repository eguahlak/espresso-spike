package dk.kalhauge.tokenizer;

import dk.kalhauge.source.Source;
import java.io.IOException;

public class WhitespaceToken extends Token {
  private String value;
  
  public static boolean understands(Source source) throws IOException {
    return Character.isWhitespace(source.peek());
    }

  public static void trim(Source source) throws IOException {
    while (Character.isWhitespace(source.peek())) source.pop();
    }
  
  public WhitespaceToken(Source source) throws IOException {
    super(source);
    int ch = source.pop();
    value = Character.toString((char)ch);
    ch = source.pop();
    while (Character.isWhitespace(ch)) {
      value += Character.toString((char)ch);
      ch = source.pop();
      }
    source.push(ch);
    }
  
  public String getValue() { return value; }

  @Override
  public boolean isWhitespace() { return true; }
  
  @Override
  public String toString() {
    return "Whitespace:     "+value.length()+" character"+(value.length() != 1 ? "s" : "")+ " long";
    }

  @Override
  public <T extends Token> boolean is(Class<T> type, String... values) {
    return type == WhitespaceToken.class;
    }

  @Override
  public boolean is(String... values) {
    return in(value, values);
    }

  @Override
  public String getText() {
    return "";
    }
  
  }
