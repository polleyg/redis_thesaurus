<html>
<head>
    <title>Thesaurus built with Redis</title>
    <link rel="shortcut icon"
          href="http://images5.fanpop.com/image/photos/30800000/The-Big-Lebowski-the-big-lebowski-30869205-1600-1200.png">
</head>
<body>

<form action="Thesaurus.php" method="post">
    Enter A Root Word: <input type="text" name="root" placeholder="enter root">
    Sort Results: <input type="radio" name="sort" value="yes" checked="checked">Yes
    <input type="radio" name="sort" value="no">No<br><br>
    <input type="submit" value="Fetch Synonyms!">
</form>

</body>
</html>
<?php

$root = $_POST["root"];
$sort = $_POST["sort"];

$redis = new Redis();
$redis->connect('127.0.0.1', 6379, 1);

if ($redis->exists($root)) {
    if (strcmp($sort, 'yes') == 0) {
        //sorted
        $members = $redis->sort($root, array('alpha' => true));
    } else {
        //unsorted
        $members = $redis->sMembers($root);
    }
    print("<ul>");
    foreach ($members as $word) {
        print("<li>$word</li>");
    }
    print("</ul>");
} else {
    print("<p>I'm sorry, but I couldn't find any match for $root in Redis. Try again.</p>");
}
?>
