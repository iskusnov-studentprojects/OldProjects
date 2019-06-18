;//12. ������ ��� �����. ���������� ����� ���������, � ������� �� ����������, 
;//�, ���� ������ �� ���� �������-��������, �� ��� ����� � ��������� ������ 
;//��������� � ����� ������� � ��������� ������.
.386
.model flat,stdcall
option casemap:none
include d:\masm32\include\windows.inc
include d:\masm32\include\user32.inc
include d:\masm32\include\kernel32.inc
includelib d:\masm32\lib\user32.lib
includelib d:\masm32\lib\kernel32.lib

; //��������� �������
WinMain proto :DWORD,:DWORD,:DWORD,:DWORD
WndProc proto :DWORD,:DWORD,:DWORD,:DWORD
ListBoxProc proto :DWORD,:DWORD,:DWORD,:DWORD
FindFile proto :DWORD,:DWORD,:DWORD
WorkFile proto :DWORD,:DWORD
lstrchr proto :DWORD
indexDot proto
dwtoa proto dwValue:DWORD, lpBuffer:DWORD

; //�������������� ������
IDM_EXIT equ 101
IDM_HELP equ 201
bn1_id equ 501
bn2_id equ 502

; //���������
.const
	szMenu db "APP_MENU",0
	szMainMenu db "MENU",0
	szListBox db "LISTBOX",0
	szLabel db "LABEL",0
	szButton db "BUTTON",0
	szEdit db "EDIT",0
	szStatic db "STATIC",0
	szBn1 db "GO",0
	szBn2 db "�����",0
	szTextLabel1 db "1. ->",0
	szTextLabel2 db "2. ->",0
	szTextLabel0 db "3. ->",0
	szTextLabel3 db "������������ ��������",0
	szHelp db "������ ��� ���� ����� ����!",0
	szTip db "� ���� 1. ������ ��� �����",0Dh,0Ah,"� ���� 2. ������ ��� ��������, �� �������� �������� �����",
	0Dh,0Ah,"� ���� 3. ������ ��� ��������, � ������� ��������� �����������",
	0Dh,0Ah,0Dh,0Ah,"����� ������ GO � ��������� �������� ��������",0Dh,0Ah,0Dh,0Ah,
	"������� ����� - ������� ��� ����",0
	szClassName db "Class1",0
	szWindowName db "������������ ������ �5",0

