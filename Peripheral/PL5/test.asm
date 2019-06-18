;//12. Задано имя файла. Определить имена каталогов, в которых он содержится, 
;//и, если найден не один каталог-родитель, то все файлы с указанным именем 
;//поместить в новый каталог с известным именем.
.386
.model flat,stdcall
option casemap:none
include d:\masm32\include\windows.inc
include d:\masm32\include\user32.inc
include d:\masm32\include\kernel32.inc
includelib d:\masm32\lib\user32.lib
includelib d:\masm32\lib\kernel32.lib

; //прототипы функций
WinMain proto :DWORD,:DWORD,:DWORD,:DWORD
WndProc proto :DWORD,:DWORD,:DWORD,:DWORD
ListBoxProc proto :DWORD,:DWORD,:DWORD,:DWORD
FindFile proto :DWORD,:DWORD,:DWORD
WorkFile proto :DWORD,:DWORD
lstrchr proto :DWORD
indexDot proto
dwtoa proto dwValue:DWORD, lpBuffer:DWORD

; //идентификаторы кнопок
IDM_EXIT equ 101
IDM_HELP equ 201
bn1_id equ 501
bn2_id equ 502

; //константы
.const
	szMenu db "APP_MENU",0
	szMainMenu db "MENU",0
	szListBox db "LISTBOX",0
	szLabel db "LABEL",0
	szButton db "BUTTON",0
	szEdit db "EDIT",0
	szStatic db "STATIC",0
	szBn1 db "GO",0
	szBn2 db "Сброс",0
	szTextLabel1 db "1. ->",0
	szTextLabel2 db "2. ->",0
	szTextLabel0 db "3. ->",0
	szTextLabel3 db "Родительские каталоги",0
	szHelp db "Просто ещё один пункт меню!",0
	szTip db "В поле 1. ввести имя файла",0Dh,0Ah,"В поле 2. ввести имя каталога, от которого начинать поиск",
	0Dh,0Ah,"В поле 3. Ввести имя калалога, в который произойдёт копирование",
	0Dh,0Ah,0Dh,0Ah,"Затем нажать GO и программа выполнит алгоритм",0Dh,0Ah,0Dh,0Ah,
	"Клавиша сброс - очищает все поля",0
	szClassName db "Class1",0
	szWindowName db "Лабораторная работа №5",0

.data
	hInstance HINSTANCE ? ; //идентификатор нашего процесса
	CommandLine LPSTR ? ; //командная строка
	hEdit1 HWND ? ; //дескриптор окна текстового поля
	hEdit2 HWND ?
	hEdit3 HWND ?
	hList HWND ? ; //дескриптор окна списка
	lpLstBox DWORD ?; //указатель на предыдущую функцию ListBoxProc
	szBuffer db MAX_PATH dup(0)  ; //текстовый буфер для пути
	szBuffer1 db MAX_PATH dup(0) ; //текстовый буфер для имён файлов
	szBuffer3 db MAX_PATH dup(0) ; //Путь к каталогу, в который копировать 
	szBuf db MAX_PATH dup(0)
	szBuf1 db 100 dup(0)
	szBuf2 db 100 dup(0)
    hWin HWND ?

    ;//====================================
	;//==Для работы со строками============
	;//====================================
	n_file dw 0
	szFF_Mask db "\\*.*",0
	szFF_Skip1 db '.',0       ; //Запрещенное имя файла
	szFF_Skip2 db '..',0      ; //Запрещенное имя файла
	szDslesh db '\\',0

.code
start:
	;//Вход в программу
	invoke GetModuleHandle, NULL		; //получение идентификатора процесса
	mov hInstance, eax
	invoke GetCommandLine				; //получение указателя командной строки
	mov CommandLine, eax
	invoke WinMain,hInstance,NULL,CommandLine,SW_SHOWDEFAULT; //вызов основной функции
	invoke ExitProcess,eax                   ; //выход из программы (с кодом возврата в eax)



