(ns syslog-ng.org.widgets
  (:use [hiccup.core]
        [hiccup.page]
        [hiccup.element]
        [hiccup.util])
  (:require [clj-rss.core :as rss]
            [clj-time.core :as time]
            [clj-time.format :as time.format]
            [clj-time.coerce :as time.coerce]))

(def ^:dynamic *root-url*)

(defn- page:header []
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
     [:a.navbar-brand.nav-link {:href (str *root-url* "#top")}
      "syslog-ng"]]

    [:div.collapse.navbar-collapse.navbar-ex1-collapse
     [:ul.nav.navbar-nav.navbar-right
      [:li
       [:a.nav-link {:href (str *root-url* "#getting-started")} "Getting started"]]
      [:li
       [:a.nav-link {:href (str *root-url* "#highlights")} "Highlights"]]
      [:li
       [:a.nav-link {:href (str *root-url* "#why")} "Why syslog-ng?"]]
      [:li
       [:a.nav-link {:href (str *root-url* "#news")} "News"]]]]

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
      "Third-party packages"]
     [:a {:href "https://github.com/balabit/syslog-ng-incubator"} "Incubator"]
     [:a.nav-link {:href "#eol"
                   :data-toggle "modal"}
      "Supported versions"]]
    [:div.row.contact
     [:div.col-md-10.col-md-offset-1.text-center
      [:div.hi-icon-wrap
       [:a {:href "https://twitter.com/sngOSE"
            :title "Twitter"}
        [:i.hi-icon.fa.fa-twitter]]
       [:a {:href "https://github.com/balabit/syslog-ng"
            :title "Source code"}
        [:i.hi-icon.fa.fa-github]]
       [:a {:href "https://lists.balabit.hu/mailman/listinfo/syslog-ng"
            :title "Mailing list"}
        [:i.hi-icon.fa.fa-envelope]]
       [:a {:href "irc://chat.freenode.net/#syslog-ng"
            :title "IRC"}
        [:i.hi-icon.fa.fa-users]]
       [:a.nav-link {:href "#downloads"
            :title "Downloads"}
        [:i.hi-icon.fa.fa-download]]]]]]])

(defn- widget:container [id title & body]
  [:div.container
   [:h3.subhead {:id id} title]
   body])