.data
	hInstance HINSTANCE ? ; //������������� ������ ��������
	CommandLine LPSTR ? ; //��������� ������
	hEdit1 HWND ? ; //���������� ���� ���������� ����
	hEdit2 HWND ?
	hEdit3 HWND ?
	hList HWND ? ; //���������� ���� ������
	lpLstBox DWORD ?; //��������� �� ���������� ������� ListBoxProc
	szBuffer db MAX_PATH dup(0)  ; //��������� ����� ��� ����
	szBuffer1 db MAX_PATH dup(0) ; //��������� ����� ��� ��� ������
	szBuffer3 db MAX_PATH dup(0) ; //���� � ��������, � ������� ���������� 
	szBuf db MAX_PATH dup(0)
	szBuf1 db 100 dup(0)
	szBuf2 db 100 dup(0)
    hWin HWND ?

    ;//====================================
	;//==��� ������ �� ��������============
	;//====================================
	n_file dw 0
	szFF_Mask db "\\*.*",0
	szFF_Skip1 db '.',0       ; //����������� ��� �����
	szFF_Skip2 db '..',0      ; //����������� ��� �����
	szDslesh db '\\',0

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
; //��������� ����������:
	LOCAL wc:WNDCLASSEX ; //����� ����
	LOCAL msg:MSG		; //��������� ���������
	LOCAL hwnd:HWND		; //���������� ����
	;//=================================================
	;// ���������� � ����������� ������ ���� WNDCLASSEX
	;//=================================================
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

	;//�������� ����
	invoke CreateWindowEx, 0, ADDR szClassName,ADDR szWindowName,\
	WS_OVERLAPPEDWINDOW,CW_USEDEFAULT,CW_USEDEFAULT,550,460,0,0,hInst,0
	mov hwnd,eax
	mov hWin,eax
	;//==================
	;// ����������� ����
	;//==================
	invoke ShowWindow,hwnd,SW_SHOWNORMAL    ;// �������� ����
	invoke UpdateWindow,hwnd				;// � ������� ��� ��������� WM_PAINT
	;//==========================
	;// ���� ��������� ���������
	;//==========================
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
	   .IF uMsg==WM_DESTROY
			invoke PostQuitMessage,NULL
	   .ELSEIF uMsg == WM_CREATE
			invoke CreateWindowEx,0,ADDR szListBox,0,WS_VISIBLE or WS_VSCROLL or WS_HSCROLL \ 
			or WS_BORDER or WS_CHILD or LBS_HASSTRINGS or LBS_NOINTEGRALHEIGHT \
			or LBS_DISABLENOSCROLL,270,50,250,330,hWnd, 0,hInstance,0
			mov hList,eax ; //���������� ���� ListBox   ;// ��������� ��������� ���� ListBox: ��������� �������
													    ;// ������� ListBoxProc. ��� ������� ���������� WndProc.
													    ;// ��� ���������� ��� ��������� ������� ������� ������
											            ;// � ���� ListBox.
			invoke SetWindowLong,hList,GWL_WNDPROC,ListBoxProc
			mov lpLstBox, eax						  ;// ����������� ������ �������
			;//=================
			;// �������� ������
			;//=================
			;// ������ 1
			invoke CreateWindowEx,0, ADDR szButton,ADDR szBn1,WS_CHILD or WS_VISIBLE,20,115,110,30,hWnd,bn1_id,hInstance,0
			;// ������ 2
			invoke CreateWindowEx,0, ADDR szButton,ADDR szBn2,WS_CHILD or WS_VISIBLE,130,115,110,30,hWnd,bn2_id, hInstance,0
			;//===============================
			;// �������� ���������� ���� Edit
			;//===============================
			invoke CreateWindowEx,0,ADDR szEdit,0, WS_BORDER or WS_VISIBLE or WS_CHILDWINDOW \
				or ES_AUTOHSCROLL or ES_NOHIDESEL,60,10,170,20,hWnd,0,hInstance,0	
			mov hEdit1,eax ; //���������� ���� Edit1(���� ��� ����� ����� �����)
			invoke CreateWindowEx,0,ADDR szEdit,0, WS_BORDER or WS_VISIBLE or WS_CHILDWINDOW \
				or ES_AUTOHSCROLL or ES_NOHIDESEL,60,50,170,20,hWnd,0,hInstance,0	
			mov hEdit2,eax ; //���������� ���� Edit2(���� ��� ����� ����� ����� ������ ������)
			invoke CreateWindowEx,0,ADDR szEdit,0, WS_BORDER or WS_VISIBLE or WS_CHILDWINDOW \
				or ES_AUTOHSCROLL or ES_NOHIDESEL,60,90,170,20,hWnd,0,hInstance,0	
			mov hEdit3,eax ; //���������� ���� Edit2(���� ��� ����� ����� ����� ��� �����������)
			;//===============================================
			;// �������� ������������� ����� � ��������� Static
			;//===============================================
			invoke CreateWindowEx,0, ADDR szStatic,ADDR szTextLabel0,WS_CHILD or WS_VISIBLE, 17,90,30,20,hWnd,0,hInstance,0
			invoke CreateWindowEx,0, ADDR szStatic,ADDR szTextLabel1,WS_CHILD or WS_VISIBLE, 17,10,30,20,hWnd,0,hInstance,0
			invoke CreateWindowEx,0, ADDR szStatic,ADDR szTextLabel2,WS_CHILD or WS_VISIBLE, 17,50,30,20,hWnd,0,hInstance,0
			invoke CreateWindowEx,0, ADDR szStatic,ADDR szTextLabel3,WS_CHILD or WS_VISIBLE, 270,10,200,20,hWnd,0,hInstance,0
			invoke CreateWindowEx,0, ADDR szStatic,ADDR szTip,WS_CHILD or WS_BORDER or WS_VISIBLE, 20,150,220,230,hWnd,0,hInstance,0
		;//==========================
		;// ��������� ������� ������
		;//==========================
	   .ELSEIF uMsg == WM_COMMAND
		   .IF wParam == bn1_id;// ������ ������ 'GO' � ����������� � ListBox
				;//����� ����� �����
				mov n_file,0
				invoke SendMessage,hList,LB_RESETCONTENT,eax,0 ;//������� ������
				invoke GetWindowText,hEdit1,ADDR szBuffer1,MAX_PATH
				invoke GetWindowText,hEdit2,ADDR szBuffer,MAX_PATH
				invoke GetWindowText,hEdit3,ADDR szBuffer3,MAX_PATH
				invoke CreateDirectory,ADDR szBuffer3,NULL
				invoke FindFile,ADDR szBuffer,ADDR szBuffer1,0
		   .ELSEIF wParam == bn2_id ; //������ ������ '�����', ��� ������ ���� ��������
				invoke SendMessage,hList,LB_RESETCONTENT,eax,0 ;//������� ������
				invoke SetWindowText,hEdit1,0
				invoke SetWindowText,hEdit2,0
				invoke SetWindowText,hEdit3,0
		   .ELSEIF wParam == IDM_EXIT;//��������� ��������� ������� ����
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

;//��������� ��������� ���������
ListBoxProc proc hCtl:HWND, uMsg:UINT, wParam:WPARAM,lParam:LPARAM
		;// ����� ������ ������� ListBoxProc ��� ���������
		;// ��������� ���������
		invoke CallWindowProc,lpLstBox,hCtl,uMsg,wParam,lParam
	ret
