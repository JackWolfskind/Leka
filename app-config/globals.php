<?php
use Symfony\Component\HttpKernel\HttpKernel;

$container = [];

$container["paths"] = [
    'config' => __DIR__ . '../../../app-config/config.json',
    'routes' => __DIR__ . '../../../app-config/routes.xml',
    'base' => '/leka'
];

function getConfig($container) {
    return json_decode(file_get_contents($container['path']['config']));
}

$container["config"] = getConfig($container);

$container["twig"] = function () {
    $loader = new Twig_Loader_Filesystem(__DIR__ . "../Views/");
    $twig = new Twig_Environment($loader);
    return $twig;
};

$container["routeCollection"] = function () {
    $collection = new RouteCollection();
    return $collection;
};

$container["route"] = function () {
    $route = new Route();
    return $route;
};


$container[HttpKernel::class] = function ($container) {
    return new HttpKernel(
        $container[EventDispatcher::class],
        $container[ControllerResolver::class],
        $container[RequestStack::class],
        $container[ArgumentResolver::class]
    );
};

return $container;

