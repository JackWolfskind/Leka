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
class AuthController extends BaseControlller {

    public function login(Response $request, Request $response, $args) {
        // Render index view
        $password = $request->getAttribute("password");
        $username = $request->getAttribute("username");

        $ldapconn = ldap_connect("ldap-dc1.gso") or die("Could not connect to LDAP server.");
        ldap_set_option($ldapconn, LDAP_OPT_PROTOCOL_VERSION, 3);
        $ldapbind = ldap_bind($ldapconn, "gso\\" . $username, $password) or die("anonymes LDAP bind fehlgeschlagen...");
        $ldap_search = ldap_search($ldapconn, "ou=benutzer,dc=gso-neu", "uid=" . $username);
        $info = ldap_get_entries($ldapconn, $ldap_search);
        echo "Daten für " . $info["count"] . " Items gefunden:<br />";
    }

    public function logout(Response $request, Request $response, $args) {
        //add code here
    }

}
