#include <stdio.h>
#include <string.h>

int main()
{
    FILE *fd;
    char word[100] = {0, };
    char ret_code;
    unsigned int num_words_end_with_y = 0;
    unsigned int num_words_c_and_n = 0;
    unsigned int num_words = 0;


    if(!(fd = fopen("in2.txt", "r")))
        perror("Failed to open file");
    do{
        ret_code = fscanf(fd, "%s", word);
        if(word != NULL && ret_code != EOF)
        {
            num_words++;
            if(word[strlen(word)-1] == 'y')
                num_words_end_with_y++;
            if(word[0] == 'c' && word[strlen(word)-1] == 'n')
            	num_words_c_and_n++;
        }
    }while(ret_code != EOF);

    printf("\nNumber of words processed: %d\n", num_words);
    printf("Number of words ending with y: %d\n\n", num_words_end_with_y);
    printf("Number of words c____n : %d\n", num_words_c_and_n);
