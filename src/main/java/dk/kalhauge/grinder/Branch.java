package dk.kalhauge.grinder;

import dk.kalhauge.tokenizer.Token;

public interface Branch {
  Branch getParent();
  void setParent(Branch value);
  Token getToken();

  Branch normalised();
  int getDepth();
  int getPosition();
  }
