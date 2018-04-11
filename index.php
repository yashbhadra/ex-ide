<?php
/**
 * Created by PhpStorm.
 * User: yash
 * Date: 08/03/18
 * Time: 12:15 AM
 */
include('DatabaseConstants.php');
include('Crud.php');

class Museum implements DatabaseConstants
{

    public $crud;
    public $id;
    public $item;
    public $conn;
    public $imageURL;
    public $audioURL;
    const TOPLEVELDOMAIN="http://192.168.0.122/museum";
    public $jsonString=array();


    function  __construct()
    {

        $this->conn = mysqli_connect(DatabaseConstants::SERVER, DatabaseConstants::USERNAME,DatabaseConstants::PASSWORD,DatabaseConstants::DATABASE);
        if (!$this->conn) {
            echo "Connection error";
        }




    }




    function getAll()
    {

        $crud=Crud::getinstance(DatabaseConstants::SERVER,DatabaseConstants::USERNAME,DatabaseConstants::PASSWORD,DatabaseConstants::DATABASE);




        $sql ="select exhibit_info.exhibit_id,exhibit_info.exhibit_name,exhibit_info.exhibit_desc,exhibit_image.image,exhibit_url.url from exhibit_info inner join exhibit_image on exhibit_info.exhibit_id=exhibit_image.exhibit_id inner join exhibit_url on exhibit_info.exhibit_id=exhibit_url.exhibit_id where 1=1";



        $result = mysqli_query($this->conn, $sql);


        $myArray = array();
        $jsonString='{'.'"exhibits"'.':[';
        if (mysqli_num_rows($result) > 0) {
            while ($row = $result->fetch_assoc())//Loop to copy the contents of row to an array
            {
                $image_url=$this->getImageURL($row['exhibit_id']);
                $audio_url=$this->getAudioURL($row['exhibit_id']);

                $item = Json_encode($row);
                $final=substr($item,0,-1);

                $jsonString .=$final.",".'"exhibit_image":'.'"'.$image_url.'"'.",".'"exhibit_audio_url":'.'"'.$audio_url.'"'.'},';



            }
           // $item = Json_encode($myArray);
            //$jsonString.=substr($jsonString,0,-1);
            //$finaljson.="}]";
            //$jsonString.=substr($jsonString,0,count($jsonString)-1);
            $jsonString=substr_replace($jsonString, '', strlen($jsonString)-1, 1);
            $jsonString.=']}';
            return $jsonString;
            //return $myArray;

        }
        else{
            echo "0 results";
            return;
        }


        //$jsonString = '{'json_encode($myArray)."}";
       // $item = Json_encode($myArray);
        //$final=substr($item,1,-2);




        //return $jsonString;
    }

    function getDetails($id)
    {

        $crud=Crud::getinstance(DatabaseConstants::SERVER,DatabaseConstants::USERNAME,DatabaseConstants::PASSWORD,DatabaseConstants::DATABASE);




        $sql ="select exhibit_info.exhibit_id,exhibit_info.exhibit_name,exhibit_info.exhibit_desc,exhibit_image.image,exhibit_url.url from exhibit_info inner join exhibit_image on exhibit_info.exhibit_id=exhibit_image.exhibit_id inner join exhibit_url on exhibit_info.exhibit_id=exhibit_url.exhibit_id where exhibit_info.exhibit_id=".$id.";";



        $result = mysqli_query($this->conn, $sql);


        $myArray = array();
        if (mysqli_num_rows($result) > 0) {
            while ($row = $result->fetch_assoc())//Loop to copy the contents of row to an array
            {
                $myArray[] = $row;
            }

        }
        else{
            echo "0 results";
            return;
        }
        $image_url=$this->getImageURL($id);
        $audio_url=$this->getAudioURL($id);

        //$jsonString = '{'json_encode($myArray)."}";
        $item = Json_encode($myArray);
        $final=substr($item,1,-2);

        $jsonString =$final.",".'"exhibit_image":'.'"'.$image_url.'"'.",".'"exhibit_audio_url":'.'"'.$audio_url.'"'."}";


        return $jsonString;


    }
    function getImageUrl($id){
        //define("TOPLEVELDOMAIN","localhost:63343/museum/");
        if($id==-1){
            return "";
        }
        $image = "images/";
        if(file_exists($image.$id.".jpg")){//check for jpg
            $image=$id.".jpg";
            return Museum::TOPLEVELDOMAIN."/images/".$image;
        }else if(file_exists($image.$id.".jpeg")){//check for jpeg
            $image=$id.".jpeg";
        }
        else if(file_exists($image.$id.".png")){//check for png
            $image=$id.".png";
        }
        else return "";
        return Museum::TOPLEVELDOMAIN."/images/".$image;
    }
    function getAudioUrl($id)
    {
        //define("TOPLEVELDOMAIN","localhost:63343/museum/");
        if($id==-1)
        {
            return "";
        }
        $path="audio/";
        if(file_exists($path.$id.".mp3"))
        {
            $audio=$id.".mp3";
            return Museum::TOPLEVELDOMAIN."/audio/".$audio;
        }
        else
        {
            return "";
        }



    }

}
if(isset($_GET['id']))
{
    $m=new Museum();
    if($_GET['id'] == -1) {
        $item=$m->getAll();
        echo $item;
    }
    else
    {
        $item=$m->getDetails($_GET['id']);
        echo $item;
    }


}



//echo $crud->getDateTime();


