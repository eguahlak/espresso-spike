package dk.kalhauge.tokenizer;

import dk.kalhauge.util.CharacterSource;
import java.io.IOException;

public class NumeralToken extends Token {
  private String value;
  private boolean discrete = true;

  public static boolean understands(CharacterSource input) throws IOException {
    return Character.isDigit(input.peek());
    }
  
  public NumeralToken(CharacterSource input) throws IOException {
    int ch = input.pop();
    value = Character.toString((char)ch);
    ch = input.pop();
    while (Character.isDigit(ch)) {
      value += Character.toString((char)ch);
      ch = input.pop();
      }
    if (ch == '.') {
      discrete = false;
      value += ".";
      ch = input.pop();
      while (Character.isDigit(ch)) {
        value += Character.toString((char)ch);
        ch = input.pop();
        }
      }
    if (ch == 'e' || ch == 'E') {
      discrete = false;
      value += "E";
      ch = input.pop();
      if (ch == '-') {
        value += "-";
        ch = input.pop();
        }
      else if (ch == '+') ch = input.pop();
      while (Character.isDigit(ch)) {
        value += Character.toString((char)ch);
        ch = input.pop();
        }
      }
    input.push(ch);
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
  
  }
