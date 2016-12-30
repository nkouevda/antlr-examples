grammar Calculator;

expression
  : additiveExpression EOF
  ;

additiveExpression
  : multiplicativeExpression additionOrSubtraction*
  ;

additionOrSubtraction
  : Plus multiplicativeExpression # addition
  | Minus multiplicativeExpression # subtraction
  ;

multiplicativeExpression
  : unaryExpression multiplicationOrDivision*
  ;

multiplicationOrDivision
  : Times unaryExpression # multiplication
  | Div unaryExpression # division
  | Modulo unaryExpression # modulo
  ;

unaryExpression
  : negation
  | exponentialExpression
  ;

negation
  : Minus exponentialExpression
  ;

exponentialExpression
  : atom exponentiation*
  ;

exponentiation
  : Pow atom
  ;

atom
  : LeftParen additiveExpression RightParen
  | number
  ;

number
  : Integer
  | Double
  ;

Integer
  : Digit+
  ;

Double
  : Digit+ Point Digit*
  | Point Digit+
  ;

Digit: [0-9];
Point: '.';

Plus: '+';
Minus: '-';
Times: '*';
Div: '/';
Modulo: '%';
Pow: '^';

LeftParen: '(';
RightParen: ')';

Whitespace: [ \t\r\n]+ -> skip;

Error: .;
