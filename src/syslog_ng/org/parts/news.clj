(ns syslog-ng.org.parts.news
  (:require [hiccup.element :refer :all]
            [hiccup.core :refer :all]

            [clj-time.core :as time]
            [clj-time.format :as time.format]
            [clj-time.coerce :as time.coerce]

            [syslog-ng.org.widgets :as widgets]))

(defn- news:item [title date & content]
  {:id (str "news/" date)
   :title title
   :date date
   :content content})

(def news-feed
  [(news:item
    "New release: syslog-ng OSE 3.5.6"
    "2014-08-05"

    [:p
     "A new version of the syslog-ng 3.5 stable branch has been "
     "released. Highlights include a memory leak fix, and "
     [:code "pdbtool merge"] " generating patterndb v4 files."]

    [:p
     "For more information, please see the "
     (link-to
      "https://github.com/balabit/syslog-ng/releases/tag/v3.5.6"
      "release notes") "!"])

   (news:item
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

(defn rss-feed []
  (map news:item->rss news-feed))

(defn page []
  (widgets/container
   "news" "News"

   [:div.col-md-10.col-md-offset-1.news
    (map news:item->html (take 5 news-feed))]))
