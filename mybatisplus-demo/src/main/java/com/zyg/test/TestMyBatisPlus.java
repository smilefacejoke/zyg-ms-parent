package com.zyg.test;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyg.entity.Student;
import com.zyg.entity.StudentVo;
import com.zyg.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 涛哥
 * @date 2021/11/22
 */

@SpringBootTest
public class TestMyBatisPlus {
    @Autowired
    private StudentService studentService;


//    1. 查询所有人
    @Test
    public void test1(){
        List<Student> students = studentService.findAll();
        for (Student student:students){
            System.out.println("student = " + student);
        }
    }

//    2. 查询单个学生
    @Test
    public void test2(){
//        2.1 定义要查询的学生id
        int sid=20;
//        2.2 根据学生id查询学生数据
        Student student=studentService.findById(sid);
        System.out.println("student = " + student);

    }

//    3. 添加学生
    @Test
    public void test3(){
        Student student=new Student("赵灵儿","女",19,"云南",4);
        studentService.add(student);
    }

//    4. 修改学生
    @Test
    public void test4(){
        //4.1 定义要修改的学生学号
        int sid=20;
        //4.2 找到该学生信息
        Student student=studentService.findById(sid);
        //4.3 判断其是否为空
        if(student!=null){
            student.setAddr("香港");
            student.setAge(21);
        }
        studentService.update(student);
    }

//    5. 删除学生
    @Test
    public void test5(){
//        5.1 定义要删除的学生id
        int sid=1;
//        5.2 开始删除
        studentService.delete(sid);
    }

//    6. 分页查询
    @Test
    public void test06(){
//        6.1 定义分页参数
        int page=1;  //当前页面
        int pageSize=5; //每页大小
//        6.2 开始分页
        IPage<Student> studentIPage =studentService.findByPage(page,pageSize);
//        6.3 查看分页信息
        long pages=studentIPage .getPages();
        long total=studentIPage .getTotal();
        List<Student> records=studentIPage .getRecords();
        System.out.println("pages = " + pages);
        System.out.println("records = " + records);
        System.out.println("total = " + total);
    }

//    7. 分页带条件查询
    @Test
    public void test7(){
//        7.1 定义分页参数
        int page=1;  //当前页面
        int pageSize=5; //每页大小
//        7.2 封装查询条件
        StudentVo vo=new StudentVo();
        vo.setName("张");
        vo.setAddr("深");

        //7.2 开始分页
        IPage<Student> studentIPage = studentService.search(page,pageSize,vo);
        List<Student> records = studentIPage.getRecords();
        System.out.println("pages = " + studentIPage.getPages());
        System.out.println("total = " + studentIPage.getTotal());
        System.out.println("records = " + records);
    }


}
