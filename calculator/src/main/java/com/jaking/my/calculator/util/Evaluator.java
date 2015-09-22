package com.jaking.my.calculator.util;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2015/9/4.
 */
public class Evaluator {
/**********优先级*************************/

        private static class Precedence{
            public int inputSymbol;
            public int topOfStack;

            public Precedence(int inSymbol,int topSymbol){
                inputSymbol=inSymbol;
                topOfStack=topSymbol;
            }
        }

        private static Precedence[] precTable={
                new Precedence(  0,-1),
                new Precedence(  0,0),
                new Precedence(100,0),
                new Precedence(  0,99),
                new Precedence(  6,5),
                new Precedence(  3,4),
                new Precedence(  3,4),
                new Precedence(  1,2),
                new Precedence(  1,2),
        };


    private static class Token{
        private int type=EOL;
        private double value=0;

        public Token(){
            this( EOL );
        }
        public Token(int t){
            this( t,0 );
        }
        public Token(int t,double v){
             type=t;value=v;
        }
        public  int getType(){
            return type;
        }
        public double getValue(){
            return value;
        }
    }
    private static class EvalTokenizer{
        private StringTokenizer str;

        public EvalTokenizer(StringTokenizer is){str=is;}

        public Token getToken(){
            double theValue;
            if (!str.hasMoreTokens())
                return new Token();
            String s=str.nextToken();
            if (s.equals(" "))
                return getToken();
            if (s.equals("^"))
                return new Token(EXP);
            if (s.equals("/"))
                return new Token(DIV);
            if (s.equals("*"))
                return new Token(MULT);
            if (s.equals("("))
                return new Token(OPAREN);
            if (s.equals(")"))
                return new Token(CPAREN);
            if (s.equals("+"))
                return new Token(PLUS);
            if (s.equals("-"))
                return new Token(MINUS);
            try{
                theValue=Double.parseDouble(s);
            }catch (NumberFormatException e){
                System.err.println("类型错误");
                return new Token();
            }
            return new Token(VALUE,theValue);
        }


    }

    public Evaluator(String s){
        opStack=new Stack<Integer>(); opStack.push(EOL);
        postfixStack=new Stack<Double>();
        str=new StringTokenizer(s,"+*-/^() ",true);
    }
    public double getValue(){
        EvalTokenizer tok=new EvalTokenizer(str);
        Token lastToken;

        do{
            lastToken=tok.getToken();
            processToken(lastToken);
        }while (lastToken.getType()!=EOL);

        if (postfixStack.isEmpty()){
            System.err.println("缺少操作式");
            return 0;
        }


        double theResult=postfixStack.pop();
        if (!postfixStack.isEmpty())
            System.err.println("警：丢失操作数");
        return theResult;
    }

    private Stack<Integer> opStack;       //中缀转后缀栈
    private Stack<Double> postfixStack;    //后缀机
    private StringTokenizer str;           //表达式
    private static final int EOL=0;
    private static final int VALUE=1;
    private static final int OPAREN=2;
    private static final int CPAREN=3;
    private static final int EXP=4;
    private static final int MULT=5;
    private static final int DIV=6;
    private static final int PLUS=7;
    private static final int MINUS=8;


    private void processToken(Token lastToken){
        int topOP;
        int lastType=lastToken.getType();
        switch (lastType){
            case VALUE:
                postfixStack.push(lastToken.getValue());
                return;
            case CPAREN:
                while ((topOP=opStack.peek())!=OPAREN&&topOP!=EOL)
                    binaryOp(topOP);
                if (topOP==OPAREN)
                    opStack.pop();
                else
                    System.err.println("少了括号");
                break;
            default:
                while (precTable[lastType].inputSymbol<=
                        precTable[topOP=opStack.peek()].topOfStack)
                    binaryOp(topOP);
                if (lastType!=EOL)
                    opStack.push(lastType);
                break;

        }

    }
    private double getTop(){
        if (postfixStack.isEmpty()){
            System.err.println("缺少操作数");
            return 0;
        }
        return  postfixStack.pop();
    }
    private double postfixPop(){
        if (postfixStack.isEmpty()){
            System.err.println("没有操作数");
            return 0;
        }
        return postfixStack.pop();
    }
    private void binaryOp(int topOP){

        if (topOP==OPAREN){
            System.err.println("没有");
            opStack.pop();
            return;
        }
        double rhs=postfixPop();
        double lhs=postfixPop();
        if (topOP==EXP)
            postfixStack.push(Math.pow(lhs, rhs));
        else if (topOP==PLUS)
            postfixStack.push(lhs+rhs);
        else if (topOP==MINUS)
            postfixStack.push(lhs-rhs);
        else if (topOP==MULT)
            postfixStack.push(lhs*rhs);
        else if (topOP==DIV)
            if (rhs!=0)
                postfixStack.push(lhs/rhs);
            else {
                System.err.println("除数不能为0");
            }
        opStack.pop();


    }

    /**
     * 判断圆括号是否成对
     * @param s
     * @return
     */
    public static boolean isMatch(String s)
    {
        Stack<Character> sc=new Stack<Character>();
        char[] c=s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i]=='('||c[i]=='['||c[i]=='{') {
                sc.push(c[i]);
            }
            else if (c[i]==')') {
                if(sc.isEmpty()){
                    return false;
                }
                else{
                    if (sc.peek()=='(') {
                        sc.pop();
                    }
                }
            }
            else if (c[i]==']') {
                if(sc.isEmpty()){
                    return false;
                }
                else
                {
                    if (sc.peek()=='[') {
                        sc.pop();
                    }
                }
            }else if (c[i]=='}') {
                if(sc.isEmpty()){
                    return false;
                }
                else{
                    if (sc.peek()=='{') {
                        sc.pop();
                    }
                }
            }
        }
        if (sc.empty()) {
            return true;
        }else {
            return false;
        }
    }

}
