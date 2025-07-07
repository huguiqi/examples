package com.example.demo;

import com.example.demo.bean.Person;
import com.example.demo.repository.CarDataRepository;
import com.example.demo.repository.PersonRepository;
import com.github.pagehelper.PageRowBounds;
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
public class PageQueryPersonTests {


	@Autowired
	private PersonRepository personRepository;

	@Test
	public void testPageQuery1(){
		List<Person> personList = personRepository.queryByPageType1(1,10);
		Assert.assertTrue("应该是10条数据",personList.size() == 10);
	}


	@Test
	public void testPageQuery2(){
		List<Person> personList = personRepository.queryByPageType2(1,10);
		Assert.assertTrue("应该是10条数据",personList.size() == 10);
	}

	@Test
	public void testPageQuery3(){
		List<Person> personList = personRepository.queryByPageType3(1,10);
		Assert.assertTrue("应该是10条数据",personList.size() == 10);
	}

	@Test
	public void testPageQuery4(){
		List<Person> personList = personRepository.queryByPageType4(1,10);
		Assert.assertTrue("应该是10条数据",personList.size() == 10);
	}

	@Test
	public void testPageQuery5(){
		List<Person> personList = personRepository.queryByPageType5(new PageRowBounds(1,10));
		Assert.assertTrue("应该是10条数据",personList.size() == 10);
	}
}
