package dk.kalhauge.tokenizer;

import dk.kalhauge.source.Source;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IdentifierToken extends Token implements Comparable<IdentifierToken> {
  private String value;
  private static Set<String> keywords = null;

  private static final String[] identifierKeywords = {
    "class", "state", "when", "if", "else", "while", "do", "for", "as", "is"
    };

  public static boolean understands(Source input) throws IOException {
    return Character.isJavaIdentifierStart(input.peek());
    }
  
  public IdentifierToken(Source source) throws IOException {
    super(source);
    int ch = source.pop();
    value = Character.toString((char)ch);
    ch = source.pop();
    while (Character.isJavaIdentifierPart(ch)) {
      value += Character.toString((char)ch);
      ch = source.pop();
      }
    source.push(ch);
    }

  public String getValue() {
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

  @Override
  public String getText() {
    return value;
    }

  @Override
  public boolean isLanguage() {
    if (keywords == null) {
      keywords = new HashSet<>();
      keywords.addAll(Arrays.asList(identifierKeywords));
      }
    return keywords.contains(value);
    }
  
  }
