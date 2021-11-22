package com.zyg.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyg.entity.Student;
import com.zyg.entity.StudentVo;

import java.util.List;

/**
 * @author 涛哥
 * @date 2021/11/22
 */
public interface StudentService {

    List<Student> findAll();

    Student findById(int sid);

    void add(Student student);

    void update(Student student);

    void delete(int sid);

    IPage<Student> findByPage(int page, int pageSize);

    IPage<Student> search(int page, int pageSize, StudentVo vo);
}
