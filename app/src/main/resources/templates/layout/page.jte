@import hexlet.code.util.NamedRoutes
@import hexlet.code.util.ResourceRoutes
@import gg.jte.Content
@param Content content


<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="${ResourceRoutes.BOOTSTRAP_LINK}" rel="stylesheet" integrity="${ResourceRoutes.BOOTSTRAP_INTEGRITY}" crossorigin="anonymous">
    <style>
        td:last-child {
            text-align: right;
        }
    </style>
    <title>Hexlet Project 4</title>
</head>
<body>
<header class="d-flex justify-content-center py-3">
    <nav class="navbar navbar-light bg-light">
        <button class="btn btn-outline-success me-1" type="button"><a href="${NamedRoutes.ROOT_PATH}" class="nav-link" >Root page</a></button>
        <button class="btn btn-outline-success me-1" type="button"><a href="${NamedRoutes.URL_PATH}" class="nav-link">All Urls</a></button>
    </nav>
</header>
<div class="container">
    ${content}
</div>
</body>
</html>