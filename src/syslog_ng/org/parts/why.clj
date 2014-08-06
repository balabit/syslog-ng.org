(ns syslog-ng.org.parts.why
  (:require [syslog-ng.org.widgets :as widgets]))

(defn why-syslog-ng? []
  (widgets/container
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
