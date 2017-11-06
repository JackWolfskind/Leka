<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Gso\Leka\Lib;

use Pimple\Container as PimpleContainer;
use Symfony\Component\Routing\RouteCollection;
use Nstrohe\Klarheit\Lib\Route;

/**
 * Description of Container
 *
 * @author cziermann
 */
class Container {

    private static $container = null;

    public function __construct($path) {
        $array = include_once $path;
        self::$container = new PimpleContainer();
        self::$container[] = $array;
    }

    function get($id) {
        self::$container->offsetGet($id);
    }

    function set($id, $value) {
        self::$container->offsetSet($id, $value);
    }

}
