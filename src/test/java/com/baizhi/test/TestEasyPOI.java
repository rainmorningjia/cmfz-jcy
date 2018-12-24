package com.baizhi.test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.cmfz.entity.Student;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Miles
 * @Title: TestPOI
 * @ProjectName cmfz-jcy
 * @Date 2018/12/24--16:43
 */
public class TestEasyPOI {
    @Test
    public void testExport(){
        List<Student> list=new ArrayList<>();
        list.add(new Student(1,"xiaohuhu1",new Date(),18));
        list.add(new Student(2,"xiaohuhu2",new Date(),18));
        list.add(new Student(3,"xiaohuhu3",new Date(),18));
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("学生", "测试", "测试"),
                Student.class, list);
        try {
            workbook.write(new FileOutputStream(new File("E:/easypoi.xls")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
