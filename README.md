# Libreri

The public version of a project to create a library system

## Development

Update your local database connection in `application.properties` or create your own `application-local.properties` file to override
settings for development.

During development it is recommended to use the profile `local`. In IntelliJ `-Dspring.profiles.active=local` can be
added in the VM options of the Run Configuration after enabling this property in "Modify options".

In addition to the Spring Boot application, the DevServer must also be started. [Node.js](https://nodejs.org/) has to be
available on the system - the latest LTS version is recommended. On first usage and after updates the dependencies have to be installed:

```
npm install
```

The DevServer can now be started as follows:

```
npm run devserver
```

Using a proxy the whole application is now accessible under `localhost:8081`. All changes to the templates and JS/CSS
files are immediately visible in the browser.

## Build

The application can be built using the following command:

```
mvnw clean package
```

Node.js is automatically downloaded using the `frontend-maven-plugin` and the final JS/CSS files are integrated into the jar.

Start your application with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/libreri-0.0.1-SNAPSHOT.jar
```

If required, a Docker image can be created with the Spring Boot plugin. Add `SPRING_PROFILES_ACTIVE=production` as
environment variable when running the container.

```
mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=io.bootify/libreri
```

## Further readings

* [Maven docs](https://maven.apache.org/guides/index.html)
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
* [Thymeleaf docs](https://www.thymeleaf.org/documentation.html)
* [Webpack concepts](https://webpack.js.org/concepts/)
* [npm docs](https://docs.npmjs.com/)
* [Bootstrap docs](https://getbootstrap.com/docs/5.3/getting-started/introduction/)
* [Htmx in a nutshell](https://htmx.org/docs/)
* [Learn Spring Boot with Thymeleaf](https://www.wimdeblauwe.com/books/taming-thymeleaf/)

## Screenshots

[![logIn.png](https://i.postimg.cc/0y7FWv7j/logIn.png)](https://postimg.cc/Wdb5zQWc)
[![menu-Empleado.png](https://i.postimg.cc/sDkb4xQb/menu-Empleado.png)](https://postimg.cc/F7xP99xx)
[![menu-Empleado.png](https://i.postimg.cc/sDkb4xQb/menu-Empleado.png)](https://postimg.cc/F7xP99xx)
[![ejemplars.png](https://i.postimg.cc/XNDRXQVj/ejemplars.png)](https://postimg.cc/Thg74jLF)
[![menu-Supervisor.png](https://i.postimg.cc/PrpBDYxm/menu-Supervisor.png)](https://postimg.cc/1fSChgDt)
[![empleados.png](https://i.postimg.cc/V6J3FCgQ/empleados.png)](https://postimg.cc/jwrgxCBZ)

