package com.nkouevda.antlr.calculator;

import java.util.Stack;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import com.google.common.base.Preconditions;

class ExpressionEvaluator extends CalculatorBaseListener {

  private final Stack<Double> values;

  private boolean started;
  private boolean finished;

  ExpressionEvaluator() {
    values = new Stack<>();
    started = false;
    finished = false;
  }

  double getValue() {
    Preconditions.checkState(started, "Have not yet started evaluating an expression");
    Preconditions.checkState(finished, "Have not yet finished evaluating an expression");
    return values.peek();
  }

  @Override
  public void enterExpression(CalculatorParser.ExpressionContext context) {
    Preconditions.checkState(!finished, "Already finished evaluating an expression");
    Preconditions.checkState(!started, "Already started evaluating an expression");
    started = true;
  }

  @Override
  public void exitExpression(CalculatorParser.ExpressionContext context) {
    finished = true;
  }

  @Override
  public void exitAddition(CalculatorParser.AdditionContext context) {
    operate((left, right) -> left + right);
  }

  @Override
  public void exitSubtraction(CalculatorParser.SubtractionContext context) {
    operate((left, right) -> left - right);
  }

  @Override
  public void exitMultiplication(CalculatorParser.MultiplicationContext context) {
    operate((left, right) -> left * right);
  }

  @Override
  public void exitDivision(CalculatorParser.DivisionContext context) {
    operate((left, right) -> left / right);
  }

  @Override
  public void exitModulo(CalculatorParser.ModuloContext context) {
    operate((left, right) -> left % right);
  }

  @Override
  public void exitNegation(CalculatorParser.NegationContext context) {
    operate(value -> -value);
  }

  @Override
  public void exitExponentiation(CalculatorParser.ExponentiationContext context) {
    operate(Math::pow);
  }

  @Override
  public void exitNumber(CalculatorParser.NumberContext context) {
    values.push(Double.valueOf(context.getText()));
  }

  private void operate(UnaryOperator<Double> operator) {
    values.push(operator.apply(values.pop()));
  }

  private void operate(BinaryOperator<Double> operator) {
    Double right = values.pop();
    Double left = values.pop();
    values.push(operator.apply(left, right));
  }
}
