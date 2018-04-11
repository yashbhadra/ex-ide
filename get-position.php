<?php
/**
 * Created by PhpStorm.
 * User: yash
 * Date: 08/03/18
 * Time: 12:15 AM
 */
include('DatabaseConstants.php');
include('Crud.php');

class Position
{


    public $co;
    public $array;

    const TOPLEVELDOMAIN="http://192.168.0.122/map";

    public $jsonString=array();



    function getPosition($distances)
    {
        $temp_x=100;
        $temp_y=100;
        /* $distances=Json_decode($distmac,"true");
         $file=Position::TOPLEVELDOMAIN."/information.map";
         $contents = file_get_contents($file);

         $array=Json_decode($contents,"true");

         for($i=0;$i<3;$i++) {
             $pattern = $distances['$i']['mac'];

             for ($j = 0;$j<count($array);$j++) {
                 if ($array[$j]['mac'] == $distances[$i]['mac']) {

                     $x1=$array['$j']['x'];
                     $x2=$array['$j']['y'];

                 }
                 $co[]=$x1;
                 $co[]=$x2;

             }*/
        $json='{';
        $temp_arr=array($temp_x,$temp_y);
        $json.=Json_encode($temp_arr);
        $json.='}';
        return $json;
    }







}

$m=new Position();
$item=$m->getPosition($_GET['distances']);
echo $item;

/* $host = "192.168.0.164";
 $port = 8080;
// No Timeout
 set_time_limit(0);
 $socket = socket_create(AF_INET, SOCK_STREAM, 0) or die("Could not create socket\n");

 $result = socket_connect($socket, $host, $port) or die("Could not connect toserver\n");*/










//echo $crud->getDateTime();


