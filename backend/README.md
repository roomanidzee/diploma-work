# studeeper-backend

### WARNING
If you need to launch only backend part, you should add ```depends_on``` for services ```system_service``` and ```file_service```:

```
depends_on:
  - mongo
  - redis
```  

### Launch
```
docker-compose up -d
```

### Tests launch

#### Before all
```
docker-compose -f docker-compose.test.yml up -d
```

#### For system_service

```
cd system_service
./gradlew --no-daemon clean test
```

#### After all
```
docker-compose -f docker-compose.test.yml down -v
```
