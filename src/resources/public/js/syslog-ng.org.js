$(".nav-link").click(function(e) {
    e.preventDefault();
    var link = $(this);
    var href = link.attr("href");
    $("html,body").animate({scrollTop: $(href).offset().top - 80}, 500);
    link.closest(".navbar").find(".navbar-toggle:not(.collapsed)").click();
});
