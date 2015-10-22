package dk.kalhauge.tokenizer;

import dk.kalhauge.source.Source;
import java.io.IOException;

public class NumeralToken extends Token {
  private String value;
  private boolean discrete = true;

  public static boolean understands(Source input) throws IOException {
    return Character.isDigit(input.peek());
    }
  
  public NumeralToken(Source source) throws IOException {
    super(source);
    int ch = source.pop();
    value = Character.toString((char)ch);
    ch = source.pop();
    while (Character.isDigit(ch)) {
      value += Character.toString((char)ch);
      ch = source.pop();
      }
    if (ch == '.') {
      discrete = false;
      value += ".";
      ch = source.pop();
      while (Character.isDigit(ch)) {
        value += Character.toString((char)ch);
        ch = source.pop();
        }
      }
    if (ch == 'e' || ch == 'E') {
      discrete = false;
      value += "E";
      ch = source.pop();
      if (ch == '-') {
        value += "-";
        ch = source.pop();
        }
      else if (ch == '+') ch = source.pop();
      while (Character.isDigit(ch)) {
        value += Character.toString((char)ch);
        ch = source.pop();
        }
      }
    source.push(ch);
    }

  public String getValue() {
    return value;
    }

  public int getIntValue() {
    return Integer.parseInt(value);
    }
  
  public boolean isDiscrete() {
    return discrete;
    }

  @Override
  public String toString() {
    return value+": "+(discrete ? "Integer" : "Real");
    }

  @Override
  public <T extends Token> boolean is(Class<T> type, String... values) {
    return type == NumeralToken.class && (values.length == 0 || value.equals(values[0]));
    }

  @Override
  public boolean is(String... values) {
    // See StringToken
    return false;
    }

  @Override
  public String getText() {
    return value;
    }
  
  }
