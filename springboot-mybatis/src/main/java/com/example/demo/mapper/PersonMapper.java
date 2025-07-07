package com.example.demo.mapper;

import com.example.demo.bean.Person;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by sam on 2017/11/12.
 */

@Mapper
public interface PersonMapper {

    @Select("SELECT * FROM PERSON WHERE USERNAME = #{userName}")
    Person findByUserName(@Param("userName") String username);

    @Select("SELECT * FROM PERSON")
    List<Person> findAll();


    @Insert("INSERT INTO PERSON(ID,USERNAME,FIRSTNAME,LASTNAME,BIRTHDATE) values(#{id},#{username},#{firstName},#{lastName},#{birthDate})")
    @SelectKey(statement = "SELECT SEQ_PERSON.Nextval as ID from DUAL",resultType = Long.class,before = true,keyProperty = "id",keyColumn = "ID")
    void save(Person person);

    @Select("SELECT * FROM PERSON")
    List<Person> findAllByPageNumSize(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    @Select("SELECT * FROM PERSON")
    List<Person> findAllByRowBounds(PageRowBounds rowBounds);
}
