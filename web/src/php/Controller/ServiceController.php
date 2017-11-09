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
class ServiceController extends BaseControlller {

    public function get(Response $request, Request $response, $args) {
        // Render index view
        $service = $this->get("service");
        $result = $service->get("test2", ['test' => "TEST"]);
        if ($result->info->http_code == 200) {
            var_dump($result->decode_response());
        }
        $body = $response->getBody();
        $body->write('test');
        return $response;
    }

}
