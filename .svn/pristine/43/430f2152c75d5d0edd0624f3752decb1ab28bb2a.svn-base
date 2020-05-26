package com.gpdi.hqplus.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @Description 导入导出excel表格
 * @Author wzr
 * @CreateDate 2019-07-15
 * @Time 17:35
 */
@Slf4j
@Component
public class ExcelUtil<T> {

    /**
     * 导出excel表格
     *
     * @param title 表格的文件名，也是表格首行的标题名称
     * @param params 为 数据字段名（String） - 中文字段说明（String）的LinkedHashMap(这个字段构建起了比较麻烦，以后可能可以进行改进)
     * @param dataSet 数据，需要为实现迭代器接口的集合
     * @param response 响应，用来输出文件和浏览器下载
     */
    public void exportExcel(String title, LinkedHashMap<String, String> params, Collection<T> dataSet, HttpServletResponse response){
        //工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //工作表
        HSSFSheet sheet = workbook.createSheet(title+".xls");
        //设置默认列宽
        sheet.setDefaultColumnWidth(20);
        //构建标题行
        buildTitle(workbook,sheet,title,params.size());
        //构建字段行
        buildFields(workbook,sheet,params);
        //构建数据行
        buildDates(workbook,sheet,dataSet,params);
        //生成本地excel文件测试，根目录
        buildExcelFile(title+".xls",workbook);
        //输出流，浏览器下载
        browserDownload(response,workbook,title);
    }

    /**
     * 构建标题行
     * @param workbook
     * @param sheet
     * @param titleName
     * @param titleLength
     */
    private void buildTitle(HSSFWorkbook workbook,HSSFSheet sheet,String titleName,int titleLength){
        HSSFRow topTitle = sheet.createRow(0);
        HSSFCell topTitleCell = topTitle.createCell(0);
        //合并第一第二行
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 1, 0, titleLength - 1);
        sheet.addMergedRegion(cellRangeAddress);
        //设置标题行样式
        topTitleCell.setCellStyle(getTitleStyle(workbook,sheet,cellRangeAddress));
        //设置标题行的值
        topTitleCell.setCellValue(titleName);
    }

    /**
     * 设置标题行样式
     * @param workbook
     * @return
     */
    private CellStyle getTitleStyle(Workbook workbook,HSSFSheet sheet,CellRangeAddress cellRangeAddress){
        CellStyle cellStyle = workbook.createCellStyle();
        //居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //四面边框
        RegionUtil.setBorderLeft(BorderStyle.THIN,cellRangeAddress,sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN,cellRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.THIN,cellRangeAddress,sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN,cellRangeAddress,sheet);
        //设置字体
        Font font = workbook.createFont();
        //加粗
        font.setBold(true);
        //12号字体
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 构建参数行
     * @param workbook
     * @param sheet
     * @param params
     */
    private void buildFields(HSSFWorkbook workbook,HSSFSheet sheet,LinkedHashMap<String,String>params){
        //标题行合并了一二行（0,1行），所以从2开始
        HSSFRow headersRow = sheet.createRow(2);
        HSSFCell headersCell;
        int cellNum = 0;
        //linkedHashMap可以保证数据按顺序取出
        Iterator<Map.Entry<String, String>> paramsIt = params.entrySet().iterator();
        while (paramsIt.hasNext()){
            headersCell = headersRow.createCell(cellNum);
            cellNum++;
            headersCell.setCellStyle(getHeadersStyle(workbook));
            headersCell.setCellValue(paramsIt.next().getValue());
        }
    }

    /**
     * 设置参数行样式和值
     * @param workbook
     * @return
     */
    private CellStyle getHeadersStyle(Workbook workbook){
        CellStyle cellStyle = workbook.createCellStyle();
        //四面边框
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        Font font = workbook.createFont();
        font.setBold(true);
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 构建数据行
     * @param workbook
     * @param sheet
     * @param dataSet
     * @param params
     */
    private void buildDates(Workbook workbook,HSSFSheet sheet,Collection<T> dataSet,LinkedHashMap<String,String> params){
        Iterator<T> it = dataSet.iterator();
        HSSFRow dataRow;
        int rowNum = 3;

        while(it.hasNext()){
            T data =it.next();
            dataRow = sheet.createRow(rowNum);
            rowNum++;
            int cellNum = 0;
            for (Map.Entry<String,String>entry : params.entrySet()){
                HSSFCell dataCell = dataRow.createCell(cellNum);
                cellNum++;
                String fieldName = entry.getKey();
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                try {
                    Class dataClass = data.getClass();
                    Method getMethod = dataClass.getMethod(getMethodName, new Class[]{});
                    Object value = getMethod.invoke(data, new Object[]{});

                    String valueText = null;
                    if (value == null){
                        valueText="";
                    }
                    /*else if*/
                    else {
                        valueText = value.toString();
                    }
                    if (valueText!=null){
                        setCellStyleAndValue(workbook,dataCell,valueText);
                    }
                }catch (Exception e){

                }
            }
        }
    }

    /**
     * 设置数据cell的格式和值
     * @param workbook
     * @param cell
     * @param valueText
     */
    private void setCellStyleAndValue(Workbook workbook,HSSFCell cell,String valueText){
        HSSFRichTextString richString = new HSSFRichTextString(valueText);
        Font font = workbook.createFont();
        font.setColor(IndexedColors.BLACK.index);
        richString.applyFont(font);
        cell.setCellValue(richString);
    }

    /**
     * 生成excel本地文件
     * @param fileName
     * @param workbook
     */
    private void buildExcelFile(String fileName,HSSFWorkbook workbook){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 浏览器下载
     * @param response
     * @param workbook
     * @param fileName
     */
    private void browserDownload(HttpServletResponse response,Workbook workbook,String fileName){
        try {
            //清空response
            response.reset();
            //设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName+".xls");
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            //将excel写入到输出流中
            workbook.write(os);
            os.flush();
            os.close();
            log.info("设置浏览器下载成功！");
        } catch (Exception e) {
            log.info("设置浏览器下载失败！");
            e.printStackTrace();
        }
    }
}
