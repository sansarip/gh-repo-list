(ns my-website.state)

(defn next-state [fsm app-state transition]
  (let [current-state (:state app-state)
        new-state (get-in fsm [current-state transition])
        _ (js/console.log (str "Transitioning from " current-state " to " new-state))]
    (assoc app-state :state new-state)))

