(ns syslog-ng.org.parts.navbar)

(defn bar
  []

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
       [:a.nav-link {:href "#getting-started"} "Getting started"]]
      [:li
       [:a.nav-link {:href "#highlights"} "Highlights"]]
      [:li
       [:a.nav-link {:href "#why"} "Why syslog-ng?"]]
      [:li
       [:a.nav-link {:href "#news"} "News"]]]]

    [:ul.nav.navbar-nav.visible-md.visible-lg
     {:style "position:absolute;right:1.5em;top:0.5em;"}
     [:li
      [:a {:href "http://www.syslog-ng.com/"} ".com"]]]]])
