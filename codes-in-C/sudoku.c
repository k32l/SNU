#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>
#define N 9

void printSudoku(int arr[N][N]);
void arrayCopy(int* a, int* b, int size){
    memcpy(b, a, size);
}

#define N 9

void printSudoku(int arr[N][N]);

int isAvailable(int puzzle[][9], int row, int col, int num)
{
    int rowStart = (row/3) * 3;
    int colStart = (col/3) * 3;
    int i, j;

    for(i=0; i<9; ++i)
    {
        if (puzzle[row][i] == num) return 0;
        if (puzzle[i][col] == num) return 0;
        if (puzzle[rowStart + (i%3)][colStart + (i/3)] == num) return 0;
    }
    return 1;
}
int fillSudoku(int puzzle[][9], int row, int col)
{
    int i;
    if(row<9 && col<9)
    {
        if(puzzle[row][col] != 0)
        {
            if((col+1)<9) return fillSudoku(puzzle, row, col+1);
            else if((row+1)<9) return fillSudoku(puzzle, row+1, 0);
            else return 1;
        }
        else
        {
            for(i=0; i<9; ++i)
            {
                if(isAvailable(puzzle, row, col, i+1))
                {
                    puzzle[row][col] = i+1;
                    if((col+1)<9)
                    {
                        if(fillSudoku(puzzle, row, col +1)) return 1;
                        else puzzle[row][col] = 0;
                    }
                    else if((row+1)<9)
                    {
                        if(fillSudoku(puzzle, row+1, 0)) return 1;
                        else puzzle[row][col] = 0;
                    }
                    else return 1;
                }
            }
        }
        return 0;
    }
    else return 1;
}


int main(){
    int puzzle[9][9] = {0};
    int i,j,h;
    srand(time(NULL));
    //rand will make a random valuje from 1~45
    //arr[i] = rand() % 45 + 1

    /*
    //Assign values to the first column
    for (i = 0; i < 9; i++){
        puzzle[i][0] = rand() % 9 + 1;
        for (h = 0; h < i; h++) {
            if(i > 0 && puzzle[i][0] == puzzle[h][0])
            {
                puzzle[i][0] = rand() % 9 + 1;
                i--;
            }
        }
    }
*/
    //Assign values to the first row
    for (j = 0; j < 9; j++){
        puzzle[0][j] = rand() % 9 + 1;
        for (h = 0; h < j; h++) {
            if(j > 0 && puzzle[0][j] == puzzle[0][h])
            {
                puzzle[0][j] = rand() % 9 + 1;
                j--;
            }
        }
    }


    printf("Sudoku first step: \n");
    printSudoku(puzzle);
    printf("-----------------------------\n");

    if(fillSudoku(puzzle, 0, 0))
    {
        printf("\n+-----+-----+-----+\n");
        for(i=1; i<10; ++i)
        {
            for(j=1; j<10; ++j) printf("|%d", puzzle[i-1][j-1]);
            printf("|\n");
            if (i%3 == 0) printf("+-----+-----+-----+\n");
        }
    }
    else printf("\n\nNO SOLUTION\n\n");


    printf("-----------------------------\n");
    printf("Sudoku after all steps: \n");
    printSudoku(puzzle);

    int tempPuzzle [9][9];
    arrayCopy((int *) puzzle, (int *) tempPuzzle, sizeof(puzzle));

    // Logic for putting zeros
    //r = (rand() % (max+1-min))+min
    int numberOfchanges = 0;
    int a, b;
    //printf("changes is %d", numberOfchanges);

    /*
    numberOfchanges = (rand() % (6+1-4))+4;
    for (h = 0; h < numberOfchanges; ++h) {

    }
    */
    int displacement = 0;

    for (h = 0; h < 3; ++h) {

        for (j = displacement; j < displacement + 3; j++) {
            i = rand() % (2 + 1 - 0) + 0;
            puzzle[i][j] = 0;
        }
        for (i = 0; i < 3; i++) {
            j = rand() % (2 + 1 - 0) + 0;
            puzzle[i][j + displacement] = 0;

        }

        displacement += 3;
    }

     displacement = 0;
     int nextStep = 3;
    for (h = 0; h < 3; ++h) {

        for (j = displacement; j < displacement + 3; j++) {
            i = rand() % (2 + 1 - 0) + 0;
            puzzle[i+nextStep][j] = 0;
        }
        for (i = 0; i < 3; i++) {
            j = rand() % (2 + 1 - 0) + 0;
            puzzle[i+nextStep][j + displacement] = 0;

        }

        displacement += 3;
    }

    displacement = 0;
    nextStep = 6;
    for (h = 0; h < 3; ++h) {

        for (j = displacement; j < displacement + 3; j++) {
            i = rand() % (2 + 1 - 0) + 0;
            puzzle[i+nextStep][j] = 0;
        }
        for (i = 0; i < 3; i++) {
            j = rand() % (2 + 1 - 0) + 0;
            puzzle[i+nextStep][j + displacement] = 0;

        }

        displacement += 3;
    }

    printf("-----------------------------\n");

    printSudoku(puzzle);
    printf("-----------------------------\n");
    printSudoku(tempPuzzle);


// Logic for putting zeros
    //TO DO:




}
void printSudoku(int arr[N][N]){
    //print a matrix
    int a, b;
    for ( a = 0; a < 9; ++a) {

        for ( b = 0; b < 9; ++b) {
            printf("%d ", arr[a][b]);
        }
        printf("\n");
    }
}


