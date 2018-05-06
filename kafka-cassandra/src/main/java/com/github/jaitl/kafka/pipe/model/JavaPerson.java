package com.github.jaitl.kafka.pipe.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Frozen;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = "kafka", name = "person")
public class JavaPerson {
    @PartitionKey
    @Column(name="ID", caseSensitive = true)
    private String ID;
    @Column(name="firstName", caseSensitive = true)
    private String firstName;
    @Frozen
    @Column(name="fullInfo", caseSensitive = true)
    private JavaPersonFullInfo fullInfo;

    public JavaPerson() {
    }

    public JavaPerson(String ID, String firstName, JavaPersonFullInfo fullInfo) {
        this.ID = ID;
        this.firstName = firstName;
        this.fullInfo = fullInfo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public JavaPersonFullInfo getFullInfo() {
        return fullInfo;
    }

    public void setFullInfo(JavaPersonFullInfo fullInfo) {
        this.fullInfo = fullInfo;
    }


    @Override
    public String toString() {
        return "JavaPerson{" +
                "ID='" + ID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", fullInfo=" + fullInfo +
                '}';
    }
}
