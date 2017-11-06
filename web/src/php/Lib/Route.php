<?php


namespace Gso\Leka\Lib;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Route as SymfonyRoute;
/**
 * Description of Route
 *
 * @author cziermann
 */
class Route {

    function __construct(AppKernel $kernel) {
        $request = Request::createFromGlobals();
        $response = $kernel->handle($request);
        $response->send();
        $kernel->terminate($request, $response);
    }

}
