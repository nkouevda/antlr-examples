package com.nkouevda.antlr.parentheses;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ParenthesesTest {

  @Test
  public void testNested() {
    String input = "()((()())())";
    Assert.assertTrue(Parentheses.verify(input));
  }

  @Test
  public void testWhitespace() {
    String input = " (\n)\t";
    Assert.assertTrue(Parentheses.verify(input));
  }

  @Test
  public void testEmpty() {
    String input = "";
    Assert.assertTrue(Parentheses.verify(input));
  }

  @Test
  public void testUnmatched() {
    String input = "(";
    Assert.assertFalse(Parentheses.verify(input));
  }

  @Test
  public void testInvalidCharacters() {
    String input = "(1)";
    Assert.assertFalse(Parentheses.verify(input));
  }
}
