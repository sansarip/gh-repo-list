(ns my-website.db)

(def default-db
  {:state        nil
   :fsm          nil
   :active-panel #(vector :div "default")
   :repos/db     {:repos    []
                  :template []
                  :selected #{}
                  :search-value ""}})
