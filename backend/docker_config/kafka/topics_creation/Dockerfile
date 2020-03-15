FROM confluentinc/cp-kafka:5.3.3

LABEL maintainer = 'Andrey Romanov'

ARG NEW_KAFKA_TOPICS

ENV KAFKA_BROKER_ID=ignored \
    KAFKA_ZOOKEEPER_CONNECT=ignored \
    KAFKA_ADVERTISED_LISTENERS=ignored \
    NEW_KAFKA_TOPICS=${NEW_KAFKA_TOPICS}

ADD run.sh /run.sh

CMD ["/run.sh"] 