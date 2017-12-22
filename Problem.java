// Person class
class Person{
  String id;
  Person(String id){
    this.id=id;
  }
}


// Room class
class Room{
  int id;
  Person person;
  
  Room(int id,Person person){
    this.id = id;
    this.person = person;
  }
}


// Game class
class Game{
  Room[] status; // The status of the game is a list of rooms with people inside
  Game(){
    
    // Define the persons
    Person personA = new Person("A");
    Person personB = new Person("B");
    Person personC = new Person("C");
    Person personD = new Person("D");
    Person personE = new Person("E");
    Person personX = new Person("X");   // Person X denotes the empty room
    Person personG = new Person("G");
    
    // Define the rules
    Room room0 = new Room(0,personG);
    Room room1 = new Room(1,personA);
    Room room2 = new Room(2,personX);
    Room room3 = new Room(3,personB);
    Room room4 = new Room(4,personC);
    Room room5 = new Room(5,personD);
    Room room6 = new Room(6,personE);
    this.status = new Room[]{room0,room1, room2, room3 ,room4, room5, room6};
    }
    
  // Based on the status of the game, we find the available rooms
   String[] findRules(){
     if (status[2].person.id == "X"){
       String[] rules = new String[]{"ud","cl","ar"};
       return rules;
     }else{
       String[] rules = new String[]{"ud","cr","al"};
       return rules;
     }
   }
   
  // We define and apply the rules (intuitive meaning explain in readme) to change the game status (i.e. swapping people in rooms)
   void applyRule(String rule){
       if (rule == "ud"){
         
         String temp = this.status[5].person.id;
         this.status[5].person.id = this.status[2].person.id ;
         this.status[2].person.id = temp;
         
       }else if(rule == "cl"){
         
         String temp1 = this.status[1].person.id;
         String temp2 = this.status[2].person.id;
         String temp4 = this.status[4].person.id;
         String temp5 = this.status[5].person.id;
         
         this.status[2].person.id = temp1;
         this.status[5].person.id = temp2;
         this.status[1].person.id = temp4;
         this.status[4].person.id = temp5;
         
       }else if(rule == "ar"){
         
          String temp2 = this.status[2].person.id;
          String temp3 = this.status[3].person.id;
          String temp5 = this.status[5].person.id;
          String temp6 = this.status[6].person.id;
         
          this.status[5].person.id = temp2;
          this.status[2].person.id = temp3;
          this.status[6].person.id = temp5;
          this.status[3].person.id = temp6;
         
       }else if(rule == "cr"){
         
          String temp2 = this.status[2].person.id;
          String temp3 = this.status[3].person.id;
          String temp5 = this.status[5].person.id;
          String temp6 = this.status[6].person.id;
         
          this.status[3].person.id = temp2;
          this.status[6].person.id = temp3;
          this.status[2].person.id = temp5;
          this.status[5].person.id = temp6;
         
       }else if(rule == "al"){
         
          String temp1 = this.status[1].person.id;
          String temp2 = this.status[2].person.id;
          String temp4 = this.status[4].person.id;
          String temp5 = this.status[5].person.id;
         
          this.status[4].person.id = temp1;
          this.status[1].person.id = temp2;
          this.status[5].person.id = temp4;
          this.status[2].person.id = temp5;
         
       }
   }


  // Checks if we have successfully swapped persons B and E
  Boolean checkSwap(){
    Boolean res = false;
    if((this.status[3].person.id == "E")&&(this.status[6].person.id == "B")){
      res = true;
    }
    return res;
  }   
}

// Running our exhaustive test for all solutions
void test(){
  
  // List of solutions and moves made for each respectively
  long[] solutions;
  int[] moveslist;
  
  for(int i=0;i<pow(3,11)-1;i++){
    // We found a solution in 11 moves, so 3^11 was our bound
    int moves = 0;
    Game game = new Game();
    // Exhaust through all base 3 numbers of length 11 digits (each digit corresponding to a rule, hence testing all solutions)
    // j is the j-th move of 11 in the solution we are trialling
    // Finding the j-th digit in our base 3 number
    for(int j=0;j<11;j++){
      int rule_id;
      if(j==0){
        rule_id = int(i%3);
      }else{
        rule_id = (int(i%pow(3,j+1))-int(i%pow(3,j)))/int(pow(3,j));
      }
      
      // Find and apply the rule found
      String[] rules = game.findRules();
      game.applyRule(rules[rule_id]);
      
      // Some rules are more 'real moves' than a 'computational move'
      if(rule_id==0){
        moves = moves +1;
      }else{
        moves = moves +3;
      }
      
      // Displaying solutions which work and their move structure and number of moves
      if(game.checkSwap()== true){
        print(asBase3(i),moves);
        String sol = str(asBase3(i));
        String movestring = str(moves);
        String[] line = {sol,movestring};
        saveStrings("output.txt",line);
        print("  |");
        break;
      }
      
    }
  }

}

// Converting to base 3 to display our base 10 number as a base 3 number, to understand the list of moves easier
long asBase3(int num) {
    long ret = 0, factor = 1;
    while (num > 0) {
        ret += num % 3 * factor;
        num /= 3;
        factor *= 10;
    }
    return ret;
}


// Pop up to see our exhaustation is complete
  void setup() {
       size(400, 400);
       stroke(255);
       test();
       Person person1 = new Person("B");
       
     }
      
     void draw() {
       line(150, 25, mouseX, mouseY);
       
     }
