package dk.kalhauge.parser;

import dk.kalhauge.util.Path;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParserTest {
  
  @Test
  public void testSimpleMinusPlus() {
    Work work = new Work(pathOf("2 - 3 + 5").iterator());
    work.processInfixDyadics("+", "-");
    Branch tree = work.normalised();
    assertThat(Compressor.compress(tree), is("+(-(2 3)5)"));
    }
  
  @Test
  public void testSimpleParanthesis() {
    Work work = new Work(pathOf("3 * ( 2 + ( 2 * 5 - 3 ) * 7 ) - 2").iterator());
    work.processInfixDyadics("*", "/");
    work.processInfixDyadics("+", "-");
    Branch tree = work.normalised();
    assertThat(Compressor.compress(tree), is("-(*(3 +(2 *(-(*(2 5)3)7)))2)"));
    }

  @Test
  public void testSimpleStatement() {
    Work work = new Work(pathOf("if ( 3 + a ) * 7 > 8 { a = 56 ; y } else z").iterator());
    work.processInfixDyadics("*", "/");
    work.processInfixDyadics("+", "-");
    work.processInfixDyadics(">", "<", ">=", "<=", "==", "!=");
    work.processInfixDyadics("=");
    work.processInfixDyadics(",", ";");
    work.processPrefixDyadics("if", "while");
    work.processInfixDyadics("else");
    Branch tree = work.normalised();
    assertThat(Compressor.compress(tree), is("else(if(>(*(+(3 a)7)8);(=(a 56)y))z)"));
  }

  private static Path<Token> pathOf(String text) {
    String[] values = (text + " $").split(" ");
    Path<Token> path = null;
    for (int i = values.length - 1; i >= 0; i--) {
      Token token = new Token(values[i], i + 1);
      path = new Path<>(token, path);
      }
    return path;
    }

  
  }
