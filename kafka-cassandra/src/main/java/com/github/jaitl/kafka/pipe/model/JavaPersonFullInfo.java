package com.github.jaitl.kafka.pipe.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

import java.util.List;

@UDT(keyspace = "kafka", name = "full_info")
public class JavaPersonFullInfo {
    private Integer age;
    @Field(name = "lastName", caseSensitive = true)
    private String lastName;
    private List<String> jobs;

    public JavaPersonFullInfo() {
    }

    public JavaPersonFullInfo(Integer age, String lastName, List<String> jobs) {
        this.age = age;
        this.lastName = lastName;
        this.jobs = jobs;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }

    @Override
    public String toString() {
        return "JavaPersonFullInfo{" +
                "age=" + age +
                ", lastName='" + lastName + '\'' +
                ", jobs=" + jobs +
                '}';
    }
}
