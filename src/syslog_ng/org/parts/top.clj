(ns syslog-ng.org.parts.top)

(defn jumbotron
  []

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
