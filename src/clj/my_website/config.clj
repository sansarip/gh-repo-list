(ns my-website.config)

(def db-spec {:dbtype   "mysql"
              :dbname   (or (System/getenv "MYSQL_DATABASE_DBNAME") "TestApp")
              :host     (or (System/getenv "MYSQL_DATABASE_HOST") "localhost")
              :port     (or (System/getenv "MYSQL_DATABASE_PORT") "3306")
              :username (or (System/getenv "MYSQL_DATABASE_USER") "root")
              :password (or (System/getenv "MYSQL_DATABASE_PASSWORD") "password")
              :maximumPoolSize (or (System/getenv "MYSQL_DATABASE_POOL_SIZE") 20)})
