# studeeper-backend

### Project launch
```
./gradlew clean build -x test
docker-compose -f docker/docker-compose.yml up -d
```

or

```
./gradlew clean build -x test
docker-compose -f docker/docker-compose.yml up -d mongo neo4j
java -jar -Xmx200m build/libs/app.jar
```

### Tests launch
```
./gradlew test
```
