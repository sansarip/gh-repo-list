(ns my-website.components.database
  (:require [com.stuartsierra.component :as component]
            [next.jdbc.connection :as connection])
  (:import (com.zaxxer.hikari HikariDataSource)))

(defrecord Database [db-spec ^HikariDataSource datasource]
  component/Lifecycle

  (start [this]
    (if datasource
      this ; already started
      (assoc this :datasource (connection/->pool HikariDataSource db-spec))))

  (stop [this]
    (if datasource
      (do
        (.close datasource)
        (assoc this :datasource nil))
      this))) ; already stopped

(defn new-database [db-spec]
  (map->Database {:db-spec db-spec}))