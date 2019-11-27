(ns my-website.views.repos.state)

(def fsm {:start               'fetch-repos
          'repos               {}
          'fetch-repos-success {:ok 'repos}
          'fetch-repos-failed  {:ok    'repos
                                :retry 'fetch-repos}
          'fetch-repos         {:fail    'fetch-repos-failed
                                :success 'fetch-repos-success}})
