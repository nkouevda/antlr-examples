package com.nkouevda.antlr.calculator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculatorTest {

  @Test
  public void testNumber() {
    String input = "123.456";
    Assert.assertEquals(Calculator.evaluate(input), 123.456);
  }

  @Test
  public void testAssociativity() {
    String input = "9-3-2";
    Assert.assertEquals(Calculator.evaluate(input), 4.0);
  }

  @Test
  public void testNegation() {
    String input = "-(2+3)";
    Assert.assertEquals(Calculator.evaluate(input), -5.0);
  }

  @Test
  public void testOperatorPrecedence() {
    String input = "1+2^3*4-5/((-6*-7+8)%9)";
    Assert.assertEquals(Calculator.evaluate(input), 32.0);
  }

  @Test
  public void testWhitespace() {
    String input = "\n( ((-3\n^ 2)\t)) ";
    Assert.assertEquals(Calculator.evaluate(input), -9.0);
  }

  @Test
  public void testEmpty() {
    String input = "";
    Assert.assertThrows(IllegalArgumentException.class, () -> Calculator.evaluate(input));
  }

  @Test
  public void testUnmatched() {
    String input = "(1";
    Assert.assertThrows(IllegalArgumentException.class, () -> Calculator.evaluate(input));
  }

  @Test
  public void testInvalidCharacters() {
    String input = "3?";
    Assert.assertThrows(IllegalArgumentException.class, () -> Calculator.evaluate(input));
  }
}
