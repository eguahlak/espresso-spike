package dk.kalhauge.grinder;

import dk.kalhauge.tokenizer.Token;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Tree implements Branch {
  private final Token token;
  private List<Branch> branches;

  Tree(Token token, Branch... branches) {
    this.token = token;
    add(branches);
    }
  
  public final void add(Branch... branches) {
    for (Branch branch : branches) {
      if (this.branches == null) this.branches = new ArrayList<>();
      this.branches.add(branch);
      }
    }

  @Override
  public String toString() {
    return "T("+token+")";
    }
    
  @Override public Token getToken() { return token; }

  public List<Branch> getBranches() {
    if (branches == null) return Collections.EMPTY_LIST;
    return branches;
    }

  @Override
  public String getText() {
    return token.getText();
    }
  
  public boolean isLeaf() {
    return branches == null || branches.isEmpty();
    }

  }
