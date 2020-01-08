import {Service} from "typedi";
import {Request} from "express";
import redis_client from '../config/redis';
import logger from '../config/logger';

@Service()
export class RedisService {

    validateRequest(req: Request): String{

        let jwtToken: string = req.header("Authorization")
        let finalKey: string;

        if(jwtToken.length != 0){
            finalKey = jwtToken.substring(7, jwtToken.length);
        }else{
            throw new Error("No JWT key in header");
        }

        let redisValue: string;

        redis_client.get(finalKey, function(error, result){

            if(error){
                logger.error(`Redis error: ${error}`)
                throw error;
            }

            if(result == null){
                let errMsg: string = `Redis: null value for key ${finalKey}`;
                logger.error(errMsg);
                throw new Error(errMsg);
            }

            redisValue = result;

        });

        return redisValue;

    }

}

