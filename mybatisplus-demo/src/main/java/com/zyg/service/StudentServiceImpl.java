package com.zyg.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyg.entity.Student;
import com.zyg.entity.StudentVo;
import com.zyg.mapper.ClassMapper;
import com.zyg.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author 涛哥
 * @date 2021/11/22
 */
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassMapper classMapper;

//    1. 查询所有学生
    @Override
    public List<Student> findAll() {
//        1.1 查询得到所有学生
        List<Student> students = studentMapper.selectList(null);
        for (Student student:students){
//            1.2 添加班级名称
            student.setCname(classMapper.selectById(student.getCid()).getCname());
        }
//        1.3 返回学生信息
        return students;

    }

//    2. id查询单个学生
    @Override
    public Student findById(int sid) {
        return studentMapper.selectById(sid);
    }

//    3. 添加学生
    @Override
    public void add(Student student) {
        studentMapper.insert(student);
    }

//    4. 修改学生信息
    @Override
    public void update(Student student) {
        studentMapper.updateById(student);
    }

    @Override
    public void delete(int sid) {
        studentMapper.deleteById(sid);
    }

    @Override
    public IPage<Student> findByPage(int page, int pageSize) {
//        6.1 开始分页
//        6.1.1 封装分页查询参数
        IPage<Student> ipage=new Page<>(page,pageSize);
//        6.1.2 封装查询条件
        Wrapper<Student> wrapper=new QueryWrapper<>();
//        6.1.3 开始分页并返回
        return studentMapper.selectPage(ipage, wrapper);    //参数1：代表分页参数对象，参数2：代表条件查询参数对象

    }


//    7. 条件查询带分页
    @Override
    public IPage<Student> search(int page, int pageSize, StudentVo vo) {
//        7.1 开始分页
//        7.1.1 封装分页参数
        IPage<Student> iPage=new Page<>(page,pageSize);
        //7.1.2 封装查询条件
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        if(vo != null){
            if(!StringUtils.isEmpty(vo.getName())){
                wrapper.like("name",vo.getName());
            }
            if(!StringUtils.isEmpty(vo.getAddr())){
                wrapper.like("addr",vo.getAddr());
            }
            if(vo.getCid() != null){
                wrapper.like("cid",vo.getCid());
            }
        }
        //7.1.3 开始分页并返回
        return studentMapper.selectPage(iPage, wrapper);   //参数1：代表分页参数对象 参数2：代表条件查询参数对象
    }
}
