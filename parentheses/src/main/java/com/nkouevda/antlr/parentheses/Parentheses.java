package com.nkouevda.antlr.parentheses;

import org.antlr.v4.runtime.misc.ParseCancellationException;

import com.nkouevda.antlr.util.ParserUtil;

public class Parentheses {

  private Parentheses() {
  }

  /**
   * Returns whether {@code input} consists of balanced parentheses.
   *
   * <p>For example, {@code verify("(())")} returns true, but {@code verify("(")} returns false.
   *
   * @param input the {@code String} to parse and verify
   * @return whether {@code input} consists of balanced parentheses
   */
  public static boolean verify(String input) {
    ParenthesesParser parser = ParserUtil.newParser(
        ParenthesesLexer::new, ParenthesesParser::new, input);

    try {
      parser.expression();
    } catch (ParseCancellationException e) {
      return false;
    }

    return true;
  }
}
