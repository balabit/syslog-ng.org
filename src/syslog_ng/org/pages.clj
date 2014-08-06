(ns syslog-ng.org.pages
  (:require [hiccup.util :refer :all]
            [clj-rss.core :as rss]

            [syslog-ng.org.widgets :as widgets]
            [syslog-ng.org.widgets.page :as page]

            [syslog-ng.org.parts.top :as top]
            [syslog-ng.org.parts.getting-started :as getting-started]
            [syslog-ng.org.parts.highlights :as highlights]
            [syslog-ng.org.parts.why :as why]
            [syslog-ng.org.parts.news :as news]
            [syslog-ng.org.parts.eol :as eol]))

(defn index []
  (page/wrap
   (list (top/jumbotron)
         (map (fn [f body]
                (f body))
              (cycle [identity widgets/container-alternate])
              [(getting-started/block) (highlights/balls)
               (why/why-syslog-ng?)
               (news/page) (eol/infobox)]))))

(defn rss []
  (with-base-url "http://www.syslog-ng.org/"
    (apply rss/channel-xml
           {:title "syslog-ng.org news"
            :link "http://www.syslog-ng.org/#news"
            :description "syslog-ng.org news"}
           (news/rss-feed))))
