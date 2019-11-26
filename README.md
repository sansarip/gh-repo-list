[![Build Status](https://travis-ci.org/sansarip/yet-another-clojure-web-app-template.svg?branch=master)](https://travis-ci.org/sansarip/yet-another-clojure-web-app-template)

## Development Mode

### Front-end:

```
lein clean
lein fig:build
```

Our pal [Figwheel Main](https://figwheel.org/) or *Figgy Smalls* as I like to call it will spin your application right up for you with auto-refresh capabilties n' all!

Wait a bit, then browse to [http://localhost:9500](http://localhost:9500).

But wait, there's more! I dunno about you, but I love constructing small, reusuable components that then compose the entirety of the site like lego bricks. What helps with that? [Devcards](https://github.com/bhauman/devcards)!

You can view your devcards at [http://localhost:9500/devcards.html](http://localhost:9500/devcards.html).

Want to test your cljs? Visit [http://localhost:9500/figwheel-extra-main/auto-testing](http://localhost:9500/figwheel-extra-main/auto-testing) and see your test turn from red to green!

### Back-end:

```
lein ring server
```

Ring, ring, ring! Your server is expecting you at [http://localhost:3000](http://localhost:3000)!

## Production Build

```
lein clean
lein with-profile uberjar ring uberjar
```

That should compile the ClojureScript code first, and then create the standalone jar which you can then run with `java -jar target/my-website.jar`

*Good luck, and have fun, you rascal Clojurians!* :beers:
