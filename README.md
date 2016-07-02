#Unsplash-Java-Unofficial
##Description
This is an unofficial wrapper on the [Unsplash API](http://unsplash.com/documentation) for Java. It aims to provide easier access to the API within Java so that you can focus on your application.

##Compiling
Because the wrapper is currently incomplete and therefore in rapid development, it must be compiled in order to be used. In the future this will not be required.

Unsplash-Java-Unofficial depends on the ScribeJava and GSON libraries. You must include these in your project in order to use Unsplash-Java-Unofficial.

If you use Gradle this is rather simple:
```
    dependencies {
      compile 'com.github.scribejava:scribejava-core:2.7.3'
      compile 'com.google.code.gson:gson:2.7'
      //also include Unsplash-Java-Unofficial here (after you compile)
    }
```

To compile it, simply download the contents of this git repo, go to the folder where the contents are stored and execute `./gradlew build`

If all went well, you should now have a jar file you can use in build/libs. If it didn't, feel free to open an issue.

##API Coverage
As mentioned earlier, this wrapper is in rapid development but is not finished. Not all API methods are covered at this time. Here is the breakdown.

API Endpoint | Covered?
-------------|----------
/me | Yes
/me (PUT) | No
/users/:username | Yes
/users/:username/photos | Yes
/users/:username/likes | Yes
/users/:username/collections | No
/photos | Yes
/photos/curated | Yes
/photos/search | Yes
/photos/:id | Yes
/photos/random | Yes
/photos/:id/stats | Yes
/photos/:id (PUT) | No
/photos/:id/like (POST) | Yes
/photos/:id/like (DELETE) | Yes
/categories | Yes
/categories/:id | Yes
/categories/:id/photos | Yes
/collections | Yes
/collections/featured | Yes
/collections/curated | Yes
/collections/:id | Yes
/collections/curated/:id | Yes
/collections/:id/photos | Yes
/collections/curated/:id/photos | Yes
/collections/:id/related | Yes
/collections (POST) | No
/collections/:id (PUT) | No
/collections/:id (DELETE) | No
/collections/:id/add (POST) | No
/collections/:id/remove (DELETE) | No
/stats/total | Yes
