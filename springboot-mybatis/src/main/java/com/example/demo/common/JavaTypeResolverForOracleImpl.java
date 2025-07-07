package com.example.demo.common;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by guiqi on 2018/1/18.
 */
public class JavaTypeResolverForOracleImpl extends JavaTypeResolverDefaultImpl{

    public JavaTypeResolverForOracleImpl() {
            this.typeMap.put(Integer.valueOf(2003), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("ARRAY", new FullyQualifiedJavaType(Object.class.getName())));
            this.typeMap.put(Integer.valueOf(-5), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("BIGINT", new FullyQualifiedJavaType(Long.class.getName())));
            this.typeMap.put(Integer.valueOf(-2), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("BINARY", new FullyQualifiedJavaType("byte[]")));
            this.typeMap.put(Integer.valueOf(-7), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("BIT", new FullyQualifiedJavaType(Boolean.class.getName())));
            this.typeMap.put(Integer.valueOf(2004), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("BLOB", new FullyQualifiedJavaType("byte[]")));
            this.typeMap.put(Integer.valueOf(16), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("BOOLEAN", new FullyQualifiedJavaType(Boolean.class.getName())));
            this.typeMap.put(Integer.valueOf(1), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("CHAR", new FullyQualifiedJavaType(String.class.getName())));
            this.typeMap.put(Integer.valueOf(2005), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("CLOB", new FullyQualifiedJavaType(String.class.getName())));
            this.typeMap.put(Integer.valueOf(70), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("DATALINK", new FullyQualifiedJavaType(Object.class.getName())));
            this.typeMap.put(Integer.valueOf(91), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("DATE", new FullyQualifiedJavaType(Date.class.getName())));
            this.typeMap.put(Integer.valueOf(3), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("INTEGER", new FullyQualifiedJavaType(BigDecimal.class.getName())));
            this.typeMap.put(Integer.valueOf(2001), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("DISTINCT", new FullyQualifiedJavaType(Object.class.getName())));
            this.typeMap.put(Integer.valueOf(8), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("DOUBLE", new FullyQualifiedJavaType(Double.class.getName())));
            this.typeMap.put(Integer.valueOf(6), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("FLOAT", new FullyQualifiedJavaType(Double.class.getName())));
            this.typeMap.put(Integer.valueOf(4), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("INTEGER", new FullyQualifiedJavaType(Integer.class.getName())));
            this.typeMap.put(Integer.valueOf(2000), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("JAVA_OBJECT", new FullyQualifiedJavaType(Object.class.getName())));
            this.typeMap.put(Integer.valueOf(-16), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("LONGNVARCHAR", new FullyQualifiedJavaType(String.class.getName())));
            this.typeMap.put(Integer.valueOf(-4), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("LONGVARBINARY", new FullyQualifiedJavaType("byte[]")));
            this.typeMap.put(Integer.valueOf(-1), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("LONGVARCHAR", new FullyQualifiedJavaType(String.class.getName())));
            this.typeMap.put(Integer.valueOf(-15), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("NCHAR", new FullyQualifiedJavaType(String.class.getName())));
            this.typeMap.put(Integer.valueOf(2011), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("NCLOB", new FullyQualifiedJavaType(String.class.getName())));
            this.typeMap.put(Integer.valueOf(-9), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("NVARCHAR", new FullyQualifiedJavaType(String.class.getName())));
            this.typeMap.put(Integer.valueOf(0), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("NULL", new FullyQualifiedJavaType(Object.class.getName())));
            this.typeMap.put(Integer.valueOf(2), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("NUMERIC", new FullyQualifiedJavaType(BigDecimal.class.getName())));
            this.typeMap.put(Integer.valueOf(1111), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("OTHER", new FullyQualifiedJavaType(Object.class.getName())));
            this.typeMap.put(Integer.valueOf(7), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("REAL", new FullyQualifiedJavaType(Float.class.getName())));
            this.typeMap.put(Integer.valueOf(2006), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("REF", new FullyQualifiedJavaType(Object.class.getName())));
            this.typeMap.put(Integer.valueOf(5), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("SMALLINT", new FullyQualifiedJavaType(Short.class.getName())));
            this.typeMap.put(Integer.valueOf(2002), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("STRUCT", new FullyQualifiedJavaType(Object.class.getName())));
            this.typeMap.put(Integer.valueOf(92), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TIME", new FullyQualifiedJavaType(Date.class.getName())));
            this.typeMap.put(Integer.valueOf(93), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TIMESTAMP", new FullyQualifiedJavaType(Date.class.getName())));
            this.typeMap.put(Integer.valueOf(-6), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Byte.class.getName())));
            this.typeMap.put(Integer.valueOf(-3), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("VARBINARY", new FullyQualifiedJavaType("byte[]")));
            this.typeMap.put(Integer.valueOf(12), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("VARCHAR", new FullyQualifiedJavaType(String.class.getName())));
    }


    //如果不想用Short作为整型，就重新这个方法
        @Override
        protected FullyQualifiedJavaType calculateBigDecimalReplacement(IntrospectedColumn column, FullyQualifiedJavaType defaultType) {
                FullyQualifiedJavaType answer;
                if(column.getScale() <= 0 && column.getLength() <= 18 && !this.forceBigDecimals) {
                        if(column.getLength() > 9) {
                                answer = new FullyQualifiedJavaType(Long.class.getName());
                        } else if(column.getLength() > 4) {
                                answer = new FullyQualifiedJavaType(Integer.class.getName());
                        } else {
                                answer = new FullyQualifiedJavaType(Integer.class.getName());
                        }
                } else {
                        answer = defaultType;
                }

                return answer;
        }


        @Override
        public String calculateJdbcTypeName(IntrospectedColumn introspectedColumn) {

                String answer = null;
                JavaTypeResolverDefaultImpl.JdbcTypeInformation jdbcTypeInformation = (JavaTypeResolverDefaultImpl.JdbcTypeInformation)this.typeMap.get(Integer.valueOf(introspectedColumn.getJdbcType()));
                if(jdbcTypeInformation != null) {
                        if (introspectedColumn.getScale()>0)
                                answer = "DECIMAL";
                         else
                                answer = jdbcTypeInformation.getJdbcTypeName();
                }
                return answer;
        }
}
