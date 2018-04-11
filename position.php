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




    function uploadMap($filename)
    {

        $target_path = TOPLEVELDOMAIN( $_FILES[$filename]['name']);

        if(move_uploaded_file($_FILES['fileToUpload'][$filename], $target_path)) {
            echo "File uploaded successfully!";
        } else{
            echo "Sorry, file not uploaded, please try again!";
        }

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

    $m=new Position();
    $item=$m->getMap();
    echo $item;

   /* $host = "192.168.0.164";
    $port = 8080;
// No Timeout
    set_time_limit(0);
    $socket = socket_create(AF_INET, SOCK_STREAM, 0) or die("Could not create socket\n");

    $result = socket_connect($socket, $host, $port) or die("Could not connect toserver\n");*/










//echo $crud->getDateTime();


