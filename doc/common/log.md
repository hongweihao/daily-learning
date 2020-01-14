commons-logging能够选择使用Log4j还是JDK Logging，但是他不依赖Log4j/JDK Logging的API。classpath中包含了log4j，优先log4j，否则使用JDK Logging




slf4j与commons-logging应该是相同类型的东西
logback/log4j/log4j2/JDK logging应该都是日志框架，同一种类型的