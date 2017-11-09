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

    public function index($request, $response, $args) {
        return $this->renderer->render($response, 'index.twig', $args);
    }

}
