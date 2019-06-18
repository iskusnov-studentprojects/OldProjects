long int result = 10;

void function(short int par1, long int par2){
	while (par1>0){
		par1--;
	}
	result = result - par1;
}

void function2(){
	function(40, 0x17);
}

void main(){
	/*Тестовый файл с кодом*/
	short int i = 0;
	while(i < 10) i++;
	long int j = 0x0;
	//Однострочный комментарий
	function2();
	j = 12*12/36;
	result = (result - i + j)*6;
}
