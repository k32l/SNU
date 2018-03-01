#include<stdio.h>
#include<stdlib.h>
#include <string.h>
typedef struct Node 
{
        int data;
        struct Node *next;
}node;
void insert(node *pointer, int data)
{
        /* Iterate through the list till we encounter the last node.*/
        while(pointer->next!=NULL)
        {
                pointer = pointer -> next;
        }
        /* Allocate memory for the new node and put data in it.*/
        pointer->next = (node *)malloc(sizeof(node));
        pointer = pointer->next;
        pointer->data = data;
        pointer->next = NULL;
}
int find(node *pointer, int key)
{
        pointer =  pointer -> next; //First node is dummy node.
        /* Iterate through the entire linked list and search for the key. */
        while(pointer!=NULL)
        {
                if(pointer->data == key) //key is found.
                {
                        return 1;
                }
                pointer = pointer -> next;//Search in the next node.
        }
        /*Key is not found */
        return 0;
}
void remove (node *pointer, int data)
{
        /* Go to the node for which the node next to it has to be deleted */
        while(pointer->next!=NULL && (pointer->next)->data != data)
        {
                pointer = pointer -> next;
        }
        if(pointer->next==NULL)
        {
                printf("Element %d is not present in the list\n",data);
                return;
        }
        /* Now pointer points to a node and the node next to it has to be removed */
        node *temp;
        temp = pointer -> next;
        /*temp points to the node which has to be removed*/
        pointer->next = temp->next;
        /*We removed the node which is next to the pointer (which is also temp) */
        free(temp);
        /* Beacuse we deleted the node, we no longer require the memory used for it . 
           free() will deallocate the memory.
         */
        return;
}
void print(node *pointer)
{
        if(pointer==NULL)
        {
                return;
        }
        printf("%d ",pointer->data);
        print(pointer->next);
}


/* function to swap data of two nodes a and b*/
void swap(node *a, node *b)
{
    int temp = a->data;
    a->data = b->data;
    b->data = temp;
}

void bubbleSort(node *pointer)
{
        pointer =  pointer -> next; //First node is dummy node.
        int swapped, i;
         node *ptr1;
         node *lptr = NULL;
         ptr1 = pointer;

        /* Checking for empty list */
        if (ptr1 == NULL)
                return;

        do
        {
                swapped = 0;


                while (ptr1->next != lptr)
                {
                        if (ptr1->data > ptr1->next->data)
                        {
                                swap(ptr1, ptr1->next);
                                swapped = 1;
                        }
                        ptr1 = ptr1->next;
                }
                lptr = ptr1;
                ptr1 = pointer;
        } while (swapped);
}

int main()
{
        /* start always points to the first node of the linked list.
           temp is used to point to the last node of the linked list.*/
        node *start,*temp;
        start = (node *)malloc(sizeof(node)); 
        temp = start;
        temp -> next = NULL;
        /* Here in this code, we take the first node as a dummy node.
           The first node does not contain data, but it used because to avoid handling special cases
           in insert and delete functions.
         */


        while(1)
        {
                char query[10];
                scanf("%s",query);
                if(strcmp(query,"insert") == 0)
                {
                        int data;
                        scanf("%d",&data);
                        insert(start,data);
                }
                else if(strcmp(query,"delete") == 0)
                {
                        int data;
                        scanf("%d",&data);
                        remove(start,data);
                }
                else if(strcmp(query,"print") == 0)
                {
                        bubbleSort(start);
                        //printf("The list is ");
                        print(start->next);
                        printf("\n");
                }
                else if(strcmp(query,"find") == 0)
                {
                        int data;
                        scanf("%d",&data);
                        int status = find(start,data);
                        if(status)
                        {
                                printf("True\n");
                        }
                        else
                        {
                                printf("False\n");

                        }
                }
                else if (strcmp(query,"exit") == 0)
                {
                        //printf("The list is ");
                        print(start->next);
                        printf("\n");
                        break;
                }
        }


}
