#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>



void chopN(char *str, size_t n)
{
    size_t len = strlen(str);
    if(n > len)
        return;
    memmove(str, str + n, len - n + 1);
}

void swap(char *first, char *second)
{
    char *temp;
    temp = (char*)malloc(100);

    strcpy(temp, first);
    strcpy(first, second);
    strcpy(second, temp);
}
void strrev(char *str){
    int i, j;
    char temp;
    i=0;
    j = strlen(str) - 1;

    while(i < j) {
        temp = str[i] ;
        str[i] = str[j];
        str[j] = temp;
        i++;
        j--;
    }
}

int getLength(long long value) {
    int counter = 0;
    while (value != 0) {
        counter++;
        value /= 10;
    }
    return counter;
}




 void addition(char in1[20], char in2[20], char *output){
     int len1, len2, i, tmpResult, remainder = 0, sum;

     int result[30] = {0};

     int minSize = 0, maxSize = 0;
     int num1[100] = {0};
     int num2[100] = {0};


     len1 = (int) strlen(in1);
     len2 = (int) strlen(in2);



     strrev(in1);
     strrev(in2);

     if(len2 > len1)
     {
         swap(in1, in2);
     }

     if (len1 >= len2)
     {
         maxSize = len1;
         minSize = len2;
     } else if (len1 < len2)
     {
         maxSize = len2;
         minSize = len1;
     }

     for(i = 0; maxSize > i; i++ )
     {
         num1[i] = (int) in1[i] - 48;
         // printf("%d ", num1[i]);
     }

     for (i = 0; i < minSize; i++) {
         num2[i] = (int) in2[i] - 48;
         //   printf("%d ", num2[i]);
     }

    // printf("\n");

    // printf("%s + %s = ", tmp1, tmp2);
     for(i = 0; i < maxSize; i++)
     {
         tmpResult = num1[i] + num2[i];

         if (remainder > 0)
         {
             tmpResult += remainder;
         }

         sum = tmpResult % 10;
         remainder = tmpResult / 10;
         //TO DO
         result[i] = sum;

     }

     if (remainder > 0)
     {
         result[i] = remainder;
         maxSize++;
         result[i+1] = 0;
     }


     //Convert from int array to String

     int convert = 0;
     int count;
     count = maxSize - 1;
     int j;
     for(j = 0; j <= count; count--) {
         output[convert] = result[count] + '0';
         convert++;
     }




 }
int main(){

    char n1[20];
    char n2[20];
    char toSum1[20];
    char toSum2[20];
    char answer[30] = {0};
    int len1, len2;
    int result[20];
    long num2[20];
    long summations[10];
    long numLong1;
    int i = 0, j = 0;



    printf("Input 1: ");
    scanf("%s", n1);

    printf("Input 2: ");
    scanf("%s", n2);


    printf("%s * %s = ", n1, n2);
    if (n1[0] == '-')
        chopN(n1,1);


    if (n2[0] == '-')
        chopN(n2,1);


    len1 = strlen(n1);
    len2 = strlen(n2);
    //convert from string to long
    for (i = 0; i <len1; ++i) {
        numLong1 = numLong1 * 10 + (n1[i] - '0');
    }
   // printf("num1 in long datatype is %lli\n", numLong1);

    //convert second number from string arr to long arr
  //  printf("Second number is ");
    for(i = 0; i < len2; i++ )
    {
        num2[i] = (long) n2[i] - 48;
       //  printf("%lli", num2[i]);
    }
   // printf("\n");
    /*
    for (i = 0; i < len1; ++i) {
        summations[i] = numLong1 * num2[i];
    }
    */
   // printf("len2 is %d\n", len2);
    int h = 0;
    for (i = 0; i < len2; len2--) {

        summations[len2 - 1] = numLong1 * num2[h];
        h++;
    }


    for (i = 0; i < len1; ++i) {
        //printf("Arr of summations %d is %lli\n", i, summations[i]);
    }


    sprintf(toSum1,"%lli",summations[0]);
    //printf("First string is %s\n", toSum);
    int clock = 1, c;

    for (i = 1; i <= len1; i++) {

        sprintf(toSum2, "%lli", summations[i]);
        for (c = 0;c < clock; c++ ) {
            strcat(toSum2, "0");
        }
        clock++;
       // printf("string is %s\n", toSum1);

        addition(toSum1,toSum2,answer);

        strcpy(toSum1, answer);

    }
    printf("%s\n", answer);


}
