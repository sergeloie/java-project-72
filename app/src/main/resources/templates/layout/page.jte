@import hexlet.code.util.NamedRoutes
@import hexlet.code.util.ResourceRoutes
@import gg.jte.Content
@param Content content


<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="${ResourceRoutes.BOOTSTRAP_LINK}" rel="stylesheet" integrity="${ResourceRoutes.BOOTSTRAP_INTEGRITY}" crossorigin="anonymous">
    <title>Hexlet Project 4</title>
</head>
<body>
<div>
    ${content}
</div>
</body>
</html>