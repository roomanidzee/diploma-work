#!/bin/bash

set -m

echo Waiting for Kafka to be ready... && \
/etc/confluent/docker/configure && \
cub kafka-ready -b std_kafka:9092 1 60 --config /etc/kafka/kafka.properties && \
sleep 5;

topics_for_creation=($(echo $NEW_KAFKA_TOPICS | tr "," "\n"))

for i in "${topics_for_creation[@]}"
do
    kafka-topics --zookeeper std_zk:2181 --topic $i --create --replication-factor 1 --partitions 1
done
