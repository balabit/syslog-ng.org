(ns syslog-ng.org.widgets
  (:use [hiccup.core]
        [hiccup.page]
        [hiccup.element]))

(defn- page:header []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width,initial-scale=1.0"}]
   [:title "syslog-ng.org"]
   (include-css "bundles/css/all.css")
   (include-css "http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css")])

(defn- page:navbar []
  [:nav.navbar.navbar-default.navbar-fixed-top {:role "navigation"}
   [:div.container
    [:div.navbar-header
     [:button.navbar-toggle {:type "button"
                             :data-toggle "collapse"
                             :data-target ".navbar-ex1-collapse"}
      [:span.icon-bar]
      [:span.icon-bar]
      [:span.icon-bar]]
     [:a.navbar-brand.nav-link {:href "#top"}
      "syslog-ng"]]

    [:div.collapse.navbar-collapse.navbar-ex1-collapse
     [:ul.nav.navbar-nav.navbar-right
      [:li
       [:a.nav-link {:href "#highlights"} "Highlights"]]
      [:li
       [:a.nav-link {:href "#features"} "Features"]]
      [:li
       [:a.nav-link {:href "#news"} "News"]]]]

    [:ul.nav.navbar-nav.visible-md.visible-lg
     {:style "position:absolute;right:1.5em;top:0.5em;"}
     [:li
      [:a {:href "http://www.syslog-ng.com/"} ".com"]]]]])

(defn- page:jumbotron []
  [:div.jumbotron {:id "top"}
   [:div.container
    [:h1 "syslog-ng"]
    [:h2
     (str "Open Source log management solution with over a million "
          "global users, an enhanced syslog daemon: the Babel fish "
          "of event processing")]
    [:p [:a.btn.btn-primary.btn-lg
    {:href "https://github.com/balabit/syslog-ng"}
      "Visit us on GitHub "
      [:span.fa.fa-chevron-circle-right.fa-lg.hidden-xs.hidden-sm]]]

    [:p.small.jumbolinks
     [:a {:href "http://www.balabit.com/sites/default/files/documents/syslog-ng-ose-3.5-guides/en/syslog-ng-ose-v3.5-guide-admin/html/index.html"} "Documentation"]
     [:a {:href
          "http://www.balabit.com/network-security/syslog-ng/opensource-logging-system/downloads/3rd-party"}
      "Third-party packages"]]
    [:div.row.contact
     [:div.col-md-10.col-md-offset-1.text-center
      [:div.hi-icon-wrap
       (link-to "https://twitter.com/sngOSE"
                [:i.hi-icon.fa.fa-twitter])
       (link-to "https://github.com/balabit/syslog-ng"
                [:i.hi-icon.fa.fa-github])
       (link-to "https://lists.balabit.hu/mailman/listinfo/syslog-ng"
                [:i.hi-icon.fa.fa-envelope])
       (link-to "irc://chat.freenode.net/#syslog-ng"
                [:i.hi-icon.fa.fa-users])]]]]])

(defn- widget:container [id title & body]
  [:div.container
   [:h3.subhead {:id id} title]
   body])

(defn- widget:highlight-ball [icon title text]
  [:div.col-md-4.col-sm-6.highlight
   [:div.highlight-ball
    [:span {:class (str "fa fa-" icon)}]]
   [:h3 title]
   [:p text]])

(defn- page:highlights []
  (widget:container
   "highlights" "Highlights"

   [:div.row.highlights
    (widget:highlight-ball
     "code" "Open source"
     (str "Released under a combination of the GNU General Public "
          "License (GPL) and Lesser General Public License (LGPL) - "
          "contributor agreement not required. Developed in the "
          "open: code, issues, mailing list all available!"))

    (widget:highlight-ball
     "sitemap" "Scalable"
     (str "The syslog-ng application scales well within a single "
          "computer, to utilize all available cores. Thanks to its "
          "flexible configuration, you cab also build an "
          "architecture that spans multiple computers."))

    (widget:highlight-ball
     "wrench" "Flexible"
     (str "The configuration is very expressive, flexible, yet, still "
          "human readable. From the discrete building blocks of "
          "sources, parsers, filters, rewrite rules, and "
          "destinations, you can build incredibly powerful "
          "systems."))]))

(defn- widget:container-alternate [body]
  [:div.container-alternate body])

(defn- page:features []
  (widget:container
   "features" "Features"

   [:div.row.features
    [:p.col-md-4.col-sm-6
     [:strong "RFC3164 or RFC5424?"] [:br]
     (str "Whether you want to work with legacy BSD syslog (RFC3164) "
          "or the enhanced RFC5424 protocol, syslog-ng has you "
          "covered. Its flexible parser can process pretty much any "
          "variant of these protocols that you find in the wild.")]

    [:p.col-md-4.col-sm-6
     [:strong "You have unstructured data?"] [:br]
     (str "You have data in an unstructured format? That's not a "
          "problem: syslog-ng comes with a set of built-in parsers, "
          "which you can combine to build very complex things.")]

    [:p.col-md-4.col-sm-6
     [:strong "Are your logs all over the place?"] [:br]
     (str "Even if the incoming events are all over the place, with "
          "syslog-ng's you can correlate events together, and "
          "transform them into a much more useful structure.")]]

   [:div.row.features
    [:p.col-md-4.col-sm-6
     [:strong "Databases, you say?"] [:br]
     (str "If you need to store your log messages in a database, you "
          "don't need to look any further! We have SQL (MySQL, "
          "PostgreSQL, even Oracle!), MongoDB. We also support "
          "inserting messages into Redis, if that's what you are "
          "after.")]

    [:p.col-md-4.col-sm-6
     [:strong "Message queues?"] [:br]
     (str "No problem! We support the Advanced Message Queuing "
          "Protocol (AMQP) and the Simple Text Oriented Messaging "
          "Protocol (STOMP) too, with more in the pipeline.")]

    [:p.col-md-4.col-sm-6
     [:strong "You need something special?"] [:br]
     (str "Even if you need something unique, there's a good chance "
          "that syslog-ng, the swiss army knife (or Babel fish) of "
          "logging already has the tools to support you. But even if "
          "not, contributing is easy! With responsive users and "
          "developers all around the globe.")]]))

(defn- page:news []
  (widget:container
   "news" "News"

   [:div.col-md-10.col-md-offset-1
    [:h4 "Google Summer of Code preparations"
     [:small.pull-right [:i.fa.fa-calendar] " 2014-02-10"]]
    [:p {:style "margin-bottom: 15em"}
     (str
      "As in previous years, we are applying to participate in the "
      "Google Summer of Code programme. We have a list of very "
      "interesting and worthwhile projects to pursue, great "
      "opportunities for any student to learn, and earn a name with "
      "contributing to software used world-wide.")]]))

(defn- page:footer []
  [:footer
   [:div.container.clearfix
    [:p.pull-left
     "Copyright &copy; 2014 "
     (link-to "http://www.balabit.com/"
              "BalaBit IT Security Ltd")]]])

(defn index []
  (html5
   (page:header)
   [:body
    (page:navbar)
    (page:jumbotron)

    (map (fn [f body]
           (f body))
         (cycle [identity widget:container-alternate])
         [(page:highlights) (page:features) (page:news)])
    (page:footer)

    (include-js "http://code.jquery.com/jquery-1.10.1.min.js")
    (include-js "bundles/js/all.js")

    [:script
     (str
      "(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){"
      "(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),"
      "m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)"
      "})(window,document,'script','//www.google-analytics.com/analytics.js','ga');"
      "ga('create', 'UA-3350024-43', 'syslog-ng.org');"
      "ga('send', 'pageview');")]]))
