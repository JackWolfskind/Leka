<?php

namespace Leka;

// Routes

$app->get('/', \Leka\Controller\IndexController::class . ":index")
        ->add($container[\Leka\Middleware\LoggerMiddleware::class])
        ->add($container[\Leka\Middleware\LoginMiddleware::class])
        ->setName("index");
$app->post('/login', \Leka\Controller\AuthController::class . ":login")
        ->add($container[\Leka\Middleware\LoggerMiddleware::class])
        ->setName("login");
$app->get('/service', \Leka\Controller\ServiceController::class . ":get")
        ->add($container[\Leka\Middleware\LoggerMiddleware::class])
        ->setName("service");
$app->get('/', function (Request $request, Response $response, array $args) {

// Render index view
            return $this->renderer->render($response, 'index.twig', $args);
        })->add($container[LoggerMiddleware::class])
        ->add($container[LoginMiddleware::class]);
