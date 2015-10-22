package dk.kalhauge.tokenizer;

public class UnknownTokenException extends RuntimeException {
  
  // TODO: add notion of whereabouts
  
  public UnknownTokenException(String message) {
    super(message);
    }

  }
