# kafka-cassandra-pipline
kafka cassandra pipline

# kafka commands
bash:

 ```docker-compose exec kafka bash```

create topic:

```/opt/kafka/bin/kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic <topic name>```

topic list:

```/opt/kafka/bin/kafka-topics.sh --list --zookeeper zookeeper:2181```
