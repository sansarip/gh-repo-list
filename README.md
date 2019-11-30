[![Build Status](https://travis-ci.org/sansarip/gh-repo-list.svg?branch=master)](https://travis-ci.org/sansarip/gh-repo-list)
# GH Repo List

An example project mocking a GitHub repo list that uses [my full stack Clojure web app template](https://github.com/sansarip/yet-another-clojure-web-app-template). You should read this document top down in the order presented. When you get to _Development Mode_, then you may choose to instead skip that section and jump to _Production Build_.

<details>
  <summary><b>Preview</b></summary>
  <img src=https://i.gyazo.com/400ded5884c15d1516583d5696aaa354.png></img>
</details>

<details>
  <summary><b>Application FSM</b></summary>
  <img src=https://i.gyazo.com/e72bf813e54c265f5ab68c814666f95a.jpg></img>
  <p>where</p>
  <p>t = template</p>
  <p>r = repositories</p>
</details>

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

## Before you run

The application will read the database connection info from your system environment variables. The defaults should be ok, other than the password. 

Run `export MYSQL_DATABASE_PASSWORD=<yourpassword>` before moving on to run the application.

Here's a full list of the database connection environment variables if you need to change them for some reason.

| NAME                     | DEFAULT VALUE | REQUIRED |
|--------------------------|---------------|----------|
| MYSQL_DATABASE_DBNAME    | TestApp       | No       |
| MYSQL_DATABASE_HOST      | localhost     | No       |
| MYSQL_DATABASE_PORT      | 3306          | No       |
| MYSQL_DATABASE_USER      | root          | No       |
| MYSQL_DATABASE_PASSWORD  | password      | Yes      |
| MYSQL_DATABASE_POOL_SIZE | 20            | No       |

## Development Mode

### Prerequisites
* DB has been spun up successfully (see above)
* [JDK 8+](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Leiningen](https://leiningen.org/)
* [Clojure](https://clojure.org/guides/getting_started)
* [yarn](https://yarnpkg.com/lang/en/docs/install/#mac-stable)
* [webpack](https://yarnpkg.com/lang/en/docs/install/#mac-stable)

Run this once
```bash
yarn webpack
```

_Yarn and webpack are only necessary because I added literal confetti :tada:_

Then run this
```bash
lein clean
lein fig:dev
```

Our pal [Figwheel Main](https://figwheel.org/) or *Figgy Smalls* as I like to call it will spin your application right up for you with auto-refresh capabilties n' all!

Wait a bit, then browse to [http://localhost:9500](http://localhost:9500).

But wait, there's more! I dunno about you, but I love constructing small, reusuable components that then compose the entirety of the site like lego bricks. What helps with that? [Devcards](https://github.com/bhauman/devcards)!

You can view your devcards at [http://localhost:9500/devcards.html](http://localhost:9500/devcards.html).

Want to test your cljs? Visit [http://localhost:9500/figwheel-extra-main/auto-testing](http://localhost:9500/figwheel-extra-main/auto-testing) and see your test turn from red to green!

## Production Build

### Prerequisites

* Same prereqs as dev 
* Make sure you've run `yarn webpack` at least once prior

```
lein clean
lein with-profile uberjar ring uberjar
```

That should compile the ClojureScript code first, and then create the standalone jar which you can then run with `java -jar target/my-website.jar`

Your website will be awaiting you at [localhost:3000](localhost:3000)

_You can change the port by specifying the `JETTY_PORT=<yourport>` env variable_
 
*Good luck, and have fun, you rascal Clojurians!* :beers:
