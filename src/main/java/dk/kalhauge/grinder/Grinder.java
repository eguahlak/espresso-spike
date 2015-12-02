package dk.kalhauge.grinder;

import dk.kalhauge.tokenizer.Token;
import dk.kalhauge.util.Ring;

public interface Grinder {
  void grind(Ring<Branch> ring);
  
  static Token previous(Ring<Branch> ring) {
    return ring.getPrevious().getData().getToken();
    }
  
  static Token next(Ring<Branch> ring) {
    return ring.getNext().getData().getToken();
    }
  
  static Token current(Ring<Branch> ring) {
    return ring.getData().getToken();
    }
  
  
  }
