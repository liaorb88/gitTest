package com.szreach.test;

import com.szreach.test.calc.Calc;
import org.testng.annotations.Test;

public class TestCalc {
    private static int x;
    private static int y;
    private static String method;

    public int calc(String method,int x,int y){
        Calc c = new Calc();
        if(method.equals("add"))
            return c.add(x,y);
        if(method.equals("minus"))
            return c.minus(x,y);
        throw new RuntimeException("不是方法");
    }

    @Test
    public void calc(){
        TestCalc t = new TestCalc();
        t.calc("add",5,5);
    }

    @Test
    public void minus(){
        TestCalc t = new TestCalc();
        t.calc("minus",8,5);
    }

}
