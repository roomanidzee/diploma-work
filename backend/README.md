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
```
docker-compose run --rm system_service launch-tests
```
