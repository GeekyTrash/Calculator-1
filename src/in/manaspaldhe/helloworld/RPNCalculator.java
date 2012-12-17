package in.manaspaldhe.helloworld;
import java.io.IOException;
import java.math.*;
/**
 *
 * @author mpaldhe
 */


public class RPNCalculator {

	RPNCalculator(){
	}

    public  boolean checkOperator (String given){
        int length=given.length();
        boolean isOperator=false;
       
        for (int i=0; i<length; i++){
            char ith_char=given.charAt(i);
            if (ith_char==' ');
            if (ith_char=='-'){
                if (length==1){
                    return true;
                }
            }
           
            else if (ith_char=='.' || ith_char=='0' ||ith_char=='0' ||ith_char=='1' ||ith_char=='2' ||ith_char=='3' ||ith_char=='4' ||ith_char=='5' ||ith_char=='6' ||ith_char=='7' ||ith_char=='8' ||ith_char=='9');                   
            else {
                isOperator=true;
                break;
            }
        }
        return isOperator;
    }
   
    public  boolean isFunction (String given){
        if (given.equals("+")) return false;
        if (given.equals("-")) return false;
        if (given.equals("*")) return false;
        if (given.equals("/")) return false;
        if (given.equals("^")) return false;

        else return true;
    }
    
    public  int Precedence (String op){
        if (op.equals("log")){return 5;}
        if (op.equals("sin")){return 5;}
        if (op.equals("cos")){return 5;}
        if (op.equals("tan")){return 5;}
        if (op.equals("arcsin")){return 5;}
        if (op.equals("arccos")){return 5;}
        if (op.equals("arctan")){return 5;}
        if (op.equals("^")){return 4;}
        if (op.equals("*")){return 3;}
        if (op.equals("/")){return 3;}
        if (op.equals("+")){return 2;}
        if (op.equals("-")){return 2;}
        else return 0;
    }

    public  boolean LeftAssociative (String op){
        if (op.equals("^")){return false;}// right
        else return true;
    }

    public  double Power (double base, double exponent){
    	return Math.pow(base, exponent);
    	/*
    	double temp=base;
        for (int i=2; i<=exponent; i++){
            base=base*temp;
        }
        return base;
        */
    }
   
    public  double Operate (double top1, double top2, String Operator){
        if (Operator.equals("+")){
            return top1+top2;
        }
        if (Operator.equals("*")){
            return top1*top2;
        }
        if (Operator.equals("-")){
            return top2-top1;
        }
        if (Operator.equals("/")){
            return top2/top1;
        }
        if (Operator.equals("^")){
            return Power(top2, top1);
        }
        if (Operator.equals("sin")){
            return Math.sin(top1);
        }
        if (Operator.equals("cos")){
            return Math.cos( top1);
        }
        if (Operator.equals("tan")){
            return Math.tan(top1);
        }
        if (Operator.equals("arctan")){
            return Math.atan(top1);
        }
        if (Operator.equals("arcsin")){
            return Math.asin(top1);
        }
        if (Operator.equals("arccos")){
            return Math.acos( top1);
        }
        if (Operator.equals("log")){
            return Math.log(top1);
        }

        else return 0;
    }

    public  String AddSeperator (String input){
        String output="";
        int length=input.length();

        int last_operator=-1;
        //output=output+input.substring(0,1);
        for (int i=0; i<length; i++){
            String current = input.substring(i,i+1);
            if (current.equals("(")){
                if (i-last_operator==1){
                    output+=";"+current;
                    last_operator=i;                   
                }
                else{
                    output+=";*;"+current;
                    last_operator=i;                   
                }
            }
            else if (current.equals(")")){
                output+=";"+current;
            }
            else if (current.equals("^")){
                    output+=";"+current;
                    last_operator=i;                   
                /*if (i-last_operator==1){
                    output+=";"+current;
                    last_operator=i;                   
                }
                else{
                    output+=";"+current;
                    last_operator=i;                   
                }*/
            }
            else if (checkOperator(current)==true){
                if (!current.equals("-")){
                    if (i-last_operator>1){
                        output=output+';';
                    }
                    if ((i>0)&&(!isFunction(input.substring(i-1,i)))){
                        output=output+';';
                    }
                    output=output+current;
                    last_operator=i;
                }
                else{
                    if (i-last_operator==1){
                        output=output+";";
                        output=output+current;                       
                    }
                    else{
                        output=output+";"+current;
                        last_operator=i;                       
                    }
                }
            }
            else {
                if (i-last_operator==1){
                    output=output+";";
                }
                output=output+current;
            }
        }
        return output;
    }
   
    public  double EvaluateRPN (String input)  throws IOException {
        int[] commas= new int[input.length()];
        int comma_counter=0;
       
        for (int i=0; i<input.length(); i++){
            commas[i]=-1;
            if (input.charAt(i) == ';'){
                commas[comma_counter]=i;
                comma_counter++;
            }
        }
        String[] expression = new String[comma_counter+1];
        expression[0]=input.substring(0,commas[0]);
        for (int i=1; i<comma_counter; i++){
            expression[i]=input.substring(commas[i-1]+1, commas[i]);
        }
        expression[comma_counter]=input.substring(commas[comma_counter-1]+1);
       
       
        // Testing the expression
        /*
        for (int i=0; i<=comma_counter; i++){
            System.out.println(expression[i]);
        }
        */
        Stack Calc= new Stack();
       
        for (int i=0; i<=comma_counter; i++){
            if (checkOperator(expression[i])==false){// if the expression is not an operator
                // Push it in stack
                Calc.Push(expression[i]);
            }
            else if (isFunction(expression[i])==true){
                double top1= Double.parseDouble(Calc.Pop().toString());
                double result = Operate(top1, 0, expression[i]);
                Calc.Push(result);
            }
            else {
                double top1= Double.parseDouble(Calc.Pop().toString());
                double top2= Double.parseDouble(Calc.Pop().toString());
                double result= Operate(top1, top2, expression[i] );
                Calc.Push(result);
                //System.out.println(top1+ " " + top2 + " " + result +" "+expression[i]);
            }
        }
       
        double final_answer=Double.parseDouble(Calc.Pop().toString());
        return final_answer;
        //System.out.println(final_answer);

    }
   
    public  String ShuntingYard (String given) throws IOException{
        int length = given.length();
        Queue numbers=new Queue();
        Stack operators=new Stack();
       
        int last_comma=-1;
        boolean new_token=false;
        for (int i=0; i<length; i++){
            String current=given.substring(i,i+1);
            String current_string="";
//            System.out.println(current);
            new_token=false;
            if (current.equals(";")){
                current_string=given.substring(last_comma+1,i);
                new_token=true;
                last_comma=i;
            }
            if (i==length-1){
                current_string=given.substring(last_comma+1);               
                new_token=true;
            }
            if (new_token==true){
 //               System.out.println(current_string);
                if (current_string.equals("(")){
                    operators.Push(current_string);
                }
                else if (current_string.equals(")")){
                    //operators.Print();
                    String top = operators.Top().toString();
                    while (!top.equals("(")){
                        numbers.Insert(operators.Pop());
                        top = operators.Top().toString();
                    }
                    operators.Pop();
                    //operators.Print();
                }

                else if (checkOperator(current_string)==false){
                    numbers.Insert(current_string);               
                }
                else if (isFunction(current_string)==true){
                    operators.Push(current_string);               
                }
                else{// operator
                    while (operators.size>0){
                        String o2= operators.Top().toString();
                        if (((Precedence(current_string)<Precedence(o2)))||((Precedence(current_string)==Precedence(o2))&&(LeftAssociative(current_string)))){
                            numbers.Insert(operators.Pop());
                        }//(!LeftAssociative(current_string))&&
                        else break;
                    }
                    operators.Push(current_string);
                }
            }
        }
        //System.out.println("-----------");
        while(operators.size>0){
            numbers.Insert(operators.Pop());
        }
        String output=numbers.Remove().toString();
        while (numbers.size>0){
            output=output+";"+numbers.Remove().toString();
        }
        return output;
    }   
   
    public String Calculate(String input) throws IOException{
        //System.out.println((AddSeperator(input)));
        //System.out.println("-----------------");
        //System.out.println(ShuntingYard(AddSeperator(input)));
        //System.out.println("-----------------");
        return Double.toString(EvaluateRPN(ShuntingYard(AddSeperator(input))));
    }
   
}