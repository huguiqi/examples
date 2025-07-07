package com.example.demo.batch;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @version v1
 * @Author: sam.hu (huguiqi@zaxh.cn)
 * @Copyright (c) 2023, zaxh Group All Rights Reserved.
 * @since: 2023/05/12/16:32
 * @summary:
 */
public class TestListEmpty  extends BaseTest{


    @Data
    class  Student{

        private String name;
        private Integer id;
    }

    public static void testList(){

        List<Student> list = new ArrayList<>();
        list.stream().forEach(str-> System.out.println(str.getName()));
    }


    public static void main(String[] args) {
        testList();
    }
}
