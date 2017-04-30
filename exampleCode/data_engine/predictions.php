<?php
  /*
  $dayOfWeek = array(
    array("Sunday", 1000000, 0, 0),
    array("Monday", 1000000, 0, 0),
    array("Tuesday", 1000000, 0, 0),
    array("Wednesday", 1000000, 0, 0),
    array("Thursday", 1000000, 0, 0),
    array("Friday", 1000000, 0, 0),
    array("Saturday", 1000000, 0, 0)
  );
  */

  /*
   * This is to store data in the future temporarily
   */

  $weightStart = 999999;
  $weightEnd = 999999;
  class Weight{
    private $date;
    private $startWeight;
    private $weight;
    private $endWeight;
    private $timeStamp;
    /*
    public function __construct(){
      $this->startWeight = 0;
      $this->weight = 0;
      $this->endWeight = 0;
      $this->timeStamp = '';
    } */

    public function __construct($date, $startWeight, $weight, $endWeight, $timeStamp){
      $this->date = $date;
      $this->startWeight = $startWeight;
      $this->weight = $weight;
      $this->endWeight = $endWeight;
      $this->timeStamp = $timeStamp;
    }

    public function getDate(){
      return $this->date;
    }

    public function getWeight(){
      return $this->weight;
    }
    public function getTimeStamp(){
      return $this->$timeStamp;
    }

    public function setWeight($weight){
      $this->weight = $weight;
    }
    public function setTimeStamp($timeStamp){
      $this->$timeStamp = $timeStamp;
    }
  }
  class Bin {
    public $number;
    public $capacity;
    public $currWeight;
    public $maxWeight;
    public $threshold;
    public $weightHistory;

    public function __construct($number, $capacity, $maxWeight, $threshold){
      $this->number = $number;
      $this->capacity = $capacity;
      $this->currWeight = 0;
      $this->maxWeight = $maxWeight;
      $this->threshold = $threshold;
      $this->weightHistory = array();
    }

    public function getBinNumber() {
      echo $this->number;
      return $this->number;
    }

    public function getWeight($index){
      $arr_length = count($weightHistory);
      return $weightHistory[$arr_length];
    }
  }



  function testBinClass(){
    $bin1 = new Bin(1, 100, 100, 20);
    echo $bin1->getBinNumber();
  }

  /*
   * This is a test function to work with dates
   */

  /*
  function prediction(){
    testBinClass();
    $hub = array("0", "1");
    $bins = array("0" , "1", "2", "3", "4", "5");
    $dates = array("Monday", "Tuesday", date("m/d/y"));

    $arr_length = count($dates);
    for ($i = 0; $i < $arr_length ; $i++){
      echo $dates[$i];
    }

    echo "Today is " . date("m/d/y") . "<br>";
    echo "Prediction starting";
    $stack = array("orange", "banana");
    array_push($stack, "apple", "raspberry");
    print_r($stack);
    echo "damn";
    echo "shit";
  }
   $link = mysqli_connect("localhost", "root", "", "stock");

    // Check connection
    if($link === false){
        die("ERROR: Could not connect. " . mysqli_connect_error());
    }
       $sql="SELECT new_weight from restaurant_name_modified where date_entered between '2017-03-10 18:00:00' and '2017-03-10 19:00:00'";
      if($result = mysqli_query($link, $sql)){
        if(mysqli_num_rows($result) > 0){
          while($row = mysqli_fetch_array($result)){
            echo $row['new_weight']."</br>";
          }
          mysqli_free_result ($result);
        }else{
          echo "No records matching your query were found.";
        }
        mysqli_close($link);
        }
   */
  /*
   * IMPORTANT: STILL NEEDS THE SQL STATEMENTS FROM ANDY
   * Description: This function returns all calculations for consumption, bin increases, for a specific time period of the day
   *              To get the whole day consumption, call this function 3 times for BREAKFAST, LUNCH, and DINNER and add the values together
   *
   * Pre-Condition:
   *                $timeFrame - Takes in a time frame to pull the data from the SQl, either breakfast, lunch, or dinner
   *                $option  - 0 denotes getting the consumption with the refilling of the bins which is both the increases and the decreases
   *                           1 denotes grabbing only the decreases, this is your consumption of materials for that bin
   *                           2 denotes how much is being put into the bin for time period
   *                $bin - This option is which bin you're grabbing the data from in the SQL statement
   * Post-Condition:
   *                Returns the set type of consumption you want for that time period.
   *
   */
  function getConsumption($date, $option, $bin, $timeFrame){
    $weights = array();
    $testing = true;
    if($timeFrame == "BREAKFAST"){
      // Set weights equal to Select statement to get time from OPENING to END OF BREAKFAST
    }
    else if ($timeFrame == "LUNCH"){
      // Set weights equal to Select statement to get time from lunch to END Of lunch
    }
    else if ($timeFrame == "DINNER" && $testing == false){
      // Set weights equal to Select statement to get time from Dinner to END OF Dinner
      $link = mysqli_connect("localhost", "root", "", "stock");

      // Check connection
      if($link === false){
        die("ERROR: Could not connect. " . mysqli_connect_error());
      }
      $sql="SELECT new_weight from restaurant_name_modified where date_entered between '2017-03-10 18:00:00' and '2017-03-10 19:00:00'";
      if($result = mysqli_query($link, $sql)){
        if(mysqli_num_rows($result) > 0){
          while($row = mysqli_fetch_array($result)){
            array_push($weights, $row['new_weight']);
            //echo $row['new_weight']."</br>";
          }
          mysqli_free_result ($result);
        }else{
          echo "No records matching your query were found.";
        }
        mysqli_close($link);

      }


    } else {

      global $weightStart;
      global $weightEnd;
      $randNum = $weightStart;
      for ($i = 0; $i < 500; $i++){
        $randNum = $weightStart - ($i * rand(0,500));
        array_push($weights, $randNum);
      }
      $weightStart = $randNum;
      $weightEnd = $randNum;
    }
    $arr_length = count($weights);
    $counter = 0;
    $total = 0;
    $decrease = 0;
    $increase = 0;
    $prevWeight = $weights[0];
    for ($i = 0; $i < $arr_length; $i++){
      $counter++;
      if($prevWeight >= $weights[$i]){
        $decrease = $decrease + ($prevWeight - $weights[$i]);
      }
      else {
        $increase = $increase + ($weights[$i] - $prevWeight);
      }
      $prevWeight = $weights[$i];
    }
    $total = $increase - $decrease;
    if ($total < 0){
      echo "Decreased by " . $decrease . "<br>";
    }
    else if ($total > 0){
      echo "Increase by " . $increase . "<br>";
    }
    else {
      echo "No consumption for time period" . "<br>";
    }
    switch($option){
      case 0:
        echo "Returning total: " . $total . "<br>";
        return $total;
        break;
      case 1:
        echo "Returning decrease: " . $decrease . "<br>";
        return $decrease;
        break;
      case 2:
        echo "Returning increase: " . $decrease . "<br>";
        return $increase;
        break;
      case 3:
        echo "Returning array" . "<br>";
        return $weights;
        break;
      default:
        return $total;
    }
    //return $weights;
  }

  $dayOfWeek = array(
    new Weight("Sunday", 999999, 999999, 999999, ''),
    new Weight("Monday", 999999, 999999, 999999, ''),
    new Weight("Tuesday", 999999, 999999, 999999, ''),
    new Weight("Wednesday", 999999, 999999, 999999, ''),
    new Weight("Thursday", 999999, 999999, 999999, ''),
    new Weight("Friday", 999999, 999999, 999999, ''),
    new Weight("Saturday", 999999, 999999, 999999, '')
  );
  /*
   * Description: Gets the total consumption for the day
   * Pre-Condition: Select the day, option, and bin
   * Post-Condition: Return the consumption in grams. Positive is increase in inventory, negative is decrease
   */
  function getDayConsumption($date,$option, $bin){
    // resolve date
    $dayConsumption = 0;
    // Call getConsumption 3 times

    $dayConsumption = getConsumption($date,$option,$bin, "BREAKFAST") + getConsumption($date,$option,$bin, "LUNCH") + getConsumption($date,$option,$bin, "DINNER");
  }

  function testConsumption(){
    global $dayOfWeek;
    date_default_timezone_set('America/New_York');
    $currentDate = date('Y-m-d h:m:s');
    echo "Today's date: " . $currentDate . "</br>";
    /*
    echo $currentDate . "</br>";
    echo date('w', strtotime($currentDate)) . "</br>";
    $incr = strtotime("+1 day", strtotime($currentDate));
    $incrementedDate = date("Y-m-d h:m:s", $incr);

    echo $incrementedDate . "</br>";
    echo strtotime($currentDate) . "</br>";
     */
    $incrementedDate = $currentDate;
    global $weightStart, $weightEnd;
    for ($i = 0; $i < 7 ; $i++){
      echo "Beginning weight: " . $weightStart . "</br>";
      $incr = strtotime("+". $i ." day", strtotime($currentDate));
      $incrementedDate = date("Y-m-d h:m:s", $incr);
      $day = date('w', strtotime($incrementedDate));
      echo date('D', $incr) . ": ";
      echo $incrementedDate . ": ";
      $consumption = getConsumption($incrementedDate, 0, 1, "DINNER");
      echo $consumption . "</br>";
      //echo $consumption . "</br>";

      echo intval($day) . "</br>";
      //var_dump($dayOfWeek[0]);
      $dayOfWeek[$day]->setWeight($consumption);
      echo "Weight: " . $dayOfWeek[$day]->getWeight() . "</br>";
      echo "End weight: " . $weightEnd . "</br>";

      if ($weightEnd < 250000){
        echo "Weight is less than 25%, reorder!</br>";
      }
    }

    echo "</br></br></br>";
    echo "</br></br></br>";
    echo "</br></br></br>";
    echo "</br></br></br>";
    echo "</br></br></br>";
    for ($i = 0; $i < 7 ; $i++){
      echo $dayOfWeek[$i]->getDate() . ": " . " Consumption: " . $dayOfWeek[$i]->getWeight() . "</br>";
    }
    echo "</br></br></br>";
    predictReorderPoint($incrementedDate);
    //printArray($consumption);
  }


  function predictReorderPoint($currentDate){
    echo "Predicting! </br>";
    $incr = strtotime("+1 day", strtotime($currentDate));
    $currentDate = date("Y-m-d h:m:s", $incr);
    $reorder = false;
    $i = 0;

    global $dayOfWeek, $weightStart, $weightEnd;
    echo "Leftover grams: " . $weightEnd . "</br>";
    while ($reorder == false){
      $incr = strtotime("+". $i ." day", strtotime($currentDate));
      $currentDate = date("Y-m-d h:m:s", $incr);
      $day = date('w', strtotime($currentDate));
      echo $dayOfWeek[$i % 7]->getDate() . ": ";
      echo $currentDate . ":</br>";
      if ($weightEnd + $dayOfWeek[$i % 7]->getWeight() < 100000){
        $weightEnd = $weightEnd + $dayOfWeek[$i % 7]->getWeight();
        echo "Reorder as weight will drop to: " . $weightEnd . "</br>";
        $reorder = true;
      } else {
        $endWeight = $weightEnd + $dayOfWeek[$i % 7]->getWeight();
        echo "No reorder: ". $weightEnd . "</br>";
      }
      $i++;
    }
  }


  function printArray($tempArray){
    echo "About to print array";
    $arr_length = count($tempArray);
    for ($i = 0; $i < $arr_length ; $i++){
      echo "Item: ";
      echo $tempArray[$i] . "<br>";
    }
  }
  //prediction();
  testConsumption();

?>
