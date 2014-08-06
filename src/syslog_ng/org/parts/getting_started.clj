(ns syslog-ng.org.parts.getting-started
  (:require [hiccup.element :refer :all]
            [syslog-ng.org.widgets :as widgets]))

(defn block
  []

  (widgets/container
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