WinMain proc hInst:HINSTANCE,hPrevInst:HINSTANCE,CmdLine:LPSTR,CmdShow:DWORD
; //локальные переменные:
	LOCAL wc:WNDCLASSEX ; //класс окна
	LOCAL msg:MSG		; //структура сообщения
	LOCAL hwnd:HWND		; //дескриптор окна
	;//=================================================
	;// Заполнение и регистрация класса окна WNDCLASSEX
	;//=================================================
	mov wc.cbSize,SIZEOF WNDCLASSEX			; //размер структуры 4*12 байтов
	mov wc.style, CS_HREDRAW or CS_VREDRAW  ; //стиль окна, предусматривающий перерисовку
											; //при вертикальном и горизонтальном движении
	mov wc.lpfnWndProc, OFFSET WndProc      ; //функция-обработчик событий окна
	mov wc.cbClsExtra,NULL					; //число дополнительных байтов
	mov wc.cbWndExtra,NULL					; //число дополнительных байтов
	push hInst
	pop wc.hInstance                        ; //идентификатор нашего процесса
	mov wc.hbrBackground,COLOR_BTNFACE+1	; //идентификатор кисти (или цвет фона+1)
	mov wc.lpszMenuName,OFFSET szMenu		; //ресурс с основным меню
	mov wc.lpszClassName,OFFSET szClassName ; //имя класса
	invoke LoadIcon,NULL,IDI_APPLICATION
	mov wc.hIcon,eax						; //идентификатор иконки
	mov wc.hIconSm,NULL						; //идентификатор маленькой иконки (должен быть 0)
	invoke LoadCursor,NULL,IDC_ARROW
	mov wc.hCursor,eax						; //идентификатор курсора
	invoke RegisterClassEx, addr wc			; //регистрация класса

	;//Создание окна
	invoke CreateWindowEx, 0, ADDR szClassName,ADDR szWindowName,\
	WS_OVERLAPPEDWINDOW,CW_USEDEFAULT,CW_USEDEFAULT,550,460,0,0,hInst,0
	mov hwnd,eax
	mov hWin,eax
	;//==================
	;// Отображение окна
	;//==================
	invoke ShowWindow,hwnd,SW_SHOWNORMAL    ;// показать окно
	invoke UpdateWindow,hwnd				;// и послать ему сообщение WM_PAINT
	;//==========================
	;// Цикл обработки сообщений
	;//==========================
	message_loop:
		invoke GetMessage,ADDR msg,0,0,0    ;// получить сообщение от окна
		test eax,eax
		; если получено WM_QUIT, GetMessage ;//вернет ноль
		jz exit_msg_loop                    ;//выйти
		invoke TranslateMessage,ADDR msg    ;// иначе - преобразовать сообщения типа WM_KEYUP
		; в сообщения типа WM_CHAR
		invoke DispatchMessage,ADDR msg     ;// и послать их оконной функции
	jmp short message_loop					;// продолжить цикл
	exit_msg_loop:
	mov eax,msg.wParam ;// код возврата
	ret
WinMain endp

