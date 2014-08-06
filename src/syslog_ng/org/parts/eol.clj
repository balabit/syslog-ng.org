(ns syslog-ng.org.parts.eol
  (:require [hiccup.element :refer :all]))

(defn infobox
  []

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
