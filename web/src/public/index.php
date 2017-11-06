<?php

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpKernel\HttpKernel;

require __DIR__.'/../../bootstrap.php';

$kernel = $container[HttpKernel::class];
$request = Request::createFromGlobals();
$response = $kernel->handle($request);
$response->send();
$kernel->terminate($request, $response);

use Nstrohe\Klarheit\Lib\Container;
$container = new Container(__DIR__ . '/../../../app-config/globals.php');
define('BASE_PATH', $container['path']['base']);