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
