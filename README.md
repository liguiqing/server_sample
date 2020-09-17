# Guozhi interview for java developer

### you can try the default accounts：admin/admin after you start the project

## Requires

**Required**

1. Run the project Client side and Server side by your IDE such as Eclipse,IntelliJ IDEA,VS Code .etc or commandline. 

   You just talk to us How can display [Sample](src/main/java/com/gz/sample/domain/Sample.java) to web page from database when remote interview only.
   
**Optional**

**you can try do the next tasks. if you can fininsh by you self and tell us how, congratulations，welcome to join us! come on!**

2.Create Entity Objects mapped to tables with annotations

tables：
[sample_clazz,sample_student,sample_course,sample_study]
the Entity Object must extends
[com.gz.sample.infrastructure.domain.LongIdEntityObject](src/main/java/com/gz/sample/infrastructure/domain/LongIdEntityObject.java)
or
[com.gz.sample.infrastructure.domain.LongIdValueObject](src/main/java/com/gz/sample/infrastructure/domain/LongIdValueObject.java)

3.Create JPA Repositories to persistent Entity Objects

4.Create Services interface And implements to use repositories and entities

5.Create Resources to provide rest APIS

6.Create Unit tests Repositories and Resources which your code

7.Show all student study data with ElementUI components such as [Table],[Card] .etc just you like.

for more information of [ElementUI]() ,refer to the [ElementUI](https://element.eleme.io/#/zh-CN/component/installation) page

you have three days only,any question,contact to <liguiqing@guozhihrm.com>
## Development

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

    npm install

We use npm scripts and [Webpack][] as our build system.

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

  
    npm run start

Npm is also used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in [package.json](package.json). You can also run `npm update` and `npm install` to manage dependencies.
Add the `help` flag on any command to see how you can use it. For example, `npm help update`.

The `npm run` command will list all of the scripts available to run for this project.

### Service workers

Service workers are commented by default, to enable them please uncomment the following code.

- The service worker registering script in index.html

```html
<script>
  if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('./service-worker.js').then(function () {
      console.log('Service Worker Registered');
    });
  }
</script>
```

Note: workbox creates the respective service worker and dynamically generate the `service-worker.js`

### Managing dependencies

For example, to add [Leaflet][] library as a runtime dependency of your application, you would run following command:

    npm install --save --save-exact leaflet

To benefit from TypeScript type definitions from [DefinitelyTyped][] repository in development, you would run following command:

    npm install --save-dev --save-exact @types/leaflet

Then you would import the JS and CSS files specified in library's installation instructions so that [Webpack][] knows about them:
Edit [src/main/webapp/app/main.ts](src/main/webapp/app/main.ts) file:

```
import 'leaflet/dist/leaflet.js';
```

Edit [src/main/webapp/content/scss/vendor.scss](src/main/webapp/content/scss/element-rewrite.scss) file:

```
@import '~leaflet/dist/leaflet.scss';
```

Note: there are still few other things remaining to do for Leaflet that we won't detail here.

### Using vue-cli

You can also use [Vue CLI][] to display the project using vue UI.

For example, the following command:

    vue ui

will generate open Vue Project Manager. From there, you'll be able to manage your project as any other Vue.js projects.

## Building for production

### Packaging as jar

To build the final jar and optimize the sample application for production, run:

    mvn -Pprod clean verify

This will concatenate and minify the client CSS and JavaScript files. It will also modify `index.html` so it references these new files.
To ensure everything worked, run:

    java -jar target/*.jar

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

    mvn -Pprod,war clean verify

## Testing

To launch your application's tests, run:
mvn test

### Client tests

Unit tests are run by [Jest][] and written with [Jasmine][]. They're located in [src/test/javascript/](src/test/javascript/) and can be run with:
npm test

For more information, refer to the [Running tests page][].

