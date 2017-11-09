<?php

use Leka\Controller\AuthController;
use Leka\Controller\IndexController;
use Leka\Controller\ServiceController;
use Leka\Middleware\LoggerMiddleware;
use Leka\Middleware\LoginMiddleware;
use Monolog\Handler\StreamHandler;
use Monolog\Logger;
use Monolog\Processor\UidProcessor;
use Slim\Views\Twig;
use Slim\Views\TwigExtension;

// DIC configuration

$container = $app->getContainer();

// view renderer
$container['renderer'] = function ($c) {
    $settings = $c->get('settings')['renderer'];
    $view = new Twig($settings['template_path'], [
        'cache' => $settings['cache_path'],
        'debug' => true
    ]);

    // Instantiate and add Slim specific extension
    $basePath = rtrim(str_ireplace('index.php', '', $c['request']->getUri()->getBasePath()), '/');
    $view->addExtension(new TwigExtension($c['router'], $basePath));
    return $view;
};

// monolog
$container['logger'] = function ($c) {
    $settings = $c->get('settings')['logger'];
    $logger = new Logger($settings['name']);
    $logger->pushProcessor(new UidProcessor());
    $logger->pushHandler(new StreamHandler($settings['path'], $settings['level']));
    return $logger;
};

// logger-middleware
$container[LoggerMiddleware::class] = function ($c) {
    $loggerMiddleware = new LoggerMiddleware($c['logger']);
    return $loggerMiddleware;
};

$container[LoginMiddleware::class] = function ($c) {
    $loginMiddleware = new LoginMiddleware($c['renderer']);
    return $loginMiddleware;
};

$container["service"] = function () {
    $service = new RestClient([
        'base_url' => "localhost:8080",
        'format' => "json",
    ]);
    return $service;
};

$container[\Leka\Controller\IndexController::class] = function($c) {
    $renderer = $c->get("renderer"); // retrieve the 'renderer' from the container
    return new IndexController($renderer);
};

$container[\Leka\Controller\AuthController::class] = function($c) {
    $renderer = $c->get("renderer"); // retrieve the 'renderer' from the container
    return new AuthController($renderer);
};

$container[\Leka\Controller\ServiceController::class] = function($c) {
    $renderer = $c->get("renderer"); // retrieve the 'renderer' from the container
    return new ServiceController($renderer);
};