<?php

namespace Leka\Controller;

use Slim\Http\Request;
use Slim\Http\Response;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Leka\Controller;

/**
 * Description of BaseControlller
 *
 * @author cziermann
 */
class BaseControlller {

    protected $renderer;

    public function __construct(\Slim\Views\Twig $renderer) {
        $this->renderer = $renderer;
    }

}
