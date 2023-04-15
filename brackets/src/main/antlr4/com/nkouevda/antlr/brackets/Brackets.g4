grammar Brackets;

expression
  : nestedExpression* EOF
  ;

nestedExpression
  : LeftParen nestedExpression* RightParen
  ;

LeftParen: '(';
RightParen: ')';

Whitespace: [ \t\r\n]+ -> skip;

Error: .;
