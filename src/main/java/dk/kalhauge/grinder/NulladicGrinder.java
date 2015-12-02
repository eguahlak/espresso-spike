package dk.kalhauge.grinder;

import dk.kalhauge.tokenizer.OperatorToken;
import dk.kalhauge.tokenizer.Token;
import dk.kalhauge.util.Ring;
import static dk.kalhauge.grinder.Grinder.*;

public class NulladicGrinder implements Grinder {
  private final String[] texts;

  public NulladicGrinder(String... texts) {
    this.texts = texts;
    }
  
  @Override
  public void grind(Ring<Branch> ring) {
    Token token = current(ring);
    if (token.is(OperatorToken.class, texts) && previous(ring).isLanguage() && next(ring).isLanguage()) {
      // TODO: consider
      }
    
    
    
    }
  
  }
