package com.szreach.test;

import com.szreach.test.calc.Calc;
import com.szreach.util.ExcelUtil;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class TestCalc {
    private static int x;
    private static int y;
    private static String method;

    ExcelUtil excelUtil =new ExcelUtil();

    public int calc(String method,int x,int y){
        Calc c = new Calc();
        if(method.equals("add"))
            return c.add(x,y);
        if(method.equals("minus"))
            return c.minus(x,y);
        throw new RuntimeException("不是方法");
    }

    @Test
    public void calc() throws IOException {
        TestCalc t = new TestCalc();
        Map<String, Object> map = excelUtil.readExcel("/home/testCalc/CalcCase.xlsx", 0,  1);
        t.calc("add",Integer.parseInt((String)map.get("x")),Integer.parseInt((String)map.get("y")));
    }

    @Test
    public void minus() throws IOException{
        TestCalc t = new TestCalc();
        Map<String, Object> map = excelUtil.readExcel("/home/testCalc/CalcCase.xlsx", 0,  2);
        t.calc("minus",Integer.parseInt((String)map.get("x")),Integer.parseInt((String)map.get("y")));
    }

}
