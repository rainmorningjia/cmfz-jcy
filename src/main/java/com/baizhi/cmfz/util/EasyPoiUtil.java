package com.baizhi.cmfz.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Temporal;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Miles
 * @Title: EasyPoiUtil
 * @ProjectName cmfz-jcy
 * @Date 2018/12/25--9:09
 */
public class EasyPoiUtil {
    public static void exportExcel(List<?> list, String title, String sheetName, String filenames,Class<?> pojoClass, HttpServletResponse response){
       Workbook workbook= ExcelExportUtil.exportExcel(new ExportParams(title,sheetName),pojoClass,list);
        try {
            String filename=filenames;
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(filename+".xls","UTF-8"));
            workbook.write(response.getOutputStream());
            if (response.getOutputStream() != null) response.getOutputStream().flush();
            if (response.getOutputStream() == null) response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new RuntimeException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (file == null){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new RuntimeException("excel文件不能为空");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }



}
