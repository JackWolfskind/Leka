var logger = {
    post: function () {
        var username = $("#username").val();
        var passsword = $("#password").val();
        var lastPath = $("#last_path").val();
        $.post("/login", {
            username: username,
            passsword: passsword,
            lastPath: lastPath

        }).then(function (response) {
        }, function (response) {
        });
    }
};