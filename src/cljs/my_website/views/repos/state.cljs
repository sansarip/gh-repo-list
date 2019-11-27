(ns my-website.views.repos.state)

(def fsm {:start               'fetching-templates-and-repos
          'fetching-templates  {:success 'fetch-templates-success
                                :fail    'fetch-templates-failure}
          'fetch-templates-failure {:ok 'chilling
                                    :fetch}
          'fetch-repos-success {:ok    'chilling
                                :fetch 'fetching-repos}
          'fetch-repos-failure {:ok    'chilling
                                :fetch 'fetching-repos}
          'fetching-repos      {:fail    'fetch-repos-failure
                                :succeed 'fetch-repos-success}
          'chilling            {:fetch-templates 'fetching-templates}})
