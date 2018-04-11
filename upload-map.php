
<?php
/**
 * Created by PhpStorm.
 * User: yash
 * Date: 08/03/18
 * Time: 12:15 AM
 */
include('DatabaseConstants.php');
include('Crud.php');

class Uploadmap
{


    public $co;
    public $array;

    const TOPLEVELDOMAIN="http://192.168.0.122/map";

    public $jsonString=array();



    function upload($filename)
    {
        $uploaddir = "http://192.168.0.122/museum/map/information.map";
        $uploadfile = $uploaddir($_FILES['userfile']['name']);

        echo '<pre>';
        if (move_uploaded_file($_FILES['userfile']['tmp_name'], $uploadfile)) {
            echo "File is valid, and was successfully uploaded.\n";
        } else {
            echo "Possible file upload attack!\n";
        }

        echo 'Here is some more debugging info:';
        print_r($_FILES);

        print "</pre>";
    }







}

$m=new Uploadmap();
$item=$m->upload($_GET['file'],$_GET['name']);


/* $host = "192.168.0.164";
 $port = 8080;
// No Timeout
 set_time_limit(0);
 $socket = socket_create(AF_INET, SOCK_STREAM, 0) or die("Could not create socket\n");

 $result = socket_connect($socket, $host, $port) or die("Could not connect toserver\n");*/










//echo $crud->getDateTime();


