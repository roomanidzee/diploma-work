# studeeper-backend

### Launch for backend part of project
```
./gradlew clean build -x test
docker-compose -f docker/docker-compose.yml up -d
```

### Launch for tests of backend

1. 
```
docker-compose -f docker/docker-compose.yml up -d mongo neo4j
```

2.
```
./gradlew test
```

3.
```
docker-compose -f docker/docker-compose.yml down -v
```
