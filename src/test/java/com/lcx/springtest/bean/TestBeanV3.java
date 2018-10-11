package com.lcx.springtest.bean;
/**
 * @Created by:  IntelliJ Idea 2017.1
 * @Author: lcx
 * @Date: 2018/10/11
 * @Time: 上午11:09
 * @Title: TestBeanV3
 * @ProjectName: lcxspring
 * @Description: TODO
 */

/**
 *
 * <p>  </p>
 * @author lichenxiang
 * @version TestBeanV3: TestBeanV3.java, v 0.1 2018年10月11日 上午11:09:09 lichenxiang Exp $
 *
 */
public class TestBeanV3 {

    private User user;

    private Integer age;

    public TestBeanV3(User user, Integer age) {
        this.user = user;
        this.age = age;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}