package dk.kalhauge.tokenizer;

import dk.kalhauge.util.CharacterSource;
import java.io.IOException;

public abstract class Token {
  public static boolean excludeWhitespace = true;
  public static final Class<? extends IdentifierToken> IDENTIFIER = IdentifierToken.class;
  public static final Class<? extends OperatorToken> OPERATOR = OperatorToken.class;
  public static final Class<? extends NumeralToken> NUMERAL = NumeralToken.class;
  public static final Class<? extends StringToken> STRING = StringToken.class;
  public static final Class<? extends StartToken> START = StartToken.class;
  public static final Class<? extends EndToken> END = EndToken.class;
  
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
  
  static Token read(CharacterSource input) throws IOException {
    if (excludeWhitespace) WhitespaceToken.trim(input);
    else if (WhitespaceToken.understands(input)) return new WhitespaceToken(input);
    if (EndToken.understands(input)) return new EndToken(input);
    if (IdentifierToken.understands(input)) return new IdentifierToken(input);
    if (NumeralToken.understands(input)) return new NumeralToken(input);
    if (OperatorToken.understands(input)) return new OperatorToken(input);
    if (StringToken.understands(input)) return new StringToken(input);
    throw new IncomprehensibleTokenException("Cannot understand character '"+(char)input.peek()+"' #"+input.peek());
    }


  
  }
