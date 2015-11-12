package dk.kalhauge.grinder;

import dk.kalhauge.util.Ring;

public class PrintGrinder implements Grinder {

  @Override
  public void grind(Ring<Branch> ring) {
    System.out.println("Grinding: "+ring.getData());
    }
  
  }
