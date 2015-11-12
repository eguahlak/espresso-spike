package dk.kalhauge.grinder;

import dk.kalhauge.tokenizer.OperatorToken;
import dk.kalhauge.tokenizer.Token;
import dk.kalhauge.util.Ring;

public class DyadicOperationGrinder implements Grinder {
  private final String[] texts;

  public DyadicOperationGrinder(String... texts) {
    this.texts = texts;
    }
  
  @Override
  public void grind(Ring<Branch> ring) {
    Token token = ring.getData().getToken();
    if (token.is(OperatorToken.class, texts)) {
      Branch left = ring.getPrevious().detach();
      Branch right = ring.getNext().detach();
      ring.setData(new Tree(token, left, right));
      }
    // 
    }
  
  }
