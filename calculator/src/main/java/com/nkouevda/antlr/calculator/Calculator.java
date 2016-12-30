package com.nkouevda.antlr.calculator;

import org.antlr.v4.runtime.misc.ParseCancellationException;

import com.nkouevda.antlr.util.ParserUtil;

public class Calculator {

  private Calculator() {
  }

  /**
   * Returns the value of the given expression.
   *
   * <p>For example, {@code evaluate("1+2")} returns {@code 3.0}, but {@code evaluate("1+")} throws
   * an exception.
   *
   * @param input the {@code String} to parse and evaluate
   * @return the value of the given expression
   * @throws IllegalArgumentException if {@code input} is an invalid expression
   */
  public static double evaluate(String input) {
    CalculatorParser parser = ParserUtil.newParser(
        CalculatorLexer::new, CalculatorParser::new, input);
    ExpressionEvaluator evaluator = new ExpressionEvaluator();

    try {
      ParserUtil.parseAndWalk(parser::expression, evaluator);
    } catch (ParseCancellationException e) {
      throw new IllegalArgumentException("Invalid expression", e);
    }

    return evaluator.getValue();
  }
}
