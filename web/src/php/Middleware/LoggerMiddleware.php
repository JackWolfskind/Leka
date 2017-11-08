<?php

namespace Leka\Middleware;

class LoggerMiddleware {

    /**
     * Example middleware invokable class
     *
     * @param  \Psr\Http\Message\ServerRequestInterface $request  PSR7 request
     * @param  \Psr\Http\Message\ResponseInterface      $response PSR7 response
     * @param  callable                                 $next     Next middleware
     *
     * @return \Psr\Http\Message\ResponseInterface
     */
    public function __invoke($request, $response, $next) {
        // Sample log message
        $container = $app->getContainer();
        $this->logger->info("Call: " . $request->getMethod());
        $response = $next($request, $response);
        $this->logger->info("Reponse: ", $response);
        return $response;
    }

}
