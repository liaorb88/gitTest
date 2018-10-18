package com.szreach.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    //private Logger logger = Logger.getLogger(ExcelUtil.class);

    public Map<String,Object> readExcel(String filePath,List<String> list,int sheet,int rowNum) throws IOException {

        //根据excel文件创建一个Workbook(工作薄)对象，book变量将引用包含Excel文件中所有行的对象
        XSSFWorkbook workbook = null;
        Map<String,Object> result = new HashMap<String, Object>();
        InputStream inputStream  = null;
        try {
            inputStream  = new FileInputStream(new File(filePath));
            workbook = new XSSFWorkbook(inputStream);


            //获取第一个Sheet页中的第rowNum行数据
            XSSFRow row = (XSSFRow) workbook.getSheetAt(sheet).getRow(rowNum);
            for (int i = 0; i< list.size(); i++){

                //对空格处理
                XSSFCell cell = row.getCell(i + 1);
                if (null == cell) {
                    result.put(list.get(i), "");
                } else {

                    //XSSFCell cell = row.getCell(i + 1);

                    if (null != cell) {
                        switch (cell.getCellType()) {
                            case XSSFCell.CELL_TYPE_STRING: // 字符串
//                            System.out.println(cell.getStringCellValue()
//                                   + "  字符串 ");
                                result.put(list.get(i),cell.getStringCellValue());

                                break;

                            case XSSFCell.CELL_TYPE_NUMERIC: // 数字
                                result.put(list.get(i),cell.getNumericCellValue());
                                break;
                            case XSSFCell.CELL_TYPE_BLANK: // 空
                                result.put(list.get(i),"");
                        }

                    }else {
                        result.put(list.get(i),"");
                    }
                }

            }
        } catch (Exception e) {

            //logger.info("!!!!!!!!" + e.getMessage() + "!!!!!!!!!!!!!!!");
        }finally {
            if(null !=inputStream ){
                inputStream.close(); //停止从文件读取行
            }

        }

        return  result;
    }



    public Map<String,Object> readExcel(String filePath,int sheet,int rowNum) throws IOException {

        //根据excel文件创建一个Workbook(工作薄)对象，book变量将引用包含Excel文件中所有行的对象
        XSSFWorkbook workbook = null;
        Map<String,Object> result = new HashMap<String, Object>();
        InputStream inputStream  = null;
        try {
            inputStream  = new FileInputStream(new File(filePath));
            workbook = new XSSFWorkbook(inputStream);

            //获取第一个Sheet页中的第rowNum行数据
            XSSFRow row = (XSSFRow) workbook.getSheetAt(sheet).getRow(rowNum);
            XSSFRow filed = (XSSFRow) workbook.getSheetAt(sheet).getRow(0);
            short x = filed.getLastCellNum();
            for (int i = 0; i<x; i++){

                //对空格处理
                XSSFCell cell = row.getCell(i);
                if (null == cell) {
                    result.put(filed.getCell(i).getStringCellValue(), "");
                } else {

                    //XSSFCell cell = row.getCell(i + 1);

                    if (null != cell) {
                        switch (cell.getCellType()) {
                            case XSSFCell.CELL_TYPE_STRING: // 字符串
//                            System.out.println(cell.getStringCellValue()
//                                   + "  字符串 ");
                                result.put(filed.getCell(i).getStringCellValue(),cell.getStringCellValue());
                                break;

                            case XSSFCell.CELL_TYPE_NUMERIC: // 数字
                                result.put(filed.getCell(i).getStringCellValue(),cell.getNumericCellValue());
                                break;
                            case XSSFCell.CELL_TYPE_BLANK: // 空
                                result.put(filed.getCell(i).getStringCellValue(),"");
                                break;
                        }

                    }else {
                        result.put(filed.getCell(i).getStringCellValue(),"");
                    }


                }

            }
        } catch (Exception e) {

            //logger.info("####################" + e.getMessage() + "####################");
        }finally {
            if(null !=inputStream ){
                inputStream.close(); //停止从文件读取行
            }

        }

        return  result;
    }
}
