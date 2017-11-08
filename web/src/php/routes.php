<?php

namespace Leka;

use Leka\Middleware\LoggerMiddleware;
use Slim\Http\Request;
use Slim\Http\Response;

// Routes

$app->get('/', function (Request $request, Response $response, array $args) {

// Render index view
    return $this->renderer->render($response, 'index.twig', $args);
})->add($container[LoggerMiddleware::class]);

