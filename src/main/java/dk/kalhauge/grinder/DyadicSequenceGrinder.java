package dk.kalhauge.grinder;

import dk.kalhauge.util.Ring;
import static dk.kalhauge.grinder.Grinder.*;
import dk.kalhauge.tokenizer.StartToken;
import dk.kalhauge.tokenizer.Token;

public class DyadicSequenceGrinder implements Grinder {
  private final String[] texts;

  public DyadicSequenceGrinder(String... texts) {
    this.texts = texts;
    }
  
  @Override
  public void grind(Ring<Branch> ring) {
    Token token = current(ring);
    if (!token.is(StartToken.class, texts)) return;
    System.out.println("Dyadic sequence grinding "+token);
    Branch left = ring.getPrevious().detach();
    ring.setData(new Tree(token, left, ring.getData()));
    }
  
  }
