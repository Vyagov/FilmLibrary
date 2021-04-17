jQuery(document).ready(function ($) {
    $("#search-form").submit(function (event) {
        enableSearchButton(false);

        event.preventDefault();

        searchWithAjax();
    });
});

function searchWithAjax() {
    let search = {}
    search["movieTitle"] = $("#searchMovie").val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/search",
        data: JSON.stringify(search),
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            display(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
            display(e);
        },
        done: function (e) {
            console.log("DONE");
            enableSearchButton(true);
        }
    });
}

function enableSearchButton(flag) {
    $("#btn-search").prop("disabled", flag);
}

function display(data) {
    let invalid = $("#invalidSearch").text("");

    $.each(data, function (str, v) {
        if (str === "message" && v) {
            invalid.text(v);
        }
        if (str === "movies" && v) {
            let tree = "";

            $.each(v, function (index, movie) {
                tree = $("<div>", {id: "my-col", class: "col-3"});
                let divTag = $("<div>", {class: "content thumbnail div-img"});
                let aTag = $("<a>");

                $.each(movie, function (id, valueId) {
                    if (id === "id") {
                        aTag.attr("href", "/movies/details/" + valueId);
                    }
                });
                let imgTag = $("<img alt='Movie' width='100%'>");

                $.each(movie, function (poster, valuePoster) {
                    if (poster === "poster") {
                        imgTag.attr("src", valuePoster);
                    }
                });
                aTag.append(imgTag);
                divTag.append(aTag);
                let divWithPTitle = $("<div>");

                $.each(movie, function (title, valueTitle) {
                    if (title === "movieTitle") {
                        divWithPTitle.append($("<p>").text(valueTitle));
                    }
                });
                divTag.append(divWithPTitle);
                let divWithPDuration = $("<div>");

                $.each(movie, function (duration, valueDuration) {
                    if (duration === "duration") {
                        divWithPDuration.append($("<p>").text(valueDuration));
                    }
                });
                divTag.append(divWithPDuration);
                tree.append(divTag);
            });
            $("#tree").empty().append(tree);
        }
    });
}
