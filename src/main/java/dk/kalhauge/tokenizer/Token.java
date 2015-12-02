package dk.kalhauge.tokenizer;

// import dk.kalhauge.source.Position;
import dk.kalhauge.source.Source;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Token {
  public static boolean excludeWhitespace = true;
  public static final Class<? extends IdentifierToken> IDENTIFIER = IdentifierToken.class;
  public static final Class<? extends OperatorToken> OPERATOR = OperatorToken.class;
  public static final Class<? extends NumeralToken> NUMERAL = NumeralToken.class;
  public static final Class<? extends StringToken> STRING = StringToken.class;
  public static final Class<? extends StartToken> START = StartToken.class;
  public static final Class<? extends EndToken> END = EndToken.class;
  
//  private final Position sourcePosition;
  private int position = 0;
  
  Token(Source source) {
//  TODO: refactor
    }
  
  Token() {}

  @Override
  public String toString() {
    return (isLanguage() ? "£" : "")+getType()+" "+getText()+"["+position+"]";
    }

  public abstract String getText();
  
  public abstract String getType();
  
  public int getPosition() {
    return position;
    }

  public int setPosition(int value) {
    position = value;
    return value + getText().length();
    }
  
//  public Position getSourcePosition() {
//    return sourcePosition;
//    }
  
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
    
  public abstract boolean isLanguage();
  
  static Token read(Source source) throws IOException {
    if (excludeWhitespace) WhitespaceToken.trim(source);
    else if (WhitespaceToken.understands(source)) return new WhitespaceToken(source);
    if (StartToken.understands(source)) return new StartToken(source);
    if (EndToken.understands(source)) return new EndToken(source);
    if (IdentifierToken.understands(source)) return new IdentifierToken(source);
    if (NumeralToken.understands(source)) return new NumeralToken(source);
    if (OperatorToken.understands(source)) return new OperatorToken(source);
    if (StringToken.understands(source)) return new StringToken(source);
    throw new UnknownTokenException("Cannot understand character '"+(char)source.peek()+"' #"+source.peek());
    }

  public static Iterator<Token> iterate(Source source) {
    return new SourceIterator(source);
    }
  
  private static class SourceIterator implements Iterator<Token> {
    Source source;
    int position = 0;

    public SourceIterator(Source source) {
      this.source = source;
      }

    @Override
    public boolean hasNext() {
      try {
        return !source.isEmpty();
        } 
      catch (IOException ex) {
        Logger.getLogger(Token.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }
      }

    @Override
    public Token next() {
      try {
        Token token = Token.read(source);
        position = token.setPosition(position) + 1;
        return token;
        } 
      catch (IOException ex) {
        Logger.getLogger(Token.class.getName()).log(Level.SEVERE, null, ex);
        throw new RuntimeException("Ups!");
        }
      }
    
    }
  
  }
