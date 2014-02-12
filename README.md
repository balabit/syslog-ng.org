The [syslog-ng.org][sngo] website
=================================

This is the source of [syslog-ng.org][sngo]. To build it, you need to
have [Leiningen][lein] installed, and afterwards the static site is
buildable with the `lein build-site` command. The needed files are
`out/index.html` and `out/bundles/`, the rest is not used by the site.

To run a local server that serves the content and rebuilds
automatically, run `lein ring server-headless`.

 [sngo]: http://syslog-ng.org/
 [lein]: https://github.com/technomancy/leiningen#leiningen

License
-------

Copyright © 2014 BalaBit IT Security Ltd, under the
[Creative Commons Attribution Share-Alike][ccbysa] license.

 [ccbysa]: http://creativecommons.org/licenses/by-sa/4.0/