WndProc proc hWnd:HWND, uMsg:UINT, wParam:WPARAM,lParam:LPARAM
	   .IF uMsg==WM_DESTROY
			invoke PostQuitMessage,NULL
	   .ELSEIF uMsg == WM_CREATE
			invoke CreateWindowEx,0,ADDR szListBox,0,WS_VISIBLE or WS_VSCROLL or WS_HSCROLL \ 
			or WS_BORDER or WS_CHILD or LBS_HASSTRINGS or LBS_NOINTEGRALHEIGHT \
			or LBS_DISABLENOSCROLL,270,50,250,330,hWnd, 0,hInstance,0
			mov hList,eax ; //дескриптор окна ListBox   ;// Изменение атрибутов окна ListBox: установка оконной
													    ;// функции ListBoxProc. Эта функция аналогична WndProc.
													    ;// Она необходима для обработки событий нажатия клавиш
											            ;// в окне ListBox.
			invoke SetWindowLong,hList,GWL_WNDPROC,ListBoxProc
			mov lpLstBox, eax						  ;// запоминание старой функции
			;//=================
			;// Создание кнопок
			;//=================
			;// Кнопка 1
			invoke CreateWindowEx,0, ADDR szButton,ADDR szBn1,WS_CHILD or WS_VISIBLE,20,115,110,30,hWnd,bn1_id,hInstance,0
			;// Кнопка 2
			invoke CreateWindowEx,0, ADDR szButton,ADDR szBn2,WS_CHILD or WS_VISIBLE,130,115,110,30,hWnd,bn2_id, hInstance,0
			;//===============================
			;// Создание текстового поля Edit
			;//===============================
			invoke CreateWindowEx,0,ADDR szEdit,0, WS_BORDER or WS_VISIBLE or WS_CHILDWINDOW \
				or ES_AUTOHSCROLL or ES_NOHIDESEL,60,10,170,20,hWnd,0,hInstance,0	
			mov hEdit1,eax ; //дескриптор окна Edit1(поле для ввода имени файла)
			invoke CreateWindowEx,0,ADDR szEdit,0, WS_BORDER or WS_VISIBLE or WS_CHILDWINDOW \
				or ES_AUTOHSCROLL or ES_NOHIDESEL,60,50,170,20,hWnd,0,hInstance,0	
			mov hEdit2,eax ; //дескриптор окна Edit2(поле для ввода имени папки начала поиска)
			invoke CreateWindowEx,0,ADDR szEdit,0, WS_BORDER or WS_VISIBLE or WS_CHILDWINDOW \
				or ES_AUTOHSCROLL or ES_NOHIDESEL,60,90,170,20,hWnd,0,hInstance,0	
			mov hEdit3,eax ; //дескриптор окна Edit2(поле для ввода имени папки для копирования)
			;//===============================================
			;// Создание неизменяемого полей с надписями Static
			;//===============================================
			invoke CreateWindowEx,0, ADDR szStatic,ADDR szTextLabel0,WS_CHILD or WS_VISIBLE, 17,90,30,20,hWnd,0,hInstance,0
			invoke CreateWindowEx,0, ADDR szStatic,ADDR szTextLabel1,WS_CHILD or WS_VISIBLE, 17,10,30,20,hWnd,0,hInstance,0
			invoke CreateWindowEx,0, ADDR szStatic,ADDR szTextLabel2,WS_CHILD or WS_VISIBLE, 17,50,30,20,hWnd,0,hInstance,0
			invoke CreateWindowEx,0, ADDR szStatic,ADDR szTextLabel3,WS_CHILD or WS_VISIBLE, 270,10,200,20,hWnd,0,hInstance,0
			invoke CreateWindowEx,0, ADDR szStatic,ADDR szTip,WS_CHILD or WS_BORDER or WS_VISIBLE, 20,150,220,230,hWnd,0,hInstance,0
		;//==========================
		;// Обработка нажатия кнопок
		;//==========================
	   .ELSEIF uMsg == WM_COMMAND
		   .IF wParam == bn1_id;// нажата кнопка 'GO' – перемещение в ListBox
				;//Здесь поиск файла
				mov n_file,0
				invoke SendMessage,hList,LB_RESETCONTENT,eax,0 ;//Очистка списка
				invoke GetWindowText,hEdit1,ADDR szBuffer1,MAX_PATH
				invoke GetWindowText,hEdit2,ADDR szBuffer,MAX_PATH
				invoke GetWindowText,hEdit3,ADDR szBuffer3,MAX_PATH
				invoke CreateDirectory,ADDR szBuffer3,NULL
				invoke FindFile,ADDR szBuffer,ADDR szBuffer1,0
		   .ELSEIF wParam == bn2_id ; //нажата кнопка 'сброс', все пункты меню сбросить
				invoke SendMessage,hList,LB_RESETCONTENT,eax,0 ;//Очистка списка
				invoke SetWindowText,hEdit1,0
				invoke SetWindowText,hEdit2,0
				invoke SetWindowText,hEdit3,0
		   .ELSEIF wParam == IDM_EXIT;//Обработка сообщений пунктов меню
				invoke DestroyWindow,hWnd
		   .ELSEIF wParam == IDM_HELP
				invoke MessageBox, NULL,OFFSET szHelp,OFFSET szWindowName,MB_OK
		   .ENDIF
	   .ELSE
			invoke DefWindowProc,hWnd,uMsg,wParam,lParam
			ret
	   .ENDIF
	   xor eax,eax
	   ret
