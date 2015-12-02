package dk.kalhauge.tokenizer;

public class SpecialToken extends Token {
  private final String text;
  public static final SpecialToken INDEX = new SpecialToken("#");
  public static final SpecialToken SENTENCE = new SpecialToken("§");
  
  public SpecialToken(String text) {
    this.text = text;  
    }
  
  @Override
  public String getText() {
    return text;
    }

  @Override
  public String getType() {
    return "Special";
    }

  @Override
  public <T extends Token> boolean is(Class<T> type, String... values) {
    return type == SpecialToken.class && in(text, values);
    }

  @Override
  public boolean is(String... values) {
    return in(text, values);
    }

  @Override
  public boolean isLanguage() {
    return false;
    }

  @Override
  public boolean isSpecifier() {
    return true;
    }
  
  }
