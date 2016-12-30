package com.nkouevda.antlr.util;

import java.util.function.Function;
import java.util.function.Supplier;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Utilities for creating and using {@link Parser}s.
 *
 * <h3>Usage</h3>
 *
 * <p>Consider the following example of parsing some {@code String input} and then walking the
 * resultant {@link ParseTree}:
 *
 * <pre>{@code
 *   CharStream charStream = new ANTLRInputStream(input);
 *   FooLexer lexer = new FooLexer(charStream);
 *   lexer.removeErrorListeners();
 *
 *   TokenStream tokenStream = new CommonTokenStream(lexer);
 *   FooParser parser = new FooParser(tokenStream);
 *   parser.setErrorHandler(new BailErrorStrategy());
 *   parser.removeErrorListeners();
 *
 *   ParseTree parseTree = parser.bar();
 *   FooListener listener = new FooListener();
 *   ParseTreeWalker.DEFAULT.walk(listener, parseTree);
 * }</pre>
 *
 * <p>With {@code ParserUtil}, this can be written much more concisely:
 *
 * <pre>{@code
 *   FooParser parser = ParserUtil.newParser(FooLexer::new, FooParser::new, input);
 *   FooListener listener = new FooListener();
 *   ParserUtil.parseAndWalk(parser::bar, listener);
 * }</pre>
 *
 * <h3>Limitations</h3>
 *
 * <p>{@code ParserUtil} does not provide methods for creating {@code Parser}s without specifying
 * {@code String input}. Although {@link Lexer}s and {@code Parser}s are reusable (see {@link
 * Lexer#setInputStream} and {@link Parser#setTokenStream}), ANTLR requires a {@link CharStream} to
 * instantiate a {@code Lexer}, and a {@link TokenStream} to instantiate a {@code Parser}, so an
 * initial {@code String input} must be provided.
 */
public class ParserUtil {

  private ParserUtil() {
  }

  public static <L extends Lexer, P extends Parser> P newParser(
      Function<CharStream, L> lexerFactory, Function<TokenStream, P> parserFactory, String input) {
    return newParser(lexerFactory, parserFactory, input, true, true);
  }

  public static <L extends Lexer, P extends Parser> P newParser(
      Function<CharStream, L> lexerFactory,
      Function<TokenStream, P> parserFactory,
      String input,
      boolean useBailErrorStrategy,
      boolean removeErrorListeners) {
    CharStream charStream = new ANTLRInputStream(input);
    L lexer = lexerFactory.apply(charStream);
    if (removeErrorListeners) {
      lexer.removeErrorListeners();
    }

    TokenStream tokenStream = new CommonTokenStream(lexer);
    P parser = parserFactory.apply(tokenStream);
    if (useBailErrorStrategy) {
      parser.setErrorHandler(new BailErrorStrategy());
    }
    if (removeErrorListeners) {
      parser.removeErrorListeners();
    }

    return parser;
  }

  public static void parseAndWalk(
      Supplier<ParseTree> parseTreeSupplier, ParseTreeListener parseTreeListener) {
    ParseTree parseTree = parseTreeSupplier.get();
    ParseTreeWalker.DEFAULT.walk(parseTreeListener, parseTree);
  }
}
