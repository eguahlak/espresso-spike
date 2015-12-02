package dk.kalhauge.grinder;

import dk.kalhauge.tokenizer.OperatorToken;
import dk.kalhauge.tokenizer.Token;
import dk.kalhauge.util.Ring;
import static dk.kalhauge.grinder.Grinder.*;

public class DyadicOperatorGrinder implements Grinder {
  private final String[] texts;

  public DyadicOperatorGrinder(String... texts) {
    this.texts = texts;
    }
  
  @Override
  public void grind(Ring<Branch> ring) {
    Token token = current(ring);
    if (token.is(OperatorToken.class, texts)) {
      Branch left = ring.getPrevious().detach();
      Branch right = ring.getNext().detach();
      ring.setData(new Tree(token, left, right));
      }
    // 
    }
  
  }
