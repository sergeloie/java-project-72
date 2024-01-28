@import hexlet.code.util.NamedRoutes
@import hexlet.code.util.Constants
@import gg.jte.Content
@param Content content


<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="${Constants.BOOTSTRAP_LINK}" rel="stylesheet" integrity="${Constants.BOOTSTRAP_INTEGRITY}" crossorigin="anonymous">
    <title>Hexlet Project 4</title>
</head>
<body>
<div>
    ${content}
</div>
</body>
</html>