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
        $password = $request->getParam("password");
        $username = $request->getParam("username");
        #$newPass = iconv( 'UTF-8', 'UTF-16LE', $password );
        $ldapconn = ldap_connect("ldap-dc1.gso") or die("Could not connect to LDAP server.");
        ldap_set_option($ldapconn, LDAP_OPT_PROTOCOL_VERSION, 3);
        ldap_set_option($ldapconn, LDAP_OPT_REFERRALS, 0);
        $bind = @ldap_bind($ldapconn);
        $res_id = ldap_search($ldapconn, "ou=fia51,ou=it,ou=schueler,ou=benutzer,dc=gso-neu", "uid=$username");
        $entry_id = ldap_first_entry($ldapconn, $res_id);
        $user_dn = ldap_get_dn($ldapconn, $entry_id);
        $link_id = ldap_bind($ldapconn, $user_dn, $password);
        if ($link_id) {
            $_SESSION["teacher"] = $username;
        }
    }

    public function logout(Response $request, Request $response, $args) {
        //add code here
    }

}