WndProc endp

;//обработка сообщения листбокса
ListBoxProc proc hCtl:HWND, uMsg:UINT, wParam:WPARAM,lParam:LPARAM
		;// Вызов старой функции ListBoxProc для обработки
		;// остальных сообщений
		invoke CallWindowProc,lpLstBox,hCtl,uMsg,wParam,lParam
	ret
ListBoxProc endp


;//Функция поиска файлов lpFStr - имя каталога, szFindFile - имя искомого файла
FindFile proc lpFStr:DWORD,szFindFile:DWORD,dFlag:dword
	LOCAL hFind:DWORD				;//Дескриптор поиска
	LOCAL FndData:WIN32_FIND_DATA	;//Структура содержит информацию для поиска
	LOCAL Flag:WORD
	LOCAL index:DWORD

	mov Flag,0
	;//Сохраниv изменяемые регистры!!!
	push ebx 
    push ecx 
	push edx

	;//Приписываем к имени каталога маску '\\*.*
	invoke lstrcat,lpFStr,ADDR szFF_Mask
	invoke FindFirstFile,lpFStr,ADDR FndData  ;//Найти первый файл каталога
	
	;//======================================================
	;invoke dwtoa, 100, ADDR szBuf			//===============
	;//======================================================
	cmp eax,INVALID_HANDLE_VALUE  ;//Ошибка, прекрщаем поиск
	jne work
		xor eax,eax
		jmp exit
work:	;//Продолжение поиска	
		;//Сохранить дескриптор поиска
		mov hFind,eax

		;//Проверки имён файлов, на недопустимые имена '.' и '..'
chek_filename:
		;//Проверка на имя '.'
		invoke  lstrcmp,ADDR szFF_Skip1,ADDR FndData.cFileName
		or eax,eax
		jz next_file ;//Если да к следующему файлу
		;//Проверка на имя '..'
		invoke  lstrcmp,ADDR szFF_Skip2,ADDR FndData.cFileName
		or eax,eax
		jz next_file ;//Если да к следующему файлу
		cmp dFlag,0
		jne do_work
		;//Каталог ?
		mov eax,FndData.dwFileAttributes
		and eax,FILE_ATTRIBUTE_DIRECTORY
		jnz do_work_with_derectory

;//Работа с именем файла, если найден то сохранение имени каталога родителя
do_work:
		xor eax,eax
		invoke lstrcpy,ADDR szBuf,ADDR FndData.cFileName ;//Копировать в буфер
		invoke indexDot									 ;//Обрезать расширение
		invoke lstrcmp,ADDR szBuf,ADDR szBuffer1		 ;//Сравнить имена
		or eax,eax
		jne next_file  ;//Имена не совпадают, то к следующему файлу
		;//Обрезка маски '\\*.*'
		invoke lstrlen,lpFStr
		sub eax,3
		add eax,lpFStr
		mov byte ptr[eax],0
		push eax
		;//Сообщение для listbox запись пути только 1 раз
		;//копирование файла всегда!
		.IF Flag==0
			invoke SendMessage,hList,LB_ADDSTRING,0,lpFStr
		    mov Flag,1
		.endif
		;//Сюда вставить копирование файла!
		invoke dwtoa, n_file, ADDR szBuf
		;//Имя копируемого файла
		invoke lstrcpy,ADDR szBuf1,lpFStr
		invoke lstrcat,ADDR szBuf1,ADDR FndData.cFileName
		;//Сформируем новое имя
		invoke lstrcpy,ADDR szBuf2,ADDR szBuffer3
		invoke lstrcat,ADDR szBuf2,ADDR szDslesh
		invoke lstrcat,ADDR szBuf2,ADDR szBuf
		invoke lstrcat,ADDR szBuf2,ADDR FndData.cFileName
		;//Копирование файла
		invoke CopyFile,ADDR szBuf1,ADDR szBuf2,FALSE
		inc n_file
		pop ecx
		mov dword ptr[ecx],'*.*'
		add ecx,4
		mov ecx,0
		jmp next_file
