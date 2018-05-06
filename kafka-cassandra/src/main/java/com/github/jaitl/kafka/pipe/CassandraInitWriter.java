package com.github.jaitl.kafka.pipe;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.github.jaitl.kafka.pipe.model.JavaPerson;
import com.github.jaitl.kafka.pipe.model.JavaPersonFullInfo;
import com.google.common.collect.Lists;

import java.util.UUID;

public class CassandraInitWriter {
    private Session session;
    private Mapper<JavaPerson> mapper;

    public CassandraInitWriter() {
        Cluster.Builder builder = Cluster.builder().addContactPoint("localhost");

        Cluster cluster = builder.build();
        session = cluster.connect();
        MappingManager managerManager = new MappingManager(session);
        mapper = managerManager.mapper(JavaPerson.class);
    }

    public void createInitData() {
        JavaPerson person1 = new JavaPerson(
                UUID.randomUUID().toString(),
                "Петя",
                new JavaPersonFullInfo(22, "Иванов", Lists.newArrayList("где то", "кто то"))
        );

        JavaPerson person2 = new JavaPerson(
                UUID.randomUUID().toString(),
                "Миша",
                new JavaPersonFullInfo(33, "Сидоров", Lists.newArrayList("ООО мимин"))
        );

        JavaPerson person3 = new JavaPerson(
                UUID.randomUUID().toString(),
                "Федя",
                new JavaPersonFullInfo(44, "Иванов", Lists.newArrayList("нету"))
        );

        mapper.save(person1);
        mapper.save(person2);
        mapper.save(person3);
    }

    public static void main(String[] args) {
        CassandraInitWriter writer = new CassandraInitWriter();
        writer.createInitData();
    }
}
