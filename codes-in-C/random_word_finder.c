#include <stdio.h>
#include <string.h>

int main(){
	FILE *fd;
	FILE *ofd;
	char word[30];
	char ret_code;
	char input[30];
	int number_of_words = 0;

	printf("Type a word to search: ");
	gets(input);
	
	if (!(fd = fopen("week4_data1.in", "r")))		
	{
		perror("Failed to open file");
	}
	while(!feof(fd)){
		ret_code = fscanf(fd, "%s", word);
	


	printf("The word is %s\n",word);
	printf("The input is %s\n",input);
		if (strcmp(input,word) == 0)
			number_of_words++;
		

		//printf("%s\n", word);
	}
	printf("Number of substring '%s': %d\n",input,number_of_words);
}