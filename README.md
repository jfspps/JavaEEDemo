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

## Differences with the Spring framework

The Java EE API is specified by the package `javax`. There is a significant amount of overlap between Spring and 'modern' Java EE (Jakarta EE). 

All Java EE beans (classes which can be instantiated by the context) are tracked according to [beans.xml](https://github.com/jfspps/JavaEEDemo/blob/main/src/main/webapp/WEB-INF/beans.xml). Look for `bean-discovery-mode="all"` in the configuration. This approach differs somewhat to Spring where the Main java class path annotated with `@SpringBootApplication` (all files and subdirectories of the Main class; there can be more than one) is scanned, looking for annotated classes.

#### Model and JPA

For [models](https://github.com/jfspps/JavaEEDemo/tree/main/src/main/java/entity), the JPA is applied similarly, with the `javax.persistence` and `javax.validation` packages. JPA [services](https://github.com/jfspps/JavaEEDemo/tree/main/src/main/java/service) are handled with `EntityManager` through the `@PersistenceContext` annotation.

The persistence of objects is implemented by [persistence.xml](https://github.com/jfspps/JavaEEDemo/blob/main/src/main/resources/META-INF/persistence.xml).

#### Controllers and servlets

Spring REST controllers are annotated with `@RestController`, and thereby defines the class as a bean for injection. The dependencies are usually other classes as services, annotated in Spring with `@Service` that are injected in the Controller's constructor. 

With JavaEE, the root path is defined with `@ApplicationPath("api/v1")` (here as a REST web service with a route `api/v1`) in a [class](https://github.com/jfspps/JavaEEDemo/blob/main/src/main/java/rest/TodoConfig.java) which extends `Application`. All URL routes are set by the annotation `@Path` and the http request method `@GET`, for example. Contrast to Spring's `@GetMapping` which set both the HTTP request and path. For a REST web service, the servlet (controller) is directed to produce and consume JSON with the `@Consumes(MediaType.APPLICATION_JSON)` and `@Produces(MediaType.APPLICATION_JSON)` annotations. In Spring, this can be set by adding the Jackson library to the Maven pom.xml; this enables both XML (the default) and JSON feeds. 

#### Templates and JSPs

Although not part of this project, HTML with embedded Java-specific tags, known as Java Server Pages or JSPs, are analogous to the Thymeleaf templates used in Spring MVC. JSPs can also be used in Spring projects, incidentally. Another framework, known as Java Server Faces or JSFs, provides another preferential way of rendering web pages in some, though not all, cases.
