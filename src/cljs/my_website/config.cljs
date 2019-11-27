(ns my-website.config)

(def debug?
  ^boolean goog.DEBUG)

(def api-spec {:uri       ""
               :endpoints {:repos    {:path "repos"}
                           :template {:path "template"}}})