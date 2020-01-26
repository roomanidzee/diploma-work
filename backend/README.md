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
docker-compose run --rm system_service launch-tests
```

#### For files_service

```
docker-compose run --rm files_service launch-tests
```

#### After all
```
docker-compose -f docker-compose.test.yml down -v
```
