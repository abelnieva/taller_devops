# java-maven-docker-example

This project is an example of how to integrate Maven based Java application with Docker, using [Spotify's docker-maven-plugin](https://github.com/spotify/docker-maven-plugin).

## cli

Build an image by running this command.

```
$ cd java-maven-docker-example/cli
$ mvn clean package
```

Run image on container.

```
$ docker run cli
Hello world!
```

You can also specify command line argument.

```
$ docker run cli there
Hello there!
```

## http-server

Building an image is done by same command above.

```
$ cd java-maven-docker-example/http-server
$ mvn clean package
```

The difference between cli and http server is actually http server uses a custom `Dockerfile` stored under `java-maven-docker-example/http-server/docker` directory while cli sets base image and entry point directly in the pom. For http server, we need to expose the listen port to Docker by giving an EXPOSE instruction which is not yet supported in docker-maven-plugin.

To run the server on the container, run this command.

```
$ docker run -d -P http-server
```

Now you see the list of containers by the following command.

```
$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED              STATUS              PORTS                     NAMES
17178a5067a2        http-server         "java -cp /http-serve"   About a minute ago   Up About a minute   0.0.0.0:32769->9999/tcp   compassionate_heisenberg
```

Make sure http server is listening on `localhost:32769` (Docker binds port automatically in this case).

```
$ curl localhost:32769
Hello world!
```
