package dk.kalhauge.tokenizer;

public class SentenceToken extends Token {

  @Override
  public String getText() {
    return "§";
    }

  @Override
  public String getType() {
    return "Sentence";
    }

  @Override
  public <T extends Token> boolean is(Class<T> type, String... values) {
    return type == SentenceToken.class && in("§", values);
    }

  @Override
  public boolean is(String... values) {
    return in("§", values);
    }

  @Override
  public boolean isLanguage() {
    return false;
    }
  
  }
