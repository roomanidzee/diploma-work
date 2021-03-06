import {Logger} from "ts-log-debug";

const logger = new Logger("app");
logger.appenders
      .set("std-log", {
          type: "stdout",
          levels: ["debug", "info", "trace", "error"]
      })
      .set("error-log", {
          type: "stderr",
          levels: ["fatal", "error", "warn"],
          layout: {
              type: "pattern",
              pattern: "%d %p %c %X{user} %m%n"
          }
      });

export default logger;