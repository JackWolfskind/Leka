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
    $password = $request->getParam("password");
    $username = $request->getParam("username");
    #$newPass = iconv( 'UTF-8', 'UTF-16LE', $password );
    $ldapconn = ldap_connect("ldap-dc1.gso") or die("Could not connect to LDAP server.");
    ldap_set_option($ldapconn, LDAP_OPT_PROTOCOL_VERSION, 3);
    ldap_set_option($ldapconn, LDAP_OPT_REFERRALS, 0);
    $bind=@ldap_bind($ldapconn);
    $res_id = ldap_search($ldapconn,"ou=fia51,ou=it,ou=schueler,ou=benutzer,dc=gso-neu","uid=$username"); 
    $entry_id = ldap_first_entry($ldapconn, $res_id);
    $user_dn = ldap_get_dn($ldapconn, $entry_id);
    $link_id = ldap_bind($ldapconn, $user_dn, $password);
    if($link_id){$_SESSION["teacher"]=$username;}
    
   


//    return $this->renderer->render($response, 'index.twig', $args);
})->add($container[LoggerMiddleware::class]);

