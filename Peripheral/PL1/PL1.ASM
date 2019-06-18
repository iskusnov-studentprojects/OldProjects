.386
.model tiny
prg segment para public use16 'code'
assume cs:prg,ds:prg,es:prg,ss:prg
org 100h
beg: jmp start

;Сообщения
mess1 db 0ah,0dh,'Input matrix1:',0ah,0dh,'$'
mess2 db 0ah,0dh,'Input matrix2:',0ah,0dh,'$'
mess3 db 0ah,0dh,'Input matrix3:',0ah,0dh,'$'
mess4 db 0ah,0dh,'Matrix:',0ah,0dh,'$'
mess5 db 0ah,0dh,'Massive:',0ah,0dh,'$'
mess6 db 0ah,0dh,'Size n (2<=n<=10): ','$'
mess7 db 0ah,0dh,'Size m(2<=m<=10): ','$'
mess8 db 0ah,0dh,'Input elements: ',0ah,0dh,'$'
ln db 0ah,0dh,'$'
space db ' ','$'
;Переменные
buff dq 10,'$'
matr1 dw 10 dup(10 dup(?))
matr2 dw 10 dup(10 dup(?))
matr3 dw 10 dup(10 dup(?))
mass1 dw 10 dup(?)
mass2 dw 10 dup(?)
mass3 dw 10 dup(?)
sizen dw 1
sizem dw 1

;Включение библиотек
include str2bin.asm
include bin2str.asm

;Вывод строки
outstr macro mess
pusha
lea dx,mess
mov ah,09h
int 21h
popa
endm

;Ввод числа
innum macro k
pusha
lea dx,buff
mov ah,0ah
int 21h
mov bx,dx
inc bx
call str2bin
mov k,ax
popa
endm

;Вывод числа
outnum macro num
pusha
mov ax,num
lea bx,buff
call bin2str
inc bx
outstr [bx]
popa
endm

;Вывод числа
inmatr macro matrix,n,m
local i,j
pusha
mov di,0
mov cx,n
i:
	push cx
	mov cx,m
	j:
		innum matrix[di] ;Ввод элемента матрицы
		add di,2
		outstr ln
	loop j ;Цикл по j
	pop cx
loop i ;Цикл по i
popa
endm

;Вывод матрицы
outmatr macro matrix,n,m
local i,j
pusha
mov di,0
mov cx,n
i:
	push cx
	mov cx,m
	j:
		outnum matrix[di] ;Вывод элемента матрицы
		add di,2
	loop j ;Цикл по j
	outstr ln ;Переход на новую строку
	pop cx
loop i ;Цикл по i
popa
endm

;Вывод массива
outmass macro mass,n
local cycle
pusha
mov cx,n
mov di,0
cycle:
	outnum mass[di] ;Вывод элемента массива
	outstr space
	add di,2
loop cycle
popa
endm

;Найти максимум в массиве размера n, ответ в ах
max macro mass,n
local cycle,nomore
push bx
push cx
mov ax,mass
mov bx,2
mov cx,n
dec cx
cycle:
	cmp ax,mass[bx]
	js nomore
	mov ax,mass[bx]
	nomore:
	add bx,2
loop cycle
pop cx
pop bx
endm

;Найти минимум в массиве размера n, ответ в ах
min macro mass,n
local cycle,noless
push bx
push cx
mov ax,mass
mov bx,2
mov cx, n
dec cx
cycle:
	cmp ax,mass[bx]
	jns noless
	mov ax,mass[bx]
	noless:
	add bx,2
loop cycle
pop cx
pop bx
endm

;Основная функция программы
fun macro matrix,mass,n,m
local cycle,sceep
pusha
mov cx,n
mov di,0
mov bx,0
outstr mess4
outmatr matrix,n,m
cycle:
	push cx
	min matrix[di],m
	mov dx,ax
	max matrix[di],m
	add ax,dx
	mov mass[bx],ax
	add bx,2
	mov cx,m ;Пропуск строки
	sceep:
	add di,2
	loop sceep
	pop cx
loop cycle
outstr mess5
outmass mass,n
popa
endm

start:
irpc t,<123> ;Повторить программу 3 раза
	outstr mess&t
	outstr mess6
	innum sizen
	outstr mess7
	innum sizem
	outstr mess8
	inmatr matr&t,sizen,sizem
	fun matr&t,mass&t,sizen,sizem
endm
mov ah,1 ;Сделать задержку
int 21h
int 20h
ends prg
end beg
