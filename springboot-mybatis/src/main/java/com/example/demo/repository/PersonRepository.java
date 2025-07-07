package com.example.demo.repository;

import com.example.demo.bean.Person;
import com.example.demo.mapper.PersonMapper;
import com.github.pagehelper.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by guiqi on 2017/11/9.
 */

@Repository
public class PersonRepository {

    @Autowired
    private PersonMapper personMapper;


    public Person findByUsername(String username){
        return personMapper.findByUserName(username);
    }



    public List<Person> findAll() {

        return personMapper.findAll();
    }

    public void save(Person person) {
        personMapper.save(person);
    }

    public List<Person> queryByPageType1(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Person> personList = personMapper.findAll();
        PageInfo page = new PageInfo(personList);
        return page.getList();
    }

    public List<Person> queryByPageType2(int pageNum, int pageSize) {
        return personMapper.findAllByPageNumSize(pageNum,pageSize);
    }

    public List<Person> queryByPageType3(int pageNum, int pageSize) {
        Page<Person> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(new ISelect() {
            @Override
            public void doSelect() {
                personMapper.findAll();
            }
        });

        return page.getResult();
    }

    public List<Person> queryByPageType4(int pageNum, int pageSize) {
        //jdk8 lambda方式用法
        Page<Person> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(()->personMapper.findAll());
        return page.getResult();
    }

    public List<Person> queryByPageType5(PageRowBounds rowBounds) {
        return personMapper.findAllByRowBounds(rowBounds);
    }
}
