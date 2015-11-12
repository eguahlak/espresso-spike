package dk.kalhauge.grinder;

import dk.kalhauge.tokenizer.IdentifierToken;
import dk.kalhauge.tokenizer.SentenceToken;
import dk.kalhauge.tokenizer.Token;
import dk.kalhauge.util.Ring;

public class SentenceGrinder implements Grinder {

  @Override
  public void grind(Ring<Branch> ring) {
    Token token = ring.getData().getToken();
    if (!token.is(IdentifierToken.class) || token.isLanguage()) return;

    Ring<Branch> left = ring.getPrevious();
    Token leftToken = left.getData().getToken();
    if (leftToken.is(SentenceToken.class)) {
      left.getData().add(left.getNext().detach());
      }
    else {
      if (!leftToken.is(IdentifierToken.class) || leftToken.isLanguage()) return;
      ring.setData(new Tree(new SentenceToken(), left.detach(), ring.getData()));
      }
    }
  
  }
