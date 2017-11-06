<?php

require __DIR__ . '/../../../vendor/autoload.php';
use Nstrohe\Klarheit\Lib\Container;
$container = new Container(__DIR__ . '/../../../app-config/globals.php');
define('BASE_PATH', $container['path']['base']);