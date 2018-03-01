#include <stdio.h>

int main (){

	int endY = 0;
	int cnword = 0;
	int start = 0;
	int get = 0;
	
	char end = ' ';
	char c, s, a, begin;


	while ((c = getchar()) != EOF) {
		
		if (c == ' ' && s == 'y'){
			endY++;
		}

		if(c == 'c' && s == ' '){
			start = 1;
		}

		if (c == ' ') {
		 	
		 	if (s == 'n' && start == 1){
		 		cnword++;
		 		start = 0;
		 	} else {
		 		start = 0;
		 	}
		 	
		}

		
		s = c;


}
//printf("Start with c are: %d\n",start );
printf("End with y are : %d\n", endY );
printf(" End with n the: %d\n", cnword );
	//printf("%c\n", s);
	
}
