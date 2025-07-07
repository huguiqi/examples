package com.example.demo;

import com.example.demo.bean.Person;
import com.example.demo.repository.CarDataRepository;
import com.example.demo.repository.PersonRepository;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class Demo1ApplicationTests {

	@Autowired
	private CarDataRepository carDataRepository;


	@Test
	@Ignore
	public void testAdd() {
		//应用插入的数据被回滚
		carDataRepository.addCarAndHouse(true);

	}

	@Test
	@Ignore
	public void testQuery(){

		System.out.println("=======查询所有的");
		Object carObj = carDataRepository.queryFor().get("car");
		Object houseObj = carDataRepository.queryFor().get("house");

		List carList =  Arrays.asList(carObj);
		List houseList =  Arrays.asList(houseObj);
		carList.forEach(System.out::println);
		houseList.forEach(System.out::println);
	}

}
