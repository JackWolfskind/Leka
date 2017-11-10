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
        $heute= getdate();
        $kw=date("W");
        $jahr=$heute["year"];
        
        $t_montag = strtotime("{$jahr}-W{$kw}"); 
        $montag=date("d.m.Y", $t_montag);
        
        $t_dienstag = strtotime("{$jahr}-W{$kw}-2"); 
        $dienstag=date("d.m.Y", $t_dienstag);
        
        $t_mittwoch = strtotime("{$jahr}-W{$kw}-3"); 
        $mittwoch=date("d.m.Y", $t_mittwoch);
        
        $t_donnerstag = strtotime("{$jahr}-W{$kw}-4"); 
        $donnerstag=date("d.m.Y", $t_donnerstag);
        
        $t_freitag = strtotime("{$jahr}-W{$kw}-5"); 
        $freitag=date("d.m.Y", $t_freitag);
        
        #Template Daten Tag
        $daten_template="<thead><tr> "
                . "<th>&nbsp;</th>"
                . "<th>Lehrer</th>"
                . "<th>Klasse</th>"
                . "<th>Thema</th>"
                . "</tr></thead>"
                . "<tbody>"
                . "<tr><td>1/2</td><td colspan=3></td></tr>"
                . "<tr><td>3/4</td><td colspan=3></td></tr>"
                . "<tr><td>5/6</td><td colspan=3></td></tr>"
                . "<tr><td>7/8</td><td colspan=3></td></tr>"
                . "<tr><td>9/10</td><td colspan=3></td></tr>"
                . "</tr></tbody>";
                
        $daten_montag=$daten_template;
        $daten_dienstag=$daten_template;
        $daten_mittwoch=$daten_template;
        $daten_donnerstag=$daten_template;
        $daten_freitag=$daten_template;

        
        $woche=date("W");
                
        $args["Daten"]=array($woche, $montag, $dienstag, $mittwoch, $donnerstag, $freitag,$daten_montag,$daten_dienstag,$daten_mittwoch,$daten_donnerstag,$daten_freitag);
        
        return $this->renderer->render($response, 'index.twig', $args);
    }

}
