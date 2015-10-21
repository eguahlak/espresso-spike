package dk.kalhauge.tokenizer;

public abstract class Token {
  public static final Class<? extends IdentifierToken> IDENTIFIER = IdentifierToken.class;
  public static final Class<? extends OperatorToken> OPERATOR = OperatorToken.class;
  public static final Class<? extends NumeralToken> NUMERAL = NumeralToken.class;
  public static final Class<? extends StringToken> STRING = StringToken.class;
  public static final Class<? extends CompositeToken> COMPOSITE = CompositeToken.class;
  
  public static boolean in(String pattern, String[] values) {
    //TODO: consider changing name of method
    if (values.length == 0) return true;
    for (String value : values) if (value.equals(pattern)) return true;
    return false;
    }
  
  public <T extends Token> T as(Class<T> type) {
    return (T)this;
    }
  
  public abstract <T extends Token> boolean is(Class<T> type, String... values);
  
  public abstract boolean is(String... values);
  
  public boolean is(Token other) {
    return false;
    }
  
  public boolean isWhitespace() { return false; }
  
  public boolean hasChildren() { 
    return children() != null && !children().isEmpty();
    }
  
  public TokenQueue children() { return null; }

  public abstract String joinWith(TokenQueue tokens);
  
  }
