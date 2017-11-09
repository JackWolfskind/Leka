<?php

namespace Leka\Middleware;

use Exception;
use Monolog\Logger;
use Psr\Http\Message\ResponseInterface;
use Psr\Http\Message\ServerRequestInterface;
use Slim\Http\Request;
use Slim\Http\Response;

class LoggerMiddleware {

    /**
     * Example middleware invokable class
     *
     * @param  ServerRequestInterface $request  PSR7 request
     * @param  ResponseInterface      $response PSR7 response
     * @param  callable                                 $next     Next middleware
     *
     * @return ResponseInterface
     */
    private $logger;

    public function __construct(Logger $logger) {
        $this->logger = $logger;
    }

    public function __invoke(Request $request, Response $response, $next) {
        // Sample log message
        try {
            $this->logger->info("Call: " . $request->getUri());
            $response = $next($request, $response);
            $this->logger->info("Reponse: ", [
                "code" => $response->getStatusCode(),
                "response" => $response->getBody()
            ]);
        } catch (Exception $e) {
            $this->logger->error($e->getMessage(), [
                "error-code" => $e->getCode(),
                "error-stack" => $e->getTraceAsString(),                
            ]);
            return;
        }
        return $response;
    }

}
