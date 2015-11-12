package dk.kalhauge.grinder;

import dk.kalhauge.tokenizer.EndToken;
import dk.kalhauge.tokenizer.StartToken;
import dk.kalhauge.tokenizer.Token;
import dk.kalhauge.util.Ring;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public class Work implements Branch {
  private Token start;
  private Token end;
  private Collection<Work> more = new ArrayList<>();
  private Ring<Branch> branches;
  
  public Work(Iterator<Token> tokens) {
    this(new StartToken(), t -> t.is(EndToken.class), tokens);
    }

  Work(Token start, Predicate<Token> stop, Iterator<Token> tokens) {
    branches = new Ring(new Tree(start));
    this.start = start;
    while (tokens.hasNext()) {
      Token token = tokens.next();
      if (stop.test(token)) {
        end = token;
        break;
        }
      if (token.is(StartToken.class)) {
        Work work = new Work(token, t -> t.is(EndToken.class), tokens);
        more.add(work);
        branches.add(work);
        }
      else branches.add(new Tree(token));
      }
    }

  @Override
  public String toString() {
    return "W("+getToken()+")";
    }
  
  @Override public Token getToken() { return start; }

  public Ring<Branch> getBranches() {
    return branches;
    }

  @Override
  public String getText() {
    return start.getText(); //+end.getText();
    }
  
  public Token getEnd() { return end; }
  
  void process(Grinder grinder) {
    for (Work work : more) work.process(grinder);
    Ring ring = branches;
    do {
      grinder.grind(ring);
      ring = ring.getNext();
      }
    while (ring != branches);
    }

  @Override
  public void add(Branch... branches) {
    throw new UnsupportedOperationException("Work.add(...) Not supported!");
    }
  
  }