(defn- page:getting-started []
  (widget:container
   "getting-started" "Getting started"

   [:div.row.getting-started
    [:div.col-md-10.col-md-offset-1
     [:p
      (str "To get started with syslog-ng, it must be installed. "
           "Most GNU/Linux distributions ship with binary packages, "
           "so either ")
      [:code "apt-get install syslog-ng"] " or "
      [:code "yum install syslog-ng"] " is enough to get started. "
      "For other distributions and operating systems, the "
      (link-to
       "http://www.balabit.com/network-security/syslog-ng/opensource-logging-system/downloads/3rd-party"
       "third-party packages list")
      " may be of use. To compile from source, please consult the "
      (link-to
       "https://github.com/balabit/syslog-ng#installation-from-source"
       "README") "."]
     [:p
      "Once installed, a simple configuration (to be placed in "
      [:code "/etc/syslog-ng/syslog-ng.conf"] "), that puts all "
      "system logs down into a single file, is presented below:"]
     [:pre
      "@version: 3.5
@include \"scl.conf\"

source      s_system { system(); internal();                 };
destination d_all    { file(\"/var/log/all.log\");             };
log                  { source(s_system); destination(d_all); };"]

     [:div.col-md-10.col-md-offset-1.text-center
      [:h3 {:id "downloads"} "Get the source"]
      [:ul.list-inline
       [:li
        [:a {:href "https://github.com/balabit/syslog-ng/releases/latest"
             :id "release-syslog-ng"}
         [:img {:src
                "//img.shields.io/badge/syslog--ng-latest-246EAB.svg?style=flat"}]]]
       [:li
        [:a {:href "https://github.com/balabit/syslog-ng-incubator/releases/latest"
             :id "release-syslog-ng-incubator"}
         [:img {:src
                "//img.shields.io/badge/syslog--ng--incubator-latest-246EAB.svg?style=flat"}]]]]]]]))

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
          "computer, utilizing all available cores. Thanks to its "
          "flexible configuration, you can also build an "
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

(defn- page:why []
  (widget:container
   "why" "Why syslog-ng?"

   [:div.row.why
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
          "syslog-ng's patterndb, you can correlate events together, "
          "and transform them into a much more useful structure.")]]

   [:div.row.why
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

(defn- news:item [title date & content]
  {:id (str "news/" date)
   :title title
   :date date
   :content content})

(defn- news:item->html [item]
  (list
   [:h4 {:id (:id item)} (:title item)
    [:small.pull-right [:i.fa.fa-calendar] " " (:date item)]]

   (:content item)))

(defn- news:item->rss [item]
  {:title (:title item)
   :link (str "http://www.syslog-ng.org/#" (:id item))
   :description (str "<![CDATA[" (html (news:item->html item)) "]]>")
   :pubDate (time.coerce/to-date (time.format/parse
                                  (time.format/formatter "yyyy-MM-dd")
                                  (:date item)))})

(def news-feed
  [(news:item
    "Google Summer of Code 2014: Midterm"
    "2014-06-30"
    [:p
     "We are happy to share the good news that "
     (link-to
      "http://www.google-melange.com/gsoc/org2/google/gsoc2014/syslog_ng"
      "all students") " passed the mid-term evaluation, and they "
     "are all making good progress!"])

   (news:item
    "Google Summer of Code 2014: Accepted syslog-ng proposals"
    "2014-04-23"

    [:p
     "We received a number of very strong proposals for this years "
     "Google Summer of Code programme, out of which, we were able "
     "to select four. "
     (link-to
      "https://algernon.blogs.balabit.com/2014/04/gsoc2014-syslog-ng-accepted-projects/"
      "Read here") " for further information."])

   (news:item
    "Google Summer of Code 2014: syslog-ng ACCEPTED!"
    "2014-02-25"

    [:p
     "After two years of participating in the Google Summer of Code "
     "programme under the umbrella of the "
     (link-to "http://www.opensuse.org/en/" "openSUSE") " project, "
     "this year, we are "
     (link-to
      "http://www.google-melange.com/gsoc/org2/google/gsoc2014/syslog_ng"
      "accepted on our own") "! We have a "
     (link-to
      "https://github.com/balabit/syslog-ng/wiki/GSoC2014-Idea-&-Project-list"
      "list of ideas") ", students are encouraged to add "
     "their own ideas, or "
     (link-to "/#top" "contact us") " if interested, or want to know "
     "more."])

   (news:item
    "Google Summer of Code preparations"
    "2014-02-10"

    [:p
     (str
      "As in previous years, we are applying to participate in the "
      "Google Summer of Code programme. We have a list of very "
      "interesting and worthwhile projects to pursue, great "
      "opportunities for any student to learn, and earn a name with "
      "contributing to software used world-wide.")])])

(defn- page:news []
  (widget:container
   "news" "News"

   [:div.col-md-10.col-md-offset-1.news
    (map news:item->html (take 5 news-feed))]))

(defn- page:footer []
  [:footer
   [:div.container.clearfix
    [:p.pull-left
     "Copyright &copy; 2014 "
     (link-to "http://www.balabit.com/"
              "BalaBit IT Security Ltd")]]])

(defn- page:analytics []
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

(defn- page:wrap [contents]
  (html5
   (page:header)
   [:body
    (page:navbar)

    contents

    (page:footer)

    (include-js "//code.jquery.com/jquery-1.10.1.min.js")
    (include-js "bundles/js/all.js")

    (page:analytics)])
  )

(defn- eol:intro []
  [:div.modal.fade {:id "eol"
                    :tabindex -1
                    :role "dialog"
                    :aria-labelledby "eol-label"
                    :aria-hidden "true"}
   [:div.modal-dialog
    [:div.modal-content
     [:div.modal-header
      [:button.close {:type "button"
                      :data-dismiss "modal"}
       [:span {:aria-hidden "true"} "&times;"]
       [:span.sr-only "Close"]]
      [:h4.modal-title {:id "eol-label"}
       "Support of syslog-ng OSE versions"]]
     [:div.modal-body
      [:ul.nav.nav-tabs {:role "tablist"}
       [:li.active
        [:a {:href "#eol-stable"
             :role "tab"
             :data-toggle "tab"} "Stable"]]
       [:li
        [:a {:href "#eol-devel"
             :role "tab"
             :data-toggle "tab"} "Development"]]]
      [:div.tab-content
       [:div.tab-pane.active {:id "eol-stable"}
        [:p
         "The stable branch, receiving all kinds of bug fixes, "
         "including smaller fixes backported from newer branches "
         "(including the next feature release and the development "
         "branch too). It may also receive very small features too, "
         "which do not affect the package as a whole."]
        [:p
         "The " (link-to
                 "https://github.com/balabit/syslog-ng-incubator"
                 "Incubator") " is built against this branch."]
        [:p
         "Currently the " [:strong "3.5"] " branch fills this role."]
        [:p
         "Expected end of life: " [:strong "2016 Q1"]]]
       [:div.tab-pane {:id "eol-devel"}
        [:p
         "The next stable branch, which we consider stable enough, but "
         "has not received as wide testing as the stable branch yet. "
         "This gets the most attention, as the idea is to stabilise "
         "it as soon as possible. Other than being newer than the "
         "Stable branch, this is treated in exactly the same way."]

        [:p
         "Currently the " [:strong "3.6"] " branch fills this role."]
        [:p
         "Expected end of life: " [:strong "2017 Q1"]]]]]
     [:div.modal-footer
      [:button.btn.btn-primary {:type "button"
                                :data-dismiss "modal"}
       "Close"]]]]])

(defn index []
  (with-redefs [*root-url* ""]
    (page:wrap
     (list (page:jumbotron)
      (map (fn [f body]
             (f body))
           (cycle [identity widget:container-alternate])
           [(page:getting-started) (page:highlights) (page:why)
            (page:news) (eol:intro)])))))

(defn rss []
  (with-base-url "http://www.syslog-ng.org/"
    (apply rss/channel-xml
           {:title "syslog-ng.org news"
            :link "http://www.syslog-ng.org/#news"
            :description "syslog-ng.org news"}
           (map news:item->rss news-feed))))
