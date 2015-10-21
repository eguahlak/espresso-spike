package dk.kalhauge.tokenizer;

import dk.kalhauge.util.CharacterSource;
import java.io.IOException;

public class IdentifierToken extends Token implements Comparable<IdentifierToken> {
  private String value;
  
  public static boolean understands(CharacterSource input) throws IOException {
    return Character.isJavaIdentifierStart(input.peek());
    }
  
  public IdentifierToken(CharacterSource input) throws IOException {
    int ch = input.pop();
    value = Character.toString((char)ch);
    ch = input.pop();
    while (Character.isJavaIdentifierPart(ch)) {
      value += Character.toString((char)ch);
      ch = input.pop();
      }
    input.push(ch);
    }

  public IdentifierToken(String name) {
    this.value = name;
    }
  
  public String getValue() {
    return value;
    }

  @Override
  public String toString() {
    return value;
    }

  @Override
  public <T extends Token> boolean is(Class<T> type, String... values) {
    return type == IdentifierToken.class && in(value, values);
    }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof IdentifierToken)) return false;
    IdentifierToken other = (IdentifierToken)obj;
    return this.value.equals(other.value);
    }

  @Override
  public int hashCode() {
    return value.hashCode();
    }

  @Override
  public int compareTo(IdentifierToken other) {
    return value.compareTo(other.value);
    }

  @Override
  public boolean is(String... values) {
    return in(value, values);
    }
  
  }
