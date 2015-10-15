package dk.kalhauge.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

class Work extends Branch { 
  private Token end;
  private Node root;
  private Collection<Work> more = new ArrayList<>();
  
  Work(Iterator<Token> tokens) {
    this(null, Token.SOF, t -> t.hasValue("$"), tokens);
    }
  
  Work(Branch parent, Token start, Predicate<Token> stop, Iterator<Token> tokens) {
    super(parent, start);
    root = new Node(new Tree(null, start));
    while (tokens.hasNext()) {
      Token token = tokens.next();
      if (stop.test(token)) {
        end = token;
        break;
        }
      if (token.hasValue("(")) {
        Work work = new Work(this, token, t -> t.hasValue(")"), tokens);
        more.add(work);
        add(work);
        }
      else if (token.hasValue("[")) {
        Work work = new Work(this, token, t -> t.hasValue("}"), tokens);
        more.add(work);
        add(work);
        }
      else if (token.hasValue("{")) {
        Work work = new Work(this, token, t -> t.hasValue("}"), tokens);
        more.add(work);
        add(work);
        }
      else add(new Tree(this, token));
      }
    }

  @Override
  public String toString() {
    return "W("+getToken()+")";
    }
  
  private Node add(Branch branch) {
    return new Node(branch, root);
    }

  Token getStart() {
    return getToken();
    }
  
  Token getEnd() {
    return end;
    }
  
  void processInfixDyadics(String... values) {
    Node node = root;
    do {
      if (node.branch.hasTokenValue(values)) {
        Branch left = node.prunePrev();
        Branch right = node.pruneNext();
        node.branch = new Tree(this, node.branch.getToken(), left, right);
        }
      node = node.next;
      }
    while (node != root);
    more.stream().forEach(work -> work.processInfixDyadics(values));
    }

  void processPrefixDyadics(String... values) {
    Node node = root;
    do {
      if (node.branch.hasTokenValue(values)) {
        Branch left = node.pruneNext();
        Branch right = node.pruneNext();
        node.branch = new Tree(this, node.branch.getToken(), left, right);
        }
      node = node.next;
      }
    while (node != root);
    more.stream().forEach(work -> work.processInfixDyadics(values));
    }

  
  
  int count() {
    int count = -1;
    Node node = root;
    do {
      count++;
      node = node.next;
      }
    while (node != root);
    return count;
    }
  
  @Override
  Branch normalised() {
    if (count() == 1) return root.next.branch.normalised();
    Node node = root.next;
    while (node != root) {
      node.branch = node.branch.normalised();
      node.branch.setParent(this);
      node = node.next;
      }
    return this;
    }
  
  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
    Node node = root.next;
    do {
      node.branch.accept(visitor);
      node = node.next;
      }
    while (node != root);
    }

  @Override
  int reposition(int start) {
    Node node = root;
    do {
      start = node.branch.reposition(start);
      node = node.next;
      }
    while (node != root);
    end.setPosition(start);
    return start + 1;
    }
  
  private static class Node {
    Branch branch;
    Node prev;
    Node next;

    Node(Branch branch) {
      this.branch = branch;
      prev = this;
      next = this;
      }

    public Node(Branch branch, Node root) {
      this.branch = branch;
      this.next = root;
      this.prev = root.prev;
      root.prev.next = this;
      root.prev = this;
      }

    @Override
    public String toString() {
      return "N-"+branch;
      }
    
    Branch prunePrev() {
      Node node = prev;
      prev = node.prev;
      prev.next = this;
      return node.branch;
      }
    
    Branch pruneNext() {
      Node node = next;
      next = node.next;
      next.prev = this;
      return node.branch;
      }
    
    }
  
  }
