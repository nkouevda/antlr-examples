grammar Brackets;

expression
  : nestedExpression* EOF
  ;

nestedExpression
  : LeftAngle nestedExpression* RightAngle
  | LeftBrace nestedExpression* RightBrace
  | LeftBracket nestedExpression* RightBracket
  | LeftParen nestedExpression* RightParen
  ;

LeftAngle: '<';
RightAngle: '>';

LeftBrace: '{';
RightBrace: '}';

LeftBracket: '[';
RightBracket: ']';

LeftParen: '(';
RightParen: ')';

Whitespace: [ \t\r\n]+ -> skip;

Error: .;
