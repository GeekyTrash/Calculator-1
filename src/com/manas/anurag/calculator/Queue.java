package com.manas.anurag.calculator;

import java.io.IOException;

/**
 *
 * @author mpaldhe
 */

/**
 *
 * @author mpaldhe
 */
public class Queue {
   
    Object [] queue= new Object [1000];
    int size =0;
    Queue (){       
    }
   
   
    void Insert (Object o){
        queue[size]=o;
        size++;       
    }

    Object Front () throws IOException{
        if (size<=0){
            throw new IOException("Queue is empty");
        }
        else{
            return queue[0];
        }
    }

    Object Remove () throws IOException{
        if (size<=0){
            throw new IOException("Queue is empty");
        }
        else{
            Object out=queue[0];
            for (int i=1; i<size; i++){
                queue[i-1]=queue[i];
            }
            size--;
            return out;
        }
    }
   
    void Print (){
        System.out.println("Printing the queue");

        for (int i=0; i<size; i++){
            System.out.print(queue[i] + ",  ");
        }
        System.out.println();
    }
}