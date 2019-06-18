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

; //��������� �������
WinMain proto :DWORD,:DWORD,:DWORD,:DWORD
WndProc proto :DWORD,:DWORD,:DWORD,:DWORD
Paint_Draw proto :HDC,:DWORD,:HINSTANCE
dwtoa proto dwValue:DWORD, lpBuffer:DWORD

; //�������������� ������
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

; //���������
.const
	szMenu db "APP_MENU",0
	szMainMenu db "MENU",0
	szListBox db "LISTBOX",0
	szLabel db "LABEL",0
	szButton db "BUTTON",0
	szEdit db "EDIT",0
	szStatic db "STATIC",0
	szBn1 db "������",0
	szBn2 db "������",0
	szHelp db "������������ ������ �����",0
	szTip db "��� �����:",0
	szClassName db "Class1",0
	szWindowName db "������������ ������ �6",0
	szEvent1 db "ReadEvent",0
	szError db "������ ������",0
	szEnd db "���� ��������!",0
	szSpace db " ",0

.data
	hInstance HINSTANCE ? ; //������������� ������ ��������
	CommandLine LPSTR ? ; //��������� ������
	hEdit1 HWND ? ; //���������� ���� ���������� ���� � ������ �����
	hEdit2 HWND ? ;
	hEdit3 HWND ?
    hWin HWND ?

	ReadBuffer db 1048576 dup(?)  ;//������������ �����
	szBuffer db MAX_PATH dup(?)
	hFile HANDLE ?    
	hEndRead HANDLE ?
	dwBytesRead DW 0
	szReadFile db "������ ������ �����!",0
	flag DW 0			;//���� ���������� �������� �� ����������
	ovl OVERLAPPED <>

	nPicture dd 1 
	n dw 1

.code
start:
	;//���� � ���������
	invoke GetModuleHandle, NULL		; //��������� �������������� ��������
	mov hInstance, eax
	invoke GetCommandLine				; //��������� ��������� ��������� ������
	mov CommandLine, eax
	invoke WinMain,hInstance,NULL,CommandLine,SW_SHOWDEFAULT; //����� �������� �������
	invoke ExitProcess,eax                   ; //����� �� ��������� (� ����� �������� � eax)



WinMain proc hInst:HINSTANCE,hPrevInst:HINSTANCE,CmdLine:LPSTR,CmdShow:DWORD
	LOCAL wc:WNDCLASSEX ; //����� ����
	LOCAL msg:MSG		; //��������� ���������
	LOCAL hwnd:HWND		; //���������� ����

	mov wc.cbSize,SIZEOF WNDCLASSEX			; //������ ��������� 4*12 ������
	mov wc.style, CS_HREDRAW or CS_VREDRAW  ; //����� ����, ����������������� �����������
											; //��� ������������ � �������������� ��������
	mov wc.lpfnWndProc, OFFSET WndProc      ; //�������-���������� ������� ����
	mov wc.cbClsExtra,NULL					; //����� �������������� ������
	mov wc.cbWndExtra,NULL					; //����� �������������� ������
	push hInst
	pop wc.hInstance                        ; //������������� ������ ��������
	mov wc.hbrBackground,COLOR_BTNFACE+1	; //������������� ����� (��� ���� ����+1)
	mov wc.lpszMenuName,OFFSET szMenu		; //������ � �������� ����
	mov wc.lpszClassName,OFFSET szClassName ; //��� ������
	invoke LoadIcon,NULL,IDI_APPLICATION
	mov wc.hIcon,eax						; //������������� ������
	mov wc.hIconSm,NULL						; //������������� ��������� ������ (������ ���� 0)
	invoke LoadCursor,NULL,IDC_ARROW
	mov wc.hCursor,eax						; //������������� �������
	invoke RegisterClassEx, addr wc			; //����������� ������
	invoke CreateWindowEx, 0, ADDR szClassName,ADDR szWindowName,\
	WS_OVERLAPPEDWINDOW,CW_USEDEFAULT,CW_USEDEFAULT,350,260,0,0,hInst,0
	mov hwnd,eax
	mov hWin,eax
	invoke ShowWindow,hwnd,SW_SHOWNORMAL    ;// �������� ����
	invoke UpdateWindow,hwnd				;// � ������� ��� ��������� WM_PAINT
	message_loop:
		invoke GetMessage,ADDR msg,0,0,0    ;// �������� ��������� �� ����
		test eax,eax
		; ���� �������� WM_QUIT, GetMessage ;//������ ����
		jz exit_msg_loop                    ;//�����
		invoke TranslateMessage,ADDR msg    ;// ����� - ������������� ��������� ���� WM_KEYUP
		; � ��������� ���� WM_CHAR
		invoke DispatchMessage,ADDR msg     ;// � ������� �� ������� �������
		jmp short message_loop					;// ���������� ����
	exit_msg_loop:
	mov eax,msg.wParam ;// ��� ��������
	ret
WinMain endp

WndProc proc hWnd:HWND, uMsg:UINT, wParam:WPARAM,lParam:LPARAM
		LOCAL Ps :PAINTSTRUCT
	    LOCAL hDC : DWORD 
		;//������ ����� ���������� �����	
	   .IF uMsg==WM_DESTROY
			invoke PostQuitMessage,NULL
	   .ELSEIF uMsg == WM_CREATE
			invoke CreateWindowEx,0, ADDR szStatic,ADDR szTip,WS_CHILD or\
			WS_VISIBLE,20,20,100,20,hWnd,0,hInstance,0
			;//���� - ��� �����
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
			;// �������� ������
			;//=================
			;// ������ 1
			invoke CreateWindowEx,0, ADDR szButton,ADDR szBn1,WS_CHILD or WS_VISIBLE,20,60,125,30,hWnd,bn1_id,hInstance,0
		;//==========================
		;// ��������� ������� ������
		;//==========================
	   .ELSEIF uMsg == WM_COMMAND
		   .IF wParam == bn1_id;// ������ ������ ������, ������ �������� ������
				;//������ ������� � �������������� ���������
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
				;//������� ����
				invoke CreateFile,ADDR szBuffer,GENERIC_READ,FILE_SHARE_READ,NULL,OPEN_EXISTING,FILE_FLAG_OVERLAPPED,NULL

				mov hFile,eax
				.if hFile==NULL
					invoke MessageBox, NULL,OFFSET szError,OFFSET szWindowName,MB_OK
				    xor eax,eax
	                ret
				.endif
				
				;//�� ������, ������ ������ �����
				;//�������������� ������
				invoke SetTimer, hWnd, NULL, 10, NULL
				invoke SendMessage,hEdit3,WM_SETTEXT,20,ADDR szSpace
				invoke SendMessage,hEdit2,WM_SETTEXT,20,ADDR szReadFile
		   .ELSEIF wParam == IDM_EXIT;//��������� ��������� ������� ����
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
				.if eax==ERROR_HANDLE_EOF;//���� ������ �� �����
					invoke CloseHandle,hFile
					invoke CloseHandle,hEndRead
					invoke KillTimer, hWnd, NULL
					invoke SendMessage,hEdit2,WM_SETTEXT,14,ADDR szEnd
				.elseif eax==ERROR_IO_PENDING;//�������� ���� ������
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