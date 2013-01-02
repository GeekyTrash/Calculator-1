package com.manas.anurag.calculator;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.*;
import java.text.*;
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
           
            else if (ith_char=='\u03C0' || ith_char=='P' || ith_char=='I'  || ith_char=='e' ||  ith_char=='.' || ith_char=='0' ||ith_char=='0' ||ith_char=='1' ||ith_char=='2' ||ith_char=='3' ||ith_char=='4' ||ith_char=='5' ||ith_char=='6' ||ith_char=='7' ||ith_char=='8' ||ith_char=='9');                   
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
        if (given.equals("mod")) return false;
        if (given.equals("!")) return false;

        else return true;
    }
    
    public  int Precedence (String op){
        if (op.equals("log")){return 5;}
        if (op.equals("ln")){return 5;}
        if (op.equals("sin")){return 5;}
        if (op.equals("cos")){return 5;}
        if (op.equals("tan")){return 5;}
        if (op.equals("asin")){return 5;}
        if (op.equals("acos")){return 5;}
        if (op.equals("atan")){return 5;}
        if (op.equals("sind")){return 5;}
        if (op.equals("cosd")){return 5;}
        if (op.equals("tand")){return 5;}
        if (op.equals("asind")){return 5;}
        if (op.equals("acosd")){return 5;}
        if (op.equals("atand")){return 5;}
        if (op.equals("abs")){return 5;}
        if (op.equals("sqrt")){return 5;}
        if (op.equals("cbrt")){return 5;}
        if (op.equals("mod")){return 4;}
        if (op.equals("^")){return 4;}
        if (op.equals("!")){return 4;}
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
    public double factorial (double n){
        double ans=1;
        for (int i=1; i<=n; i++){
            ans=ans*i;
        }
        return ans;
    }
   
    public  double Operate (double top1, double top2, String Operator) throws IOException{
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
        if (Operator.equals("!")){
            if (((int) top1) != top1){
                throw new IOException("Float in factorial");
            }
            else{
                return factorial(top1);
            }
        }
        if (Operator.equals("mod")){
            return (top2%top1);
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
        if (Operator.equals("atan")){
            return Math.atan(top1);
        }
        if (Operator.equals("asin")){
            return Math.asin(top1);
        }
        if (Operator.equals("acos")){
            return Math.acos( top1);
        }
        if (Operator.equals("sind")){
            return Math.sin(top1*Math.PI/180);
        }
        if (Operator.equals("cosd")){
            return Math.cos( top1*Math.PI/180);
        }
        if (Operator.equals("tand")){
            return Math.tan(top1*Math.PI/180);
        }
        if (Operator.equals("atand")){
            return (Math.atan(top1))*180/Math.PI;
        }
        if (Operator.equals("asind")){
            return (Math.asin(top1))*180/Math.PI;
        }
        if (Operator.equals("acosd")){
            return (Math.acos( top1))*180/Math.PI;
        }
        if (Operator.equals("abs")){
            return Math.abs(top1);
        }
        if (Operator.equals("log")){
            return Math.log10(top1);
        }
        if (Operator.equals("ln")){
            return Math.log(top1);
        }
        if (Operator.equals("sqrt")){
            return Math.sqrt(top1);
        }
        if (Operator.equals("cbrt")){
            return Math.cbrt(top1);
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
            if ((current.equals("("))){// || ((checkOperator(current)==true)&&(isFunction(current)==true))){
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
            else if (((checkOperator(current)==true)&&(isFunction(current)==true))  && (i>0) && (checkOperator(input.substring(i-1,i))==false)){
                    output+=";*;"+current;
                    last_operator=i;                   
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
                    if ((i>0) && (input.charAt(i-1)=='(')){
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
            if (expression[i].equals("(")){
                throw new IOException("Incorrect Brackets");
            }
            if (checkOperator(expression[i])==false){// if the expression is not an operator
            	if (expression[i].equals("\u03C0")){
                    Calc.Push(Double.toString(Math.PI));            		
            	}
            	else if (expression[i].equals("e")){
                    Calc.Push(Double.toString(Math.E));            		
            	}
            	else{
                	// Push it in stack
                    Calc.Push(expression[i]);
            	}
            }
            else if (isFunction(expression[i])==true){
            	if (expression[i].equalsIgnoreCase("\u03C0")){
            		Calc.Push(Math.PI);                    
            	}
            	else{
                	double top1= Double.parseDouble(Calc.Pop().toString());
                    double result = Operate(top1, 0, expression[i]);
                    Calc.Push(result);
            	}
            }
            else {
                if (expression[i].equals("!")){
                    double top1= Double.parseDouble(Calc.Pop().toString());
                    double result= Operate(top1, 0, expression[i] );                    
                    Calc.Push(result);                    
                }
                else{
                    double top1= Double.parseDouble(Calc.Pop().toString());
                    double top2= Double.parseDouble(Calc.Pop().toString());
                    double result= Operate(top1, top2, expression[i] );
                    Calc.Push(result);                    
                }
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

    public static int[] Fractionize (double in){
        int i=10;
        double[] z = new double[i];
        double[] N = new double[i];
        double[] D = new double[i];
        if (((int) in) == in){
        	N[9]=in;
        	D[9]=1;
        }
        else{
	        z[1]=in;
	        D[0]=0;
	        D[1]=1;
	        for (i=2; i<10; i++){
	            z[i]=1/(z[i-1]- ((int) z[i-1]));
	            D[i]=D[i-1]*((int)z[i])+D[i-2];
	            N[i]=(int) Math.round(in*D[i]);
	            if (Math.abs(in-N[i]/D[i])< 0.0001){
	                z[9]=z[i];
	                D[9]=D[i];
	                N[9]=N[i];
	                break;
	            }
	        }
        }
        int[] ans = new int[2];
        ans[0]=(int) N[9];
        ans[1]=(int) D[9];
        
        //System.out.println(ans[1]);
        //System.out.println(ans[0]);
        //System.out.println((double) ans[1] / (double) ans[0]);
        return ans;

    }

    public String[] Calculate_Fraction (String input)throws IOException{
        double res= EvaluateRPN(ShuntingYard(AddSeperator(input)));
        int[] ans= new int[2];
        ans=Fractionize(res);
        String[] result = new String[2];
        result[0]=Integer.toString(ans[0]);
        result[1]=Integer.toString(ans[1]);
        return result;
    }
    
    
    public String Calculate(String input) throws IOException{
        double res= EvaluateRPN(ShuntingYard(AddSeperator(input)));
        if (((int) res) == res){
        	return Integer.toString((int) res);
        }
        if (res<1000000){
            DecimalFormat df = new DecimalFormat("0.###");
        	return (df.format(res));
        }
        else{        	
            DecimalFormat df = new DecimalFormat("0.###E0");
        	return (df.format(res));
        }
    }
   
}