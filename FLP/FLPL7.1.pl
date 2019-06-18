cons(X, Y, [X|Y]).
append([],X,X).
append([X|Y],Z,[X|P]) :- append(Y,Z,P).

max(X,Y):-maxlist(X,0,Y).
maxlist([],M,M).
maxlist([X|Y],N,M):-X>N,maxlist(Y,X,M).
maxlist([X|Y],N,M):-maxlist(Y,N,M).

count(X,N,A):-countnum(X,N,0,A).
countnum([],M,M,A).
countnum([X|Y],N,M,A):-X=A,NM is M+1,countnum(Y,N,NM,A).
countnum([X|Y],N,M,A):-countnum(Y,N,M,A).

newlist(X,ANS,I,MAX):-makenewlist(X,ANS,[],I,MAX).
makenewlist(X,CUR,CUR,MAX,MAX).
makenewlist(X,ANS,CUR,I,MAX):-count(X,T,I),NI is I+1,append(CUR,[T],NCUR),makenewlist(X,ANS,NCUR,NI,MAX).

fun(X,ANS):-max(X,MAX),NMAX is MAX+1,newlist(X,ANS,1,NMAX).