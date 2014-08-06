(ns syslog-ng.org.widgets.page
  (:require [hiccup.page :refer :all]
            [hiccup.element :refer :all]

            [syslog-ng.org.parts.navbar :as navbar]))

(defn- header []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width,initial-scale=1.0"}]
   [:title "syslog-ng.org"]
   [:link {:href "/news.rss"
           :rel "alternate"
           :type "application/rss+xml"
           :title "RSS 2.0 Feed"}]
   (include-css "bundles/css/all.css")
   (include-css "//fonts.googleapis.com/css?family=Asap")
   (include-css "//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css")])

(defn- footer []
  [:footer
   [:div.container.clearfix
    [:p.pull-left
     "Copyright &copy; 2014 "
     (link-to "http://www.balabit.com/"
              "BalaBit IT Security Ltd")]]])

(defn- analytics []
  (list
   [:script
     (str
      "(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){"
      "(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),"
      "m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)"
      "})(window,document,'script','//www.google-analytics.com/analytics.js','ga');"
      "ga('create', 'UA-3350024-43', 'syslog-ng.org');"
      "ga('send', 'pageview');")]
   [:script
     (str
      "(function(){var didInit = false;function initMunchkin(){"
      "if(didInit === false) {didInit = true;Munchkin.init('855-UZV-853');}}"
      "var s = document.createElement('script');s.type = 'text/javascript';"
      "s.async = true;s.src = '//munchkin.marketo.net/munchkin.js';"
      "s.onreadystatechange = function() {if (this.readyState == 'complete' "
      "|| this.readyState == 'loaded') {initMunchkin();}};"
      "s.onload = initMunchkin;"
      "document.getElementsByTagName('head')[0].appendChild(s);})();")]))

(defn wrap [contents]
  (html5
   (header)
   [:body
    (navbar/bar)

    contents

    (footer)

    (include-js "//code.jquery.com/jquery-1.10.1.min.js")
    (include-js "bundles/js/all.js")

    (analytics)]))
