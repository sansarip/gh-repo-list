(ns my-website.views.repos.state)

(def fsm {:start             'fetching-t-r
          'fetching-t-r      {:succeed 'fetching-r
                              :fail    'fetch-t-r-failure}
          'fetch-t-r-failure {:ok    'done
                              :retry 'fetching-t-r}
          'fetching-r        {:succeed 'done
                              :fail    'fetch-r-failure}
          'fetch-r-failure   {:ok    'done
                              :retry 'fetching-t-r}
          'done              {:refresh 'fetching-t-r}})
