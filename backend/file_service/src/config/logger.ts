import { Category, CategoryServiceFactory, CategoryConfiguration, LogLevel } from "typescript-logging";

CategoryServiceFactory.setDefaultConfiguration(new CategoryConfiguration(LogLevel.Info));

const logger = new Category("app");

export default logger;