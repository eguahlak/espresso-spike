package dk.kalhauge.grinder;

import dk.kalhauge.util.Ring;
import static dk.kalhauge.grinder.Grinder.*;
import dk.kalhauge.tokenizer.SpecialToken;
import dk.kalhauge.tokenizer.StartToken;
import dk.kalhauge.tokenizer.Token;

public class IndexGrinder implements Grinder {
  
  @Override
  public void grind(Ring<Branch> ring) {
    Token token = current(ring);
    if (!token.is(StartToken.class, "[]")) return;
    System.out.println("Index grinding "+token);
    Branch left = ring.getPrevious().detach();
    ring.setData(new Tree(SpecialToken.INDEX, left, ring.getData()));
    }
  
  }
