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
CopyData proto :LPSTR, :LPSTR
MainFun proto :HWND, :LPSTR
GetParentDirectory proto :LPSTR, :LPSTR
m2m macro x,y
	push y
	pop x
endm

.data
ClassName db "Form1", 0	;Название класса
AppName db "PL5", 0		;Название приложения
hInstance HINSTANCE ?	;Дескриптор приложения
hEditSource HWND ?
CommandLine LPSTR ?		;Дескриптор консоли
buttonID equ 1000
exit_ID equ 2000
help_ID equ 2001
szListBox db "LISTBOX",0
szButton db "BUTTON",0
szEdit db "EDIT",0
szStatic db "STATIC",0
szMenu db "APP_MENU",0

;Строки
buttonText1 db "ReadFile", 0
staticText1 db "Путь к файлу:", 0
messageText1 db "Указанный путь к файлу не существует", 0
messageText2 db "Не удалось создать окно",0
messageText3 db "Ошибка чтения",0
messageText4 db "Файл успешно прочитан",0
helpText db "Помощь",0
messageCaption1 db "Ошибка!",0
messageCaption2 db "Помощь",0
messageCaption3 db "Сообщение",0
buffer1 db 128 dup(0)
buffer2 db 1048576 dup(0)

.code
start:
invoke GetModuleHandle, NULL		; //получение идентификатора процесса
mov hInstance, eax
invoke GetCommandLine				; //получение указателя командной строки
mov CommandLine, eax
invoke WinMain,hInstance,NULL,CommandLine,SW_SHOWDEFAULT; //вызов основной функции
invoke ExitProcess,eax                   ; //выход из программы (с кодом возврата в eax)

WinMain proc hInst:HINSTANCE,hPrevInst:HINSTANCE,CmdLine:LPSTR,CmdShow:DWORD
	LOCAL wc:WNDCLASSEX
	LOCAL msg:MSG
	LOCAL hWnd:HWND
	;Заполнение стpуктуpы wc
	mov   wc.cbSize,SIZEOF WNDCLASSEX
	mov   wc.style, CS_BYTEALIGNWINDOW
	mov   wc.lpfnWndProc, OFFSET WndProc
	mov   wc.cbClsExtra,NULL

	mov   wc.cbWndExtra,NULL
	push  hInstance
	pop   wc.hInstance
	mov   wc.hbrBackground,COLOR_WINDOW

	mov   wc.lpszMenuName, offset szMenu
	mov   wc.lpszClassName,OFFSET ClassName
	invoke LoadIcon,NULL,IDI_APPLICATION
	mov   wc.hIcon,eax

	mov   wc.hIconSm,eax
	invoke LoadCursor,NULL,IDC_ARROW
	mov   wc.hCursor,eax
	invoke RegisterClassEx, addr wc  ; pегистpация нашего класса окна
	invoke CreateWindowEx, NULL,
			ADDR ClassName,
			ADDR AppName,
				WS_OVERLAPPEDWINDOW, ;Стиль окна
				CW_USEDEFAULT,
				CW_USEDEFAULT,
				CW_USEDEFAULT,
				CW_USEDEFAULT,
				NULL, 				;Дескриптор родительского окна
				NULL,
				hInst, 				;Дескриптор приложения
				NULL
	.if eax==NULL
		invoke MessageBox, NULL, addr messageText2, addr messageCaption1, MB_OK
		ret
	.endif
	mov   hWnd,eax

	invoke ShowWindow, hWnd, SW_SHOWNORMAL ; отобpазить окно
	invoke UpdateWindow, hWnd ; обновить клиентскую область
	
	.WHILE TRUE   ;Цикл обработки сообщений
		invoke GetMessage, ADDR msg,NULL,0,0
	.BREAK .IF (!eax)
		invoke TranslateMessage, ADDR msg
		invoke DispatchMessage, ADDR msg
	.ENDW
	mov eax,msg.wParam ; сохpанение возвpащаемого значения в eax
	ret
