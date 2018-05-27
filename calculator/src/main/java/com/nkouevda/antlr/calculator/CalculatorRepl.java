package com.nkouevda.antlr.calculator;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class CalculatorRepl {

  private CalculatorRepl() {
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Use ^C or ^D or \"exit\" or \"quit\" to exit");

    while (true) {
      System.out.print("> ");
      String input;

      try {
        input = scanner.nextLine().trim();
      } catch (NoSuchElementException e) {
        // ^D
        break;
      }

      if (input.isEmpty()) {
        continue;
      } else if (input.equals("exit") || input.equals("quit")) {
        break;
      }

      try {
        double output = Calculator.evaluate(input);
        System.out.println(output);
      } catch (IllegalArgumentException e) {
        System.out.println("error: " + e.getMessage());
      }
    }
  }
}
