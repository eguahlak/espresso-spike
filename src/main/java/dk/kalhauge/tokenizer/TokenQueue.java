package dk.kalhauge.tokenizer;

import dk.kalhauge.util.CharacterStack;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TokenQueue {
  public static boolean excludeWhitespace = true;
  private List<Token> tokens = new ArrayList<>();
  private int index = 0;
  
  public TokenQueue() { }

  @Override
  public String toString() {
    if (isEmpty()) return "ε";
    if (size() == 1) return peek().toString();
    return peek().toString()+" ...";
    }
  
  public void add(Token token) { tokens.add(token); }
  
  public TokenQueue(CharacterStack input, Token stop) throws IOException {
    while (true) {
      Token token = read(input);
      if (token.is(stop)) break;
      tokens.add(token);
      }
    }
  
  public TokenQueue(CharacterStack input) throws IOException {
    this(input, new EndToken());
    }

  public boolean isEmpty() {
    return size() == 0;
    }

  public int size() {
    return tokens.size() - index;
    }
  
  private Token read(CharacterStack input) throws IOException {
    if (excludeWhitespace) WhitespaceToken.trim(input);
    else if (WhitespaceToken.understands(input)) return new WhitespaceToken(input);
    if (EndToken.understands(input)) return new EndToken(input);
    if (IdentifierToken.understands(input)) return new IdentifierToken(input);
    if (NumeralToken.understands(input)) return new NumeralToken(input);
    if (OperatorToken.understands(input)) return new OperatorToken(input);
    if (StringToken.understands(input)) return new StringToken(input);
    if (CompositeToken.understands(input)) return new CompositeToken(input);
    throw new IncomprehensibleTokenException("Cannot understand character '"+(char)input.peek()+"' #"+input.peek());
    }

  public Token pop() {
    if (index >= tokens.size()) return new EndToken();
    return tokens.get(index++);
    }

  public <T extends Token> T pop(Class<T> type) {
    return (T)pop();
    }

  public <T extends Token> List<T> popList(Class<T> type, String delimiter) {
    List<T> list = new ArrayList<>();
    list.add(pop(type));
    while (peek(OperatorToken.class, delimiter)) {
      pop();
      list.add(pop(type));
      }
    return list;
    }

  public List<Token> popList(String delimiter) {
    // TODO: remove if never used
    throw new UnsupportedOperationException("Not supported yet.");
    }
  
  public TokenQueue popTo(String... patterns) {
    TokenQueue popped = new TokenQueue();
    while (!isEmpty() && !peek().is(patterns)) popped.add(pop());
    return popped;
    }

  public List<TokenQueue> split(String delimiter) {
    List<TokenQueue> list = new ArrayList<>();
    while (!isEmpty()) {
      TokenQueue split = new TokenQueue();
      Token token;
      while (!(isEmpty() || (token = pop()).is(OperatorToken.class, delimiter))) 
          split.add(token);
      list.add(split);
      }
    return list;
    }

  public Token peek(int position) {
    return tokens.get(index + position);
    }
  
  public Token peek() {
    return tokens.get(index);
    }

  public <T extends Token> T peek(Class<T> type) {
    return (T)peek();
    }
  
  public <T extends Token> boolean peek(int position, Class<T> type, String... values) {
    return tokens.get(index + position).is(type, values);
    }

  public <T extends Token> boolean peek(Class<T> type, String... values) {
    return tokens.get(index).is(type, values);
    }

  public boolean back() {
    if (index == 0) return false;
    index--;
    return true;
    }

  public String joined() {
    return pop().joinWith(this);
    }
  
  }