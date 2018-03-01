#include <stdio.h>
#include <stdlib.h>
int * maximum_Sum_Subarray(int arr[],int n);
int max(int x, int y);

int main()
{
	int * ans;
	int arr[] = {3,-2,5,-1};
	ans = maximum_Sum_Subarray(arr, 4);
	printf("%d\n [%d-%d]", ans[0], ans[1], ans[2]);
	return 0;
}




int * maximum_Sum_Subarray(int arr[],int n)		//Overall Time Complexity O(n^3)
{

	int last, first;

	int ans = 0;							// #include<climits>
	//iterate over all possible subarray sizes from 1 to n
	for(int sub_array_size = 1;sub_array_size <= n; ++sub_array_size)
	{
		//iterate over all posible start indexes from 0 to n
		for(int start_index = 0;start_index < n; ++start_index)				//O(n)
		{
			if(start_index+sub_array_size > n)	//Subarray exceeds array bounds
				break;
			int sum = 0;
			for(int i = start_index; i < (start_index+sub_array_size); i++)	//O(n)
				sum+= arr[i];



			int temp;
			temp = max(ans,sum);
			if(temp > ans){
				first = arr[start_index];
				last = arr[(start_index + sub_array_size)-1];
			}


			ans = temp;
			//printf("Ans is %d\n\n",ans );
		}
	}
	int * return_array = (int*) malloc(sizeof(int*) * 3);
	return_array[0] = ans;
	return_array[1] = first;
	return_array[2] = last;

	return return_array;
	//Can also try int s, int e int size;
	//&s, &e, &size
	//max_sum_subarray(int arr[], int n, int *s, int *e, int *size)
}


int max(int x, int y){
	if(x < y)
		return y;
	else
		return x;
}