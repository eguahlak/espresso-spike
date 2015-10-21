package dk.kalhauge.tokenizer;

import dk.kalhauge.util.CharacterSource;
import java.io.IOException;

public class WhitespaceToken extends Token {
  private String value;
  
  public static boolean understands(CharacterSource input) throws IOException {
    return Character.isWhitespace(input.peek());
    }

  public static void trim(CharacterSource input) throws IOException {
    while (Character.isWhitespace(input.peek())) input.pop();
    }
  
  public WhitespaceToken(CharacterSource input) throws IOException {
    int ch = input.pop();
    value = Character.toString((char)ch);
    ch = input.pop();
    while (Character.isWhitespace(ch)) {
      value += Character.toString((char)ch);
      ch = input.pop();
      }
    input.push(ch);
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
  
  }
