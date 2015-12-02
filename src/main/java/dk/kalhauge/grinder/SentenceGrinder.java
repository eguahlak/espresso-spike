package dk.kalhauge.grinder;

import dk.kalhauge.tokenizer.SpecialToken;
import dk.kalhauge.tokenizer.Token;
import dk.kalhauge.util.Ring;
import static dk.kalhauge.grinder.Grinder.*;

public class SentenceGrinder implements Grinder {

  @Override
  public void grind(Ring<Branch> ring) {
    Token current = current(ring);
    if (!current.isSpecifier()) return;
    Token previous = previous(ring);
    if (!previous.isSpecifier()) return;
    System.out.println("Sentence grinding "+previous+" § "+current);
    Branch left = ring.getPrevious().detach();
    ring.setData(new Tree(SpecialToken.SENTENCE, left, ring.getData()));
    }
  
  }
