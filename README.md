# DefinitelyTyped Java Libraries

<!--
[![Build Status](https://travis-ci.org/DefinitelyTyped/DefinitelyTyped.png?branch=master)](https://travis-ci.org/DefinitelyTyped/DefinitelyTyped)

[![Join the chat at https://gitter.im/borisyankov/DefinitelyTyped](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/borisyankov/DefinitelyTyped?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
-->

The repository of Java APIs providing access to *high quality* libraries wrapping any [JavaScript library and its TypeScript definition](https://github.com/DefinitelyTyped/DefinitelyTyped). Contribute to this project by forking and converting more of existing typings in this repository. It is easy. Clone the repository. Select a project, like `underscore` and:

```bash
$ cp jquery/pom.xml underscore/pom.xml
$ cd underscore
$ open pom.xml
# replace references to jquery with underscore
$ mvn install
```

A Java wrapper giving you access to the `underscore` library will be created for you in your local [Maven](http://maven.apache.org) repository. In addition to that write some tests to verify the generated
library works. Then create a pull request.

You may need to do some tweaks (e.g. [comment something out](https://github.com/dukescript/maven-typings-plugin/commit/c5b6a2121f3ad069973a118c96e72ff5268298f9)
or to [remove not typeable definition](https://github.com/dukescript/maven-typings-plugin/commit/19c15abd88dd1b50b087e46f5c9e929be666a6c0))
to the typings definition file to make the conversion to *Java* successful.
Alternatively you can improve the [Maven Typings Plugin](https://github.com/dukescript/maven-typings-plugin)
to handle the conversion in a better way.

## Using Latest Version of the Libraries

The snapshots of the *DefinitelyTyped Java Libraries* are being uploaded to
a [Maven repository](https://oss.sonatype.org/content/repositories/snapshots/) and are ready for your immediate consumption.
The current [list of available libraries](https://oss.sonatype.org/content/repositories/snapshots/com/dukescript/libraries/)
contains the core JavaScript (**net.java.html.lib**), generic access to DOM (**net.java.html.lib.dom**)
and popular JavaScript libraries like [JQuery](https://oss.sonatype.org/content/repositories/snapshots/com/dukescript/libraries/net.java.html.lib.jquery/),
[Angular](https://oss.sonatype.org/content/repositories/snapshots/com/dukescript/libraries/net.java.html.lib.angular/)
and [Knockout](https://oss.sonatype.org/content/repositories/snapshots/com/dukescript/libraries/net.java.html.lib.ko/).
To include these libraries in your application add a repository definition to
your `pom.xml` file:

```xml
<repositories>
    <repository>
        <id>typings-snapshots</id>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
        <releases>
            <enabled>false</enabled>
        </releases>
        <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
    </repository>
</repositories>
```

and then you can depend on any of these libraries in a regular Maven way:

```xml
<dependency>
    <groupId>${project.groupId}</groupId>
    <artifactId>net.java.html.lib</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
<dependency>
    <groupId>${project.groupId}</groupId>
    <artifactId>net.java.html.lib.jquery</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## Contributions

DefinitelyTyped Java Libraries project can only move forward
thanks to contributions of users like you!
There is so many typings definitions that are waiting for conversion to Java!

## License

This project is licensed under the MIT license.
Copyrights on the definition files are respective of each contributor listed
at the beginning of each definition file.
The copyrights of individual Java wrappers can be found in history.
This work is sponsored by [DukeScript project](http://dukescript.com).
