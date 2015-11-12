package dk.kalhauge.grinder;

import dk.kalhauge.tokenizer.Token;

public interface Branch {
  Token getToken();
  String getText();
  void add(Branch... branches);
  }
