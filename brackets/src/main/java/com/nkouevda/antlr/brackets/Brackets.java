package com.nkouevda.antlr.brackets;

import org.antlr.v4.runtime.misc.ParseCancellationException;

import com.nkouevda.antlr.util.ParserUtil;

public class Brackets {

  private Brackets() {
  }

  /**
   * Returns whether {@code input} consists of balanced brackets.
   *
   * <p>For example, {@code verify("(())")} returns true, but {@code verify("(")} returns false.
   *
   * @param input the {@code String} to parse and verify
   * @return whether {@code input} consists of balanced brackets
   */
  public static boolean verify(String input) {
    BracketsParser parser = ParserUtil.newParser(
        BracketsLexer::new, BracketsParser::new, input);

    try {
      parser.expression();
    } catch (ParseCancellationException e) {
      return false;
    }

    return true;
  }
}
