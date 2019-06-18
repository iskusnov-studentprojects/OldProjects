cons(X, Y, [X|Y]).
append([],X,X).
append([X|Y],Z,[X|P]) :- append(Y,Z,P).

fun(ANS,X,Z):-fun1(ANS,[],X,Z).
fun1(CUR,CUR,[],Z).
fun1(ANS,CUR,[X|Y],Z):-X=Z,cons(A,B,Y),append(CUR,[X],NCUR),fun1(ANS,NCUR,B,Z).
fun1(ANS,CUR,[X|Y],Z):-append(CUR,[X],NCUR),fun1(ANS,NCUR,Y,Z).