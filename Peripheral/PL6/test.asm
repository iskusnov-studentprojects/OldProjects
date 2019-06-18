.386
.model flat,stdcall
option casemap:none
include d:\masm32\include\windows.inc
include d:\MASM32\INCLUDE\gdi32.inc
include d:\masm32\include\user32.inc
include d:\masm32\include\kernel32.inc
includelib d:\masm32\lib\user32.lib
includelib d:\masm32\lib\kernel32.lib
includelib d:\MASM32\LIB\gdi32.lib

; //прототипы функций
WinMain proto :DWORD,:DWORD,:DWORD,:DWORD
WndProc proto :DWORD,:DWORD,:DWORD,:DWORD
Paint_Draw proto :HDC,:DWORD,:HINSTANCE
dwtoa proto dwValue:DWORD, lpBuffer:DWORD

; //идентификаторы кнопок
IDM_EXIT equ 101
IDM_HELP equ 201
bn1_id equ 501
bn2_id equ 502

szText MACRO Name, Text:VARARG
	LOCAL lbl
	jmp lbl
		Name db Text,0
	lbl:
ENDM

; //константы
.const
	szMenu db "APP_MENU",0
	szMainMenu db "MENU",0
	szListBox db "LISTBOX",0
	szLabel db "LABEL",0
	szButton db "BUTTON",0
	szEdit db "EDIT",0
	szStatic db "STATIC",0
	szBn1 db "Чтение",0
	szBn2 db "Запись",0
	szHelp db "Ассинхронное чтение файла",0
	szTip db "Имя файла:",0
	szClassName db "Class1",0
	szWindowName db "Лабораторная работа №6",0
	szEvent1 db "ReadEvent",0
	szError db "Ошибка чтения",0
	szEnd db "Файл загружен!",0
	szSpace db " ",0

.data
	hInstance HINSTANCE ? ; //идентификатор нашего процесса
	CommandLine LPSTR ? ; //командная строка
	hEdit1 HWND ? ; //дескриптор окна текстового поля с именем файла
	hEdit2 HWND ? ;
	hEdit3 HWND ?
    hWin HWND ?

	ReadBuffer db 1048576 dup(?)  ;//мегабайтовый буфер
	szBuffer db MAX_PATH dup(?)
	hFile HANDLE ?    
	hEndRead HANDLE ?
	dwBytesRead DW 0
	szReadFile db "Начато чтение файла!",0
	flag DW 0			;//Флаг определяет запущено ли считывание
	ovl OVERLAPPED <>

	nPicture dd 1 
	n dw 1

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
	LOCAL wc:WNDCLASSEX ; //класс окна
	LOCAL msg:MSG		; //структура сообщения
	LOCAL hwnd:HWND		; //дескриптор окна

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
	invoke CreateWindowEx, 0, ADDR szClassName,ADDR szWindowName,\
	WS_OVERLAPPEDWINDOW,CW_USEDEFAULT,CW_USEDEFAULT,350,260,0,0,hInst,0
	mov hwnd,eax
	mov hWin,eax
	invoke ShowWindow,hwnd,SW_SHOWNORMAL    ;// показать окно
	invoke UpdateWindow,hwnd				;// и послать ему сообщение WM_PAINT
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
		LOCAL Ps :PAINTSTRUCT
	    LOCAL hDC : DWORD 
		;//Чтение файла происходит здесь	
	   .IF uMsg==WM_DESTROY
			invoke PostQuitMessage,NULL
	   .ELSEIF uMsg == WM_CREATE
			invoke CreateWindowEx,0, ADDR szStatic,ADDR szTip,WS_CHILD or\
			WS_VISIBLE,20,20,100,20,hWnd,0,hInstance,0
			;//Поле - имя файла
			invoke CreateWindowEx,0,ADDR szEdit,0,WS_BORDER or WS_VISIBLE or WS_CHILDWINDOW or\
			ES_AUTOHSCROLL or ES_NOHIDESEL,120,20,170,20,hWnd, 0,hInstance,0
			mov hEdit1,eax
			invoke CreateWindowEx,0,ADDR szEdit,0,WS_BORDER or WS_VISIBLE or WS_CHILDWINDOW or\
			ES_AUTOHSCROLL or ES_NOHIDESEL,20,110,170,20,hWnd, 0,hInstance,0
			mov hEdit2,eax
			invoke CreateWindowEx,0,ADDR szEdit,0,WS_BORDER or WS_VISIBLE or WS_CHILDWINDOW or\
			ES_AUTOHSCROLL or ES_NOHIDESEL,20,150,170,20,hWnd, 0,hInstance,0
			mov hEdit3,eax
			;//=================
			;// Создание кнопок
			;//=================
			;// Кнопка 1
			invoke CreateWindowEx,0, ADDR szButton,ADDR szBn1,WS_CHILD or WS_VISIBLE,20,60,125,30,hWnd,bn1_id,hInstance,0
		;//==========================
		;// Обработка нажатия кнопок
		;//==========================
	   .ELSEIF uMsg == WM_COMMAND
		   .IF wParam == bn1_id;// нажата кнопка считат, запуск процесса чтения
				;//Создаём событие и инициализируем структуру
				invoke CreateEvent,NULL,FALSE,FALSE,NULL
				.if eax==NULL
					invoke MessageBox, NULL,OFFSET szError,OFFSET szWindowName,MB_OK
				    xor eax,eax
	                ret
				.endif
				mov hEndRead,eax 
			    mov ovl.loffset,0        
	            mov ovl.OffsetHigh,0     
	            mov ovl.hEvent,eax

			    invoke GetWindowText,hEdit1,ADDR szBuffer,MAX_PATH
				;//Открыть файл
				invoke CreateFile,ADDR szBuffer,GENERIC_READ,FILE_SHARE_READ,NULL,OPEN_EXISTING,FILE_FLAG_OVERLAPPED,NULL

				mov hFile,eax
				.if hFile==NULL
					invoke MessageBox, NULL,OFFSET szError,OFFSET szWindowName,MB_OK
				    xor eax,eax
	                ret
				.endif
				
				;//Всё хорошо, начать чтение файла
				;//Инициализируем таймер
				invoke SetTimer, hWnd, NULL, 10, NULL
				invoke SendMessage,hEdit3,WM_SETTEXT,20,ADDR szSpace
				invoke SendMessage,hEdit2,WM_SETTEXT,20,ADDR szReadFile
		   .ELSEIF wParam == IDM_EXIT;//Обработка сообщений пунктов меню
				invoke DestroyWindow,hWnd
		   .ELSEIF wParam == IDM_HELP
				invoke MessageBox, NULL,OFFSET szHelp,OFFSET szWindowName,MB_OK
		   .ENDIF
		.elseif uMsg == WM_TIMER
			invoke dwtoa,n,ADDR szBuffer
			inc n
		    invoke SendMessage,hEdit3,WM_SETTEXT,10,ADDR szBuffer
			invoke ReadFile,hFile,ADDR ReadBuffer, 1048576,NULL,ADDR ovl
			.if eax==0
				invoke GetLastError
				.if eax==ERROR_HANDLE_EOF;//Файл считан до конца
					invoke CloseHandle,hFile
					invoke CloseHandle,hEndRead
					invoke KillTimer, hWnd, NULL
					invoke SendMessage,hEdit2,WM_SETTEXT,14,ADDR szEnd
				.elseif eax==ERROR_IO_PENDING;//Возможно файл считан
					invoke GetOverlappedResult,hFile, ADDR ovl, ADDR dwBytesRead, FALSE 
					.if eax==0
						invoke GetLastError
						.if eax==ERROR_HANDLE_EOF
							invoke CloseHandle,hFile
							invoke CloseHandle,hEndRead
							invoke KillTimer, hWnd, NULL
							invoke SendMessage,hEdit2,WM_SETTEXT,10,ADDR szEnd
						.endif
					.endif
				.else
					invoke CloseHandle,hFile
					invoke CloseHandle,hEndRead
					invoke KillTimer, hWnd, NULL
					invoke SendMessage,hEdit2,WM_SETTEXT,13,ADDR szError
				.endif
			.endif
			invoke WaitForSingleObject,hEndRead,INFINITE
			mov eax,ovl.loffset
			add eax,1048576
			mov ovl.loffset,eax
	   .ELSE
			invoke DefWindowProc,hWnd,uMsg,wParam,lParam
			ret
	   .ENDIF
	   xor eax,eax
	   ret
WndProc endp

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