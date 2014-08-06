(ns syslog-ng.org.widgets)

(defn container [id title & body]
  [:div.container
   [:h3.subhead {:id id} title]
   body])

(defn container-alternate [body]
  [:div.container-alternate body])
