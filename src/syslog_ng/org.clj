(ns syslog-ng.org
  (:require [stasis.core :as stasis]
            [syslog-ng.org.widgets :as w]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [optimus.prime :as optimus]
            [optimus.assets :as assets]
            [optimus.optimizations :as optimizations]
            [optimus.strategies :refer [serve-live-assets serve-frozen-assets]]
            [optimus.export]))

(defn get-assets []
  (concat
   (assets/load-bundles "resources/public"
                        {"css/all.css" ["/css/bootstrap.css"
                                        "/css/bootstrap-theme.css"
                                        "/css/syslog-ng.org.css"]
                         "js/all.js" ["/js/bootstrap.js"
                                      "/js/syslog-ng.org.js"]})
   (assets/load-assets "resources/public"
                       ["/images/logo.48x48.png"])))

(def pages {"/index.html" (w/index)
            "/news.rss" (w/rss)})

(def app (-> (stasis/serve-pages pages)
             (optimus/wrap get-assets optimizations/all
                           serve-frozen-assets)
             wrap-content-type))

(defn export []
  (let [assets (optimizations/all (get-assets) {})]
    (stasis/empty-directory! "out")
    (optimus.export/save-assets assets "out")
    (stasis/export-pages pages "out" {:optimus-assets assets})))
