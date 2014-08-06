(ns syslog-ng.org.parts.highlights
  (:require [syslog-ng.org.widgets :as widgets]))

(defn- ball [icon title text]

  [:div.col-md-4.col-sm-6.highlight
   [:div.highlight-ball
    [:span {:class (str "fa fa-" icon)}]]
   [:h3 title]
   [:p text]])

(defn balls []
  (widgets/container
   "highlights" "Highlights"

   [:div.row.highlights
    (ball
     "code" "Open source"
     (str "Released under a combination of the GNU General Public "
          "License (GPL) and Lesser General Public License (LGPL) - "
          "contributor agreement not required. Developed in the "
          "open: code, issues, mailing list all available!"))

    (ball
     "sitemap" "Scalable"
     (str "The syslog-ng application scales well within a single "
          "computer, utilizing all available cores. Thanks to its "
          "flexible configuration, you can also build an "
          "architecture that spans multiple computers."))

    (ball
     "wrench" "Flexible"
     (str "The configuration is very expressive, flexible, yet, still "
          "human readable. From the discrete building blocks of "
          "sources, parsers, filters, rewrite rules, and "
          "destinations, you can build incredibly powerful "
          "systems."))]))
