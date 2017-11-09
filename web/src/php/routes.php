<?php

namespace Leka;

use Leka\Middleware\LoggerMiddleware;
use Leka\Middleware\LoginMiddleware;
use Slim\Http\Request;
use Slim\Http\Response;

// Routes

$app->get('/', function (Request $request, Response $response, array $args) {

// Render index view
            return $this->renderer->render($response, 'index.twig', $args);
        })->add($container[LoggerMiddleware::class])
        ->add($container[LoginMiddleware::class]);

$app->post('/login', function (Request $request, Response $response, array $args) {

// Render index view
    $password = $request->getAttribute("password");
    $username = $request->getAttribute("username");
    
    $ldapconn = ldap_connect("ldap-dc1.gso") or die("Could not connect to LDAP server.");
    ldap_set_option($ldapconn, LDAP_OPT_PROTOCOL_VERSION, 3);
    $ldapbind = ldap_bind($ldapconn,"gso\\".$username,$password) or die("anonymes LDAP bind fehlgeschlagen...");
    $ldap_search = ldap_search($ldapconn, "ou=benutzer,dc=gso-neu", "uid=" . $username);
    $info = ldap_get_entries($ldapconn, $ldap_search);
    echo "Daten für " . $info["count"] . " Items gefunden:<br />";



//    return $this->renderer->render($response, 'index.twig', $args);
})->add($container[LoggerMiddleware::class]);

