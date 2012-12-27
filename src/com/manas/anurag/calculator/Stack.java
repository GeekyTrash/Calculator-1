package com.manas.anurag.calculator;
import java.io.IOException;

/**
 *
 * @author mpaldhe
 */
public class Stack {

    Object [] stack= new Object [1000];
    int size =0;
    Stack (){        
    }
    
    void Push (Object o){
        stack[size]=o;
        size++;        
    }

    Object Top () throws IOException{
        if (size<=0){
            throw new IOException("Stack is empty");
        }
        else{
            return stack[size-1];
        }
    }

    Object Pop () throws IOException{
        if (size<=0){
            throw new IOException("Stack is empty");
        }
        else{
            size--;
            return stack[size];
        }
    }
    
    void Print (){
        System.out.println("Printing the stack");
        for (int i=0; i<size; i++){
            System.out.print(stack[i] + ",  ");
        }
        System.out.println();
    }
}