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
    //分支归并
    @Test
    public void divideAndConquerAndMerge(){
        int[] a = {42,30,68,98,86,15,57};
        sort(a, 0, a.length-1);
        for (int i : a) {
            System.out.print(i+" ");
        }
    }
    public static void sort(int[] a, int left, int right) {
        if (left >= right)
            return;

        int center = (left + right) >> 1;
        sort(a, left, center);
        sort(a, center + 1, right);
        merge(a, left, center, right);
    }

    public static void merge(int[] data, int left, int center, int right) {
        int[] tmpArr = new int[right+1];
        int mid = center + 1;
        int index = left; // index记录临时数组的索引
        int tmp = left;

        // 从两个数组中取出最小的放入中临时数组
        while (left <= center && mid <= right) {
            tmpArr[index++] = (data[left] <= data[mid]) ? data[left++]: data[mid++];
        }
        // 剩余部分依次放入临时数组
        while (mid <= right) {
            tmpArr[index++] = data[mid++];
        }
        while (left <= center) {
            tmpArr[index++] = data[left++];
        }
        // 将临时数组中的内容复制回原数组
        for (int i = tmp; i <= right; i++) {
            data[i] = tmpArr[i];
        }
    }
    public void devive(int a){
        if (a<1) return;
        a=a-1;
        devive(a);
        System.out.println("11111111111111111"+a);
    }
    @Test
    public void testDidui(){
        devive(10);
    }
}