ListBoxProc endp


;//������� ������ ������ lpFStr - ��� ��������, szFindFile - ��� �������� �����
FindFile proc lpFStr:DWORD,szFindFile:DWORD,dFlag:dword
	LOCAL hFind:DWORD				;//���������� ������
	LOCAL FndData:WIN32_FIND_DATA	;//��������� �������� ���������� ��� ������
	LOCAL Flag:WORD
	LOCAL index:DWORD

	mov Flag,0
	;//�������v ���������� ��������!!!
	push ebx 
    push ecx 
	push edx

	;//����������� � ����� �������� ����� '\\*.*
	invoke lstrcat,lpFStr,ADDR szFF_Mask
	invoke FindFirstFile,lpFStr,ADDR FndData  ;//����� ������ ���� ��������
	
	;//======================================================
	;invoke dwtoa, 100, ADDR szBuf			//===============
	;//======================================================
	cmp eax,INVALID_HANDLE_VALUE  ;//������, ��������� �����
	jne work
		xor eax,eax
		jmp exit
work:	;//����������� ������	
		;//��������� ���������� ������
		mov hFind,eax

		;//�������� ��� ������, �� ������������ ����� '.' � '..'
chek_filename:
		;//�������� �� ��� '.'
		invoke  lstrcmp,ADDR szFF_Skip1,ADDR FndData.cFileName
		or eax,eax
		jz next_file ;//���� �� � ���������� �����
		;//�������� �� ��� '..'
		invoke  lstrcmp,ADDR szFF_Skip2,ADDR FndData.cFileName
		or eax,eax
		jz next_file ;//���� �� � ���������� �����
		cmp dFlag,0
		jne do_work
		;//������� ?
		mov eax,FndData.dwFileAttributes
		and eax,FILE_ATTRIBUTE_DIRECTORY
		jnz do_work_with_derectory

;//������ � ������ �����, ���� ������ �� ���������� ����� �������� ��������
do_work:
		xor eax,eax
		invoke lstrcpy,ADDR szBuf,ADDR FndData.cFileName ;//���������� � �����
		invoke indexDot									 ;//�������� ����������
		invoke lstrcmp,ADDR szBuf,ADDR szBuffer1		 ;//�������� �����
		or eax,eax
		jne next_file  ;//����� �� ���������, �� � ���������� �����
		;//������� ����� '\\*.*'
		invoke lstrlen,lpFStr
		sub eax,3
		add eax,lpFStr
		mov byte ptr[eax],0
		push eax
		;//��������� ��� listbox ������ ���� ������ 1 ���
		;//����������� ����� ������!
		.IF Flag==0
			invoke SendMessage,hList,LB_ADDSTRING,0,lpFStr
		    mov Flag,1
		.endif
		;//���� �������� ����������� �����!
		invoke dwtoa, n_file, ADDR szBuf
		;//��� ����������� �����
		invoke lstrcpy,ADDR szBuf1,lpFStr
		invoke lstrcat,ADDR szBuf1,ADDR FndData.cFileName
		;//���������� ����� ���
		invoke lstrcpy,ADDR szBuf2,ADDR szBuffer3
		invoke lstrcat,ADDR szBuf2,ADDR szDslesh
		invoke lstrcat,ADDR szBuf2,ADDR szBuf
		invoke lstrcat,ADDR szBuf2,ADDR FndData.cFileName
		;//����������� �����
		invoke CopyFile,ADDR szBuf1,ADDR szBuf2,FALSE
		inc n_file
		pop ecx
		mov dword ptr[ecx],'*.*'
		add ecx,4
		mov ecx,0
		jmp next_file
;//������ ������� � �������� �������
do_work_with_derectory:
		;//�������� ����� ����� '*.*'
		invoke lstrlen,lpFStr
		sub eax,3
		add eax,lpFStr
		mov byte ptr[eax],0

		;//�������� ��� �������� � ����
		invoke lstrlen,lpFStr
		add eax,lpFStr
        push eax
        invoke lstrcat,lpFStr,ADDR FndData.cFileName

		;//����������� ����� �������
		invoke FindFile,lpFStr,szFindFile,dFlag

		;// ������� ����� ������ �� �����
        pop ecx
        mov dword ptr[ecx],'*.*'
		mov dword ptr[ecx+4],0

		or eax,eax
		jz stop_scan

next_file:
		;//����� ���������� �����
		invoke  FindNextFile,hFind,ADDR FndData
		or eax,eax
		jnz chek_filename
		mov eax,TRUE
stop_scan:
		;//������� ���������� ������
		push eax
		invoke  FindClose,hFind
		pop eax
exit:	;//������������ �������� � �����
		pop edx 
	    pop ecx 
		pop ebx
		ret
FindFile endp

;//�������� � ������ ��, ��� ������ �����
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