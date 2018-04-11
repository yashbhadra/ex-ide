<?php
/**
 * Created by PhpStorm.
 * User: yash
 * Date: 08/03/18
 * Time: 12:15 AM
 */
include('DatabaseConstants.php');
include('Crud.php');

class Map
{


    public $co;
    public $array;

    const TOPLEVELDOMAIN="http://192.168.0.122/map";

    public $jsonString=array();


    function getMap()
    {
        //$file=Position::TOPLEVELDOMAIN."/information.map";
        $url="http://192.168.0.122/museum/map/information.map";
        //$contents = file_get_contents($url);
       $contents=file_get_contents($url);
        //$array=Json_encode($contents,"true");

        return $contents;
echo $contents;
   //return $jsonString; calculate distance and return
    }






    function file_get_contents_curl($url) {
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_HEADER, 0);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1); //Set curl to return the data instead of printing it to the browser.
    curl_setopt($ch, CURLOPT_URL, $url);
    $data = curl_exec($ch);
    curl_close($ch);
    return $data;
}

}

    $m=new Map();
    $item=$m->getMap();
    echo $item;

   /* $host = "192.168.0.164";
    $port = 8080;
// No Timeout
    set_time_limit(0);
    $socket = socket_create(AF_INET, SOCK_STREAM, 0) or die("Could not create socket\n");

    $result = socket_connect($socket, $host, $port) or die("Could not connect toserver\n");*/










//echo $crud->getDateTime();


