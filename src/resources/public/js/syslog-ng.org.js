function scrollTo (link) {
    var href = link.attr("href");
    $("html,body").animate({scrollTop: $(href).offset().top - 100}, 500);
}

$(".nav-link").click(function(e) {
    e.preventDefault();
    scrollTo($(this));
});

$(document).ready(function () {
  function get_latest_release (repo) {
    var badge_name = repo.replace(/-/g, "--");
    $.getJSON("https://api.github.com/repos/balabit/" + repo + "/releases",
              function (data) {
                var v = data[0].tag_name.replace(/^[^0-9]*/, "");
                $("#release-" + repo + " img").attr('src', "//img.shields.io/badge/" + badge_name + "-" + v + "-246EAB.svg?style=flat");
            })
  };

  get_latest_release("syslog-ng");
  get_latest_release("syslog-ng-incubator");
});