WinMain endp

WndProc proc hWnd:HWND, uMsg:UINT, wParam:WPARAM,lParam:LPARAM	
   .if uMsg==WM_DESTROY
		invoke PostQuitMessage,NULL
   .elseif uMsg == WM_CREATE		
		;Поля редактирования
		invoke CreateWindowEx,NULL,addr szEdit,NULL, WS_BORDER or WS_VISIBLE or WS_CHILD,10,30,300,20,hWnd,NULL,hInstance,NULL
		mov hEditSource,eax
		;Кнопки
		invoke CreateWindowEx,0, addr szButton,addr buttonText1,WS_BORDER or WS_CHILD or WS_VISIBLE,10,60,80,20,hWnd,buttonID,hInstance,0
		;Надписи
		invoke CreateWindowEx,NULL,addr szStatic,addr staticText1, WS_VISIBLE or WS_CHILD,10,10,300,20,hWnd,NULL,hInstance,NULL
	.elseif uMsg==WM_COMMAND
		.if wParam == buttonID
			invoke GetWindowText, hEditSource, addr buffer1, 128
			invoke MainFun, hWnd, addr buffer1
		.elseif wParam == exit_ID
			invoke PostQuitMessage,NULL
		.elseif wParam == help_ID
			invoke MessageBox, hWnd, addr helpText, addr messageCaption2, MB_OK
		.endif
    .else
		invoke DefWindowProc,hWnd,uMsg,wParam,lParam
		ret
   .endif
   xor eax,eax
   ret
WndProc endp

MainFun proc hWnd:HWND, lpSource:LPSTR
	local hFile:HANDLE
	local hEndRead:HANDLE
	local overlapped:OVERLAPPED
	local dwBytesRead:DWORD
	invoke CreateFile, lpSource, GENERIC_READ, FILE_SHARE_READ, NULL, OPEN_EXISTING, FILE_FLAG_OVERLAPPED, NULL
	.if eax==NULL
		invoke MessageBox, hWnd, addr messageText1, addr messageCaption1, MB_OK
		xor eax, eax
		ret
	.endif
	mov hFile,eax
	
	invoke CreateEvent,NULL,FALSE,FALSE,NULL
	.if eax==NULL
		invoke MessageBox, NULL,addr messageText3,addr messageCaption1,MB_OK
	    xor eax,eax
        ret
	.endif
	mov hEndRead, eax
	mov overlapped.loffset,0        
	mov overlapped.OffsetHigh,0     
	mov overlapped.hEvent,eax
	
	.while eax!=0
		invoke ReadFile,hFile,addr buffer2, 1048576,NULL,addr overlapped
	.endw
	
	.if eax==0
		invoke GetLastError
		.if eax==ERROR_HANDLE_EOF;//Файл считан до конца
			invoke CloseHandle,hFile
			invoke CloseHandle,hEndRead
			invoke MessageBox, hWnd,addr messageText4, addr messageCaption3, MB_OK
		.elseif eax==ERROR_IO_PENDING;//Возможно файл считан
			invoke GetOverlappedResult,hFile, addr overlapped, addr dwBytesRead, FALSE 
			.if eax==0
				invoke GetLastError
				.if eax==ERROR_HANDLE_EOF
					invoke CloseHandle,hFile
					invoke CloseHandle,hEndRead
					invoke MessageBox,hWnd,addr messageText4, addr messageCaption3, MB_OK
				.endif
			.endif
		.else
			invoke CloseHandle,hFile
			invoke CloseHandle,hEndRead
			invoke MessageBox,hWnd,addr messageText3, addr messageCaption1, MB_OK
		.endif
	.endif
	invoke WaitForSingleObject,hEndRead,INFINITE
	mov eax,overlapped.loffset
	add eax,1048576
	mov overlapped.loffset,eax
	
	mov eax,TRUE
	ret
MainFun endp

end start