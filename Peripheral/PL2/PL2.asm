;Обработчик прерывания от клавиатуры, работающий в 2 ре-жимах, в 1 режиме – отключены цифры, во 2 – отключены буквы. 
;Переключение между режимами каждые 5 с. Нажатие Ctrl-Alt-Ins воз-вращает текущий режим к стандартной обработке.
jumps
prg segment para public 'code'
assume cs:prg,ds:prg
org 100h
beg: jmp start

;Переменные
old09 dd ?	 		;Старое прерывание от клавиатуры
old1c dd ?			;Старое прерывание от таймера
flag db 0			;Флаг режима работы
standflag db 0		;Флаг стандартной обработки
time db 0			;Четчик времени

sign dw 0fedch		;Сигнатура

;Новый обработчик прерывания клавиатуры
keyboard proc far
	sti
	push ax
	push bx
	push di
	push es
	cmp cs:standflag,1
	je standart
	
	mov ax,40h 				;устанавливаем регистр es на область данных BIOS
	mov es,ax 
	
	mov di,es:[1ch]			;получаем указатель на голову 
	cmp di,01Eh 			;он указывает на начало буфера?
	jne minus2 				;если нет, то для доступа к только 
	mov di,03Ch 			;что помещенным в буфер scan и ascii-кодам необходимо отнять 2 от нового “хвоста” 
	jmp el 					;иначе старый “хвост” указывал на 
	minus2: sub di,2 		;конец буфера, что и запишем в di и перейдем на el. 
	el: mov ax,es:[di] 		;cчитаем scan код из буфера клавиатуры 
	mov bl,10001100b 		;запишем в bl для test строку с установленным сочетанием клавиш 
	test es:[17h],bl 		;в слове флагов клавиатуры есть признак нажатия комбинации 
	jz nocombo 				;если 0 , то комбинация не зажата
	mov cs:standflag,1
	jmp standart
	
	nocombo:
	in al,60h 		;Cчитаем scan-код из порта
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
	
	in al,61h ; подтвердим обработку scan-кода 
	mov ah,al 
	or al,80h 
	out 61h,al
	mov al,ah 
	out 61h,al
	
	cli 						;запретить прерывания 
	mov al,20h 					;выдаем сигнал о завершении 
	out 20h,al 					;аппаратного прерывания
	pop es
	pop di
	pop bx
	pop ax
	iret
	
	;Стандартная обработка
	standart:
	pop es 
	pop di 
	pop bx 
	pop ax 
	jmp cs:[old09] ; передадим управление стандартному
endp keyboard

;Новый обработчик прерывания от таймера
timer proc far
	inc cs:time
	
	cmp cs:time,91 ;Если прошло 5 секунд
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
	
	;Установка нового обработчика для таймера
	mov ax,351Ch
	int 21h
	mov word ptr old1c,bx
	mov word ptr old1c+2,es
	mov dx,offset timer
	mov ax,251ch ;вызовем функцию установки обработчика
	int 21h
	
	mov ax,3509h ; проверим адрес обработчика 
	int 21h 
	mov ax,es:[bx-2] ; это наш обработчик в памяти? 
	cmp ax,cs:sign 
	je outmem ; если наш – на выгрузку 
	mov word ptr old09,bx ; системный – сохраним его 
	mov word ptr old09+2,es ; адрес в двойном слове old09 
	mov bx,cs 
	mov ds,bx ; установим ds на наш сегмент 
	mov dx,offset keyboard ; в dx поместим адрес нашей ISR 
	mov ax,2509h ; и запишем адреса в вектор 
	int 21h ; прерываний 
	lea dx, start
	int 27h ; оставим резидентно
	
outmem: ; выгрузка 
	mov dx, word ptr es:old1c 
	mov bx, word ptr es:old1c+2 
	mov ds,bx 
	mov ax,2509h ; запись в вектор прерываний 
	int 21h ; адреса стандартного обработчика
	
	mov dx, word ptr es:old09 
	mov bx, word ptr es:old09+2 
	mov ds,bx 
	mov ax,2509h ; запись в вектор прерываний 
	int 21h ; адреса стандартного обработчика 
	push es 
	mov es,es:[2Ch] ; выгрузка окружения 
	mov ah,49h 
	int 21h 
	pop es 
	mov ah,49h ; выгрузка программы 
	int 21h 
	int 20h
prg ends
end beg