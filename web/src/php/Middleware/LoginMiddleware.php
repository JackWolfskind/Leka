<?php

namespace Leka\Middleware;

use Exception;
use Psr\Http\Message\ResponseInterface;
use Psr\Http\Message\ServerRequestInterface;
use Slim\Http\Request;
use Slim\Http\Response;
use Slim\Views\Twig;

class LoginMiddleware {

    private $renderer;

    public function __construct(Twig $renderer) {
        $this->renderer = $renderer;
    }

    /**
     * Example middleware invokable class
     *
     * @param  ServerRequestInterface $request  PSR7 request
     * @param  ResponseInterface      $response PSR7 response
     * @param  callable                                 $next     Next middleware
     *
     * @return ResponseInterface
     */
    public function __invoke(Request $request, Response $response, $next) {
        // Sample log message
        try {
            if (!isset($_SESSION['teacher'])) {

                $response = $this->renderer->render($response, "anmeldung.twig", [
                    "loginpath" => "/login",
                    "last_path" => $request->getUri()
                ]);
            } else {
                $response = $next($request, $response);
            }
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
