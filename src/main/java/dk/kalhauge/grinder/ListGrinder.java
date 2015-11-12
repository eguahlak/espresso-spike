package dk.kalhauge.grinder;

import dk.kalhauge.tokenizer.OperatorToken;
import dk.kalhauge.tokenizer.Token;
import dk.kalhauge.util.Ring;

public class ListGrinder implements Grinder {
  private String[] texts;

  public ListGrinder(String... texts) {
    this.texts = texts;
    }

  @Override
  public void grind(Ring<Branch> ring) {
    Token token = ring.getData().getToken();
    if (!token.is(OperatorToken.class, texts)) return;
    
    Branch left = ring.getPrevious().detach();
    Branch right = ring.getNext().detach();
    ring.setData(new Tree(token, left, right));
    }
  
  }
