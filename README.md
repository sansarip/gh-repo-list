[![Build Status](https://travis-ci.org/sansarip/gh-repo-list.svg?branch=master)](https://travis-ci.org/sansarip/gh-repo-list)

## About

An example project that uses [my full stack Clojure web app template](https://github.com/sansarip/yet-another-clojure-web-app-template)

### Application FSM

![fsm](https://i.gyazo.com/524b5caf72db1255b4078be15d65eb8c.png)

where

t = template

r = repositories

## Spinning up the DB

### Prerequisites
* [Docker](https://www.docker.com/get-started)
* [Docker Compose](https://docs.docker.com/compose/gettingstarted/)
* Make (`brew install make` or `apt install make`)


1. Create a `.env` file in the root directory of this project.
2. Navigate to the root directory of this project and execute `echo 'MYSQL_ROOT_PASSWORD=<yourpassword>' >> .env`.
3. While still in the root directory, spin up the MySQL DB by running `make start-db`.
4. After a little while the MySQL DB will initialize along with Adminer; navigate to [localhost:8080](localhost:8080) and log in with the password you set in step two.
5. You should now be able to see the dummy data located within two tables in the TestApp database.

## Development Mode

### Prerequisites
* [JDK 8+](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Leiningen](https://leiningen.org/)
* [Clojure](https://clojure.org/guides/getting_started)

```
lein clean
lein fig:dev
```

Our pal [Figwheel Main](https://figwheel.org/) or *Figgy Smalls* as I like to call it will spin your application right up for you with auto-refresh capabilties n' all!

Wait a bit, then browse to [http://localhost:9500](http://localhost:9500).

But wait, there's more! I dunno about you, but I love constructing small, reusuable components that then compose the entirety of the site like lego bricks. What helps with that? [Devcards](https://github.com/bhauman/devcards)!

You can view your devcards at [http://localhost:9500/devcards.html](http://localhost:9500/devcards.html).

Want to test your cljs? Visit [http://localhost:9500/figwheel-extra-main/auto-testing](http://localhost:9500/figwheel-extra-main/auto-testing) and see your test turn from red to green!

## Production Build

```
lein clean
lein with-profile uberjar ring uberjar
```

That should compile the ClojureScript code first, and then create the standalone jar which you can then run with `java -jar target/my-website.jar`

Your website will be awaiting you at [localhost:3000](localhost:3000)

*Good luck, and have fun, you rascal Clojurians!* :beers:
