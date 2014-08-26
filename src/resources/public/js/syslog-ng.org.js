function scrollTo (link) {
    var href = link.attr("href");
    $("html,body").animate({scrollTop: $(href).offset().top - 100}, 500);
}

$(".nav-link").click(function(e) {
    scrollTo($(this));
});

$(document).ready(function () {
  function get_latest_release (repo, prerelease, badge_name, branch, color) {
    if (!branch)
        branch = repo;
    if (!color)
        color = "246EAB"
    if (!badge_name)
        badge_name = repo.replace(/-/g, "--");
    $.getJSON("https://api.github.com/repos/balabit/" + repo + "/releases",
              function (data) {
                var v = data.filter(function (e) { return e.prerelease == prerelease; })[0].tag_name.replace(/^[^0-9]*/, "");
                $("#release-" + branch + " img").attr('src', "//img.shields.io/badge/" + badge_name + "-" + v + "-" + color + ".svg?style=flat");
            })
  };

  get_latest_release("syslog-ng", false);
  get_latest_release("syslog-ng-incubator", false);
  get_latest_release("syslog-ng", true, "syslog--ng%20BETA", "syslog-ng-beta", "F7841E");
});
