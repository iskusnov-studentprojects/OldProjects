;���������� ���������� �� ����������, ���������� � 2 ��-�����, � 1 ������ � ��������� �����, �� 2 � ��������� �����. 
;������������ ����� �������� ������ 5 �. ������� Ctrl-Alt-Ins ���-������� ������� ����� � ����������� ���������.
jumps
prg segment para public 'code'
assume cs:prg,ds:prg
org 100h
beg: jmp start

;����������
old09 dd ?	 		;������ ���������� �� ����������
old1c dd ?			;������ ���������� �� �������
flag db 0			;���� ������ ������
standflag db 0		;���� ����������� ���������
time db 0			;������ �������

sign dw 0fedch		;���������

;����� ���������� ���������� ����������
keyboard proc far
	sti
	push ax
	push bx
	push di
	push es
	cmp cs:standflag,1
	je standart
	
	mov ax,40h 				;������������� ������� es �� ������� ������ BIOS
	mov es,ax 
	
	mov di,es:[1ch]			;�������� ��������� �� ������ 
	cmp di,01Eh 			;�� ��������� �� ������ ������?
	jne minus2 				;���� ���, �� ��� ������� � ������ 
	mov di,03Ch 			;��� ���������� � ����� scan � ascii-����� ���������� ������ 2 �� ������ �������� 
	jmp el 					;����� ������ ������ �������� �� 
	minus2: sub di,2 		;����� ������, ��� � ������� � di � �������� �� el. 
	el: mov ax,es:[di] 		;c������ scan ��� �� ������ ���������� 
	mov bl,10001100b 		;������� � bl ��� test ������ � ������������� ���������� ������ 
	test es:[17h],bl 		;� ����� ������ ���������� ���� ������� ������� ���������� 
	jz nocombo 				;���� 0 , �� ���������� �� ������
	mov cs:standflag,1
	jmp standart
	
	nocombo:
	in al,60h 		;C������ scan-��� �� �����
	mov ah,al
	cmp cs:flag,1
	je r2
		cmp ah,10h ;ax>=10h
		js standart
		cmp ah,35h ;ax<=35h
		jns standart
		jmp ex
	r2:
		cmp ah,02h ;ax>=02h
		js standart
		cmp ah,0Dh ;ax<=0dh
		jns standart
	ex:
	
	in al,61h ; ���������� ��������� scan-���� 
	mov ah,al 
	or al,80h 
	out 61h,al
	mov al,ah 
	out 61h,al
	
	cli 						;��������� ���������� 
	mov al,20h 					;������ ������ � ���������� 
	out 20h,al 					;����������� ����������
	pop es
	pop di
	pop bx
	pop ax
	iret
	
	;����������� ���������
	standart:
	pop es 
	pop di 
	pop bx 
	pop ax 
	jmp cs:[old09] ; ��������� ���������� ������������
endp keyboard

;����� ���������� ���������� �� �������
timer proc far
	inc cs:time
	
	cmp cs:time,91 ;���� ������ 5 ������
	jne timer_exit
	mov cs:time,0 
	mov cs:standflag,0
	
	cmp cs:flag,1
	jne timer_incf
		mov cs:flag,0
	jmp timer_exit
	timer_incf:	
		mov cs:flag,1
	
	timer_exit:
	iret
endp timer

start:
	push cs
	pop ds
	
	;��������� ������ ����������� ��� �������
	mov ax,351Ch
	int 21h
	mov word ptr old1c,bx
	mov word ptr old1c+2,es
	mov dx,offset timer
	mov ax,251ch ;������� ������� ��������� �����������
	int 21h
	
	mov ax,3509h ; �������� ����� ����������� 
	int 21h 
	mov ax,es:[bx-2] ; ��� ��� ���������� � ������? 
	cmp ax,cs:sign 
	je outmem ; ���� ��� � �� �������� 
	mov word ptr old09,bx ; ��������� � �������� ��� 
	mov word ptr old09+2,es ; ����� � ������� ����� old09 
	mov bx,cs 
	mov ds,bx ; ��������� ds �� ��� ������� 
	mov dx,offset keyboard ; � dx �������� ����� ����� ISR 
	mov ax,2509h ; � ������� ������ � ������ 
	int 21h ; ���������� 
	lea dx, start
	int 27h ; ������� ����������
	
outmem: ; �������� 
	mov dx, word ptr es:old1c 
	mov bx, word ptr es:old1c+2 
	mov ds,bx 
	mov ax,2509h ; ������ � ������ ���������� 
	int 21h ; ������ ������������ �����������
	
	mov dx, word ptr es:old09 
	mov bx, word ptr es:old09+2 
	mov ds,bx 
	mov ax,2509h ; ������ � ������ ���������� 
	int 21h ; ������ ������������ ����������� 
	push es 
	mov es,es:[2Ch] ; �������� ��������� 
	mov ah,49h 
	int 21h 
	pop es 
	mov ah,49h ; �������� ��������� 
	int 21h 
	int 20h
prg ends
end beg