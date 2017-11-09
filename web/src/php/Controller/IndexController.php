<?php

namespace Leka\Controller;

use Slim\Http\Request;
use Slim\Http\Response;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of IndexController
 *
 * @author cziermann
 */
class IndexController extends BaseControlller {

    protected $service;


    public function __construct(\Slim\Views\Twig $renderer, \Monolog\Logger $logger, $service) {
        $this->service = $service;
        parent::__construct($renderer, $logger);
    }
    
    public function index($request, $response, $args) {
        
        
        $result = $this->service->get("lesson/list", []);
        #if ($result->info->http_code == 200) {
            //var_dump($result->decode_response());
        #}
        $args["lessons"] = $result->decode_response();
        return $this->renderer->render($response, 'index.twig', $args);
    }

}
