.386
.model flat,stdcall
option casemap:none
include d:\masm32\include\windows.inc
include d:\masm32\include\user32.inc
include d:\masm32\include\kernel32.inc
includelib d:\masm32\lib\user32.lib
includelib d:\masm32\lib\kernel32.lib
include d:\masm32\include\gdi32.inc
includelib d:\masm32\include\gdi32.lib

WinMain proto :DWORD, :DWORD, :DWORD, :DWORD

.data
f db 0
ClassName db "Form1",0
AppName db "PL3",0
hInstance HINSTANCE ?
CommandLine LPSTR ?

.code
start:

invoke GetModuleHandle, NULL
mov hInstance, eax

invoke GetCommandLine
mov CommandLine, eax

invoke WinMain, hInstance, NULL, CommandLine, SW_SHOWDEFAULT
invoke ExitProcess, eax

WinMain proc hInst:HINSTANCE, hPrevInst:HINSTANCE, CmdLine:LPSTR, CmdShow:DWORD
    LOCAL wc:WNDCLASSEX
    LOCAL msg:MSG
    LOCAL hwnd:HWND
    
    ; заполнение стpуктуpы wc
    mov   wc.cbSize,SIZEOF WNDCLASSEX
    mov   wc.style, CS_HREDRAW or CS_VREDRAW or CS_DBLCLKS
    mov   wc.lpfnWndProc, OFFSET WndProc
    mov   wc.cbClsExtra,NULL

    mov   wc.cbWndExtra,NULL
    push  hInstance
    pop   wc.hInstance
    mov   wc.hbrBackground,COLOR_WINDOW+1

    mov   wc.lpszMenuName,NULL
    mov   wc.lpszClassName,OFFSET ClassName
    invoke LoadIcon,NULL,IDI_APPLICATION
    mov   wc.hIcon,eax

    mov   wc.hIconSm,eax
    invoke LoadCursor,NULL,IDC_ARROW
    mov   wc.hCursor,eax
    invoke RegisterClassEx, addr wc  ; pегистpация класса окна
    invoke CreateWindow,ADDR ClassName,
                ADDR AppName,
                WS_OVERLAPPEDWINDOW, ;Стиль окна
                CW_USEDEFAULT,
                CW_USEDEFAULT,
                CW_USEDEFAULT,
                CW_USEDEFAULT,
                HWND_DESKTOP, ;Дескриптор родительского окна
                NULL,
                hInst, ;Дескриптор приложения
                NULL
    mov   hwnd,eax

    invoke ShowWindow, hwnd,CmdShow ; отобpазить окно
    invoke UpdateWindow, hwnd ; обновить клиентскую область

    .WHILE TRUE   ; цикл обработки сообщения
       invoke GetMessage, ADDR msg,NULL,0,0
    .BREAK .IF (!eax)
       invoke TranslateMessage, ADDR msg
       invoke DispatchMessage, ADDR msg
    .ENDW
     mov     eax,msg.wParam ; сохpанение возвpащаемого значения в eax
     ret
     
WinMain endp

WndProc proc hWnd:HWND, uMsg:UINT, wParam:WPARAM, lParam:LPARAM
    LOCAL hdc:HDC
    LOCAL ps:PAINTSTRUCT
    LOCAL lpRect :RECT
    
    .IF uMsg==WM_DESTROY            ; если пользователь закpывает окно
        invoke PostQuitMessage,NULL ; выходим из пpогpаммы
    .ELSEIF uMsg==WM_LBUTTONDBLCLK  ; двойной счелчек левой кнопкой мыши
        .IF f==0
            mov f,1
        .ELSE
            mov f,0
        .ENDIF
    .ELSEIF uMsg==WM_KEYDOWN
        .IF f==1
            pusha
            invoke GetWindowRect,hWnd,ADDR lpRect ;Получить текущую позицию окна
            mov eax,lpRect.top
            mov ebx,lpRect.right
            sub ebx,lpRect.left ;Вычислить ширину
            mov ecx,lpRect.bottom
            sub ecx,lpRect.top;Вычислить высоту
            .IF wParam==VK_1
                add eax,1
            .ELSEIF wParam==VK_2
                add eax,2
            .ELSEIF wParam==VK_3
                add eax,3
            .ELSEIF wParam==VK_4
                add eax,4
            .ELSEIF wParam==VK_5
                add eax,5
            .ELSEIF wParam==VK_6
                add eax,6
            .ELSEIF wParam==VK_7
                add eax,7
            .ELSEIF wParam==VK_8
                add eax,8
            .ELSEIF wParam==VK_9
                add eax,9
            .ENDIF
            invoke SetWindowPos,hWnd,HWND_TOP,lpRect.left,eax,ebx,ecx,SWP_SHOWWINDOW;Установить новую позицию окна
            popa
        .ENDIF
    .ELSE
        invoke DefWindowProc,hWnd,uMsg,wParam,lParam ; Дефаултная функция обpаботки окна
        ret
    .ENDIF
    xor eax,eax

    ret
WndProc endp

end start