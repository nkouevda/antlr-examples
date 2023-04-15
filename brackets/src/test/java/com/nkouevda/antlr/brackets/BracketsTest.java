package com.nkouevda.antlr.brackets;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BracketsTest {

  @Test
  public void testNested() {
    String input = "()<[{}()]()>";
    Assert.assertTrue(Brackets.verify(input));
  }

  @Test
  public void testWhitespace() {
    String input = " (\n)\t";
    Assert.assertTrue(Brackets.verify(input));
  }

  @Test
  public void testEmpty() {
    String input = "";
    Assert.assertTrue(Brackets.verify(input));
  }

  @Test
  public void testInterleaved() {
    String input = "([)]";
    Assert.assertFalse(Brackets.verify(input));
  }

  @Test
  public void testUnmatched() {
    String input = "(";
    Assert.assertFalse(Brackets.verify(input));
  }

  @Test
  public void testInvalidCharacters() {
    String input = "(1)";
    Assert.assertFalse(Brackets.verify(input));
  }
}
