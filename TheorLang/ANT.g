grammar ANT;

options
{
	language = Java;
}
@parser::namespace {Generated}
@lexer::namespace {Generated}

ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

CONST :	'0'..'9'+
    ;
	
CONST16 : ('0'..'9'|'a'..'f'|'A'..'F')+
    ;

COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;
//Начало
begin: (opisanie_peremennix | opisanie_funkcii)*
;

opisanie_peremennix: tip spisok_peremennix ';'
;

spisok_peremennix: element_spiska_peremennix spisok_peremennix_dop
;

spisok_peremennix_dop: (',' element_spiska_peremennix)*
;

element_spiska_peremennix: ID element_spiska_peremennix_dop
;

element_spiska_peremennix_dop: ('=' expression)?
;

constanta: (CONST | CONST16)
;

tip: ('long int' | 'short int')
;

opisanie_funkcii: 'void' ID '(' opisanie_funkcii_dop ')' block
;

opisanie_funkcii_dop: spisok_parametrov?
;

spisok_parametrov: element_spiska_parametrov spisok_parametrov_dop
;

spisok_parametrov_dop: (',' element_spiska_parametrov)*
;

element_spiska_parametrov: tip ID
;

block: '{' operator* '}'
;

operator: (opisanie_peremennix | while | prisvaivanie | vizov_funkcii | block | return | (expression ';') | ';')
;

return: 'return' ';'
;

while: 'while' '(' expression ')' operator
;

prisvaivanie: ID '=' expression ';'
;

vizov_funkcii: ID '(' vizov_funkcii_dop ')' ';'
;

vizov_funkcii_dop: paramentri?
;

paramentri: expression paramentri_dop
;

paramentri_dop: (',' expression)*
;

expression: expression1
;

expression1: expression2 expression1_dop
;

expression1_dop: ('||' expression1)
;

expression2: expression3 expression2_dop
;

expression2_dop: ('&&' expression2)?
;

expression3: expression4 expression3_dop
;

expression3_dop: ('==' expression3 | '!=' expression3)?
;

expression4: expression5 expression4_dop
;

expression4_dop: ('>' expression4 | '<' expression4 | '>=' expression4 | '<=' expression4)?
;

expression5: expression6 expression5_dop
;

expression5_dop: ('+' expression5 | '-' expression5)?
;

expression6: expression7 expression6_dop
;

expression6_dop: ('*' expression6 | '/' expression6 | '%' expression6)?
;

expression7: ('++' expression8 | '--' expression8 | '!' expression8 | '+' expression8 | '-' expression8 | expression8)
;

expression8: expression9 expression8_dop
;

expression8_dop: ('++'|'--')?
;

expression9: ID | constanta | (expression1)
;
