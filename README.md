# JavaEE Demo

## Deploying with Payara

With the Payara application server directory (and the JAR named as follows), enter

```bash
java -jar payara-micro-5.2021.2.jar --deploy C:\Users\James\dev\demo\target\JavaEEDemo.war --port 8080
```

Change the path of the /target path as required. To build an JAR, with the application server and dependencies built in, enable the <profiles> in pom.xml.

## Posting new entities

Post new todo items with the following JSON body:

```json
{
	"task": "Read full stack EE course notes"
}
```

using the endpoint as a POST request:

```
http://localhost:8080/JavaEEDemo/api/v1/todo/new
```