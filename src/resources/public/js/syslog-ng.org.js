function scrollTo (link) {
    var href = link.attr("href");
    $("html,body").animate({scrollTop: $(href).offset().top - 100}, 500);
}

$(".nav-link").click(function(e) {
    e.preventDefault();
    scrollTo($(this));
});
