# spring-boot-demo
spring boot + web +  mybatis + mysql


## 测试批量插入效率

### 三种批量插入数据库性能对比

1. for 循环插入

2千条数据，用时78.003 秒

2. 通过sql拼接插入

20万条数据, 用时8.8235 秒

3. 通过mybatis批处理方式

20万条数据，用时7.8781 秒

数据量越大，表字段越多，mybatis的批处理方式性能越佳