;//Работа переход в дочерний каталог
do_work_with_derectory:
		;//Обрезать маску файла '*.*'
		invoke lstrlen,lpFStr
		sub eax,3
		add eax,lpFStr
		mov byte ptr[eax],0

		;//Дописать имя каталога к пути
		invoke lstrlen,lpFStr
		add eax,lpFStr
        push eax
        invoke lstrcat,lpFStr,ADDR FndData.cFileName

		;//Рекурсивный вызов функции
		invoke FindFile,lpFStr,szFindFile,dFlag

		;// Вернуть маску поиска на место
        pop ecx
        mov dword ptr[ecx],'*.*'
		mov dword ptr[ecx+4],0

		or eax,eax
		jz stop_scan

next_file:
		;//Поиск следующего файла
		invoke  FindNextFile,hFind,ADDR FndData
		or eax,eax
		jnz chek_filename
		mov eax,TRUE
stop_scan:
		;//Закрыть дескриптор поиска
		push eax
		invoke  FindClose,hFind
		pop eax
exit:	;//Восстановить регистры и выйти
		pop edx 
	    pop ecx 
		pop ebx
		ret
FindFile endp

;//Обрезает у строки всё, что дальше точки
indexDot proc
	LOCAL n:DWORD
	push esi
	xor eax,eax
	xor esi,esi
	lea esi,szBuf
	do_work:
		cmp byte ptr [esi],'.'
		jz exit
		inc esi
		inc eax
		cmp word ptr [esi],0a0dh
	jnz do_work
	exit:
	mov esi,eax
	lea eax,szBuf
	add eax,esi
	mov byte ptr[eax],0
	invoke SetWindowText,hWin,ADDR szBuf
	pop esi
	ret
indexDot endp

dwtoa proc dwValue:DWORD, lpBuffer:DWORD

    ; -------------------------------------------------------------
    ; convert DWORD to ascii string
    ; dwValue is value to be converted
    ; lpBuffer is the address of the receiving buffer
    ; EXAMPLE:
    ; invoke dwtoa,edx,ADDR buffer
    ;
    ; Uses: eax, ecx, edx.
    ; -------------------------------------------------------------

    push ebx
    push esi
    push edi

    mov eax, dwValue
    mov edi, [lpBuffer]

    test eax,eax
    jnz sign

  zero:
    mov word ptr [edi],30h
    jmp dtaexit

  sign:
    jns pos
    mov byte ptr [edi],'-'
    neg eax
    add edi, 1

  pos:
    mov ecx, 3435973837
    mov esi, edi

    .while (eax > 0)
      mov ebx,eax
      mul ecx
      shr edx, 3
      mov eax,edx
      lea edx,[edx*4+edx]
      add edx,edx
      sub ebx,edx
      add bl,'0'
      mov [edi],bl
      add edi, 1
    .endw

    mov byte ptr [edi], 0       ; terminate the string

    ; We now have all the digits, but in reverse order.

    .while (esi < edi)
      sub edi, 1
      mov al, [esi]
      mov ah, [edi]
      mov [edi], al
      mov [esi], ah
      add esi, 1
    .endw

  dtaexit:

    pop edi
    pop esi
    pop ebx

    ret

dwtoa endp


end start