# antlr-examples

[ANTLR](http://www.antlr.org/) examples.

## Compilation

Compile:

    mvn compile

Test:

    mvn test

Clean up:

    mvn clean

## Examples

Calculator REPL:

    $ mvn -q compile exec:java -pl calculator
    Use ^C or ^D or "exit" or "quit" to exit
    > 2 + 2
    4.0
    > 1 + 2 ^ 3 * 4 - 5 / ((-6 * -7 + 8) % 9)
    32.0
    > 1 / 0
    Infinity
    > 1 -
    error: Invalid expression
    > asdf
    error: Invalid expression

## License

[MIT License](LICENSE.txt)
