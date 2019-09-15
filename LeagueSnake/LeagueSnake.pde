//*
// ***** SEGMENT CLASS *****
// This class will be used to represent each part of the moving snake.
//*

class Segment {

//Add x and y member variables. They will hold the corner location of each segment of the snake.
int x;
int y;


// Add a constructor with parameters to initialize each variable.
public Segment(int x, int y){
    this.x=x;
    this.y=y;
}


}


//*
// ***** GAME VARIABLES *****
// All the game variables that will be shared by the game methods are here
//*

Segment snakehead;
int foodX;
int foodY;
ArrayList<Segment> tail= new ArrayList<Segment>();
int direction=UP;
int numberofpieces=0;

//*
// ***** SETUP METHODS *****
// These methods are called at the start of the game.
//*

void setup() {
  size(500,500);
  snakehead= new Segment(250,250);
  frameRate(20);
  dropFood();
}

void dropFood() {
  //Set the food in a new random location
  foodX= ((int) random(50)*10);
  foodY=((int) random(50)*10);
}



//*
// ***** DRAW METHODS *****
// These methods are used to draw the snake and its food 
//*

void draw() {
  background(0,0,0);
  fill(255,255,0);
  text("score: " +numberofpieces  ,10,20);
  drawFood();
  move();
  drawSnake();
  eat();
}

void drawFood() {
  //Draw the food
  fill(100,240,50);
  square(foodX,foodY,10);
  
}

void drawSnake() {
  //Draw the head of the snake followed by its tail
  fill(9,40,230);
  rect(snakehead.x,snakehead.y,10,10);
  manageTail();
}


//*
// ***** TAIL MANAGEMENT METHODS *****
// These methods make sure the tail is the correct length.
//*

void drawTail() {
  //Draw each segment of the tail 
  for(int i=0; i<tail.size(); i++){
    rect(tail.get(i).x,tail.get(i).y,10,10);
    
  }
}

void manageTail() {
  //After drawing the tail, add a new segment at the "start" of the tail and remove the one at the "end" 
  //This produces the illusion of the snake tail moving.
  checkTailCollision();
  drawTail();
  tail.add(new Segment(snakehead.x,snakehead.y));
  tail.remove(0);
  
  
}

void checkTailCollision() {
  //If the snake crosses its own tail, shrink the tail back to one segment
  for(int i=0; i<tail.size(); i++){
     if(tail.get(i).x==snakehead.x && snakehead.y==tail.get(i).y){
       numberofpieces=0;
       tail=new ArrayList<Segment>();
       tail.add(new Segment(snakehead.x, snakehead.y));
       
     }
    
  }
  
}



//*
// ***** CONTROL METHODS *****
// These methods are used to change what is happening to the snake
//*

void keyPressed() {
  //Set the direction of the snake according to the arrow keys pressed
  if(key==CODED){
    if(keyCode==UP){
      direction=UP;
    }
    else if(keyCode==DOWN){
       direction=DOWN; 
    }
    if(keyCode==RIGHT){
     direction=RIGHT; 
    }
    else if(keyCode==LEFT){
     direction=LEFT; 
    }
    
  }
  
}

void move() {
  //Change the location of the Snake head based on the direction it is moving.
  
    
  switch(direction) {
  case UP:
    // move head up here 
    snakehead.y-=5;
    break;
  case DOWN:
    // move head down here 
    snakehead.y+=5;
    break;
  case LEFT:
   // figure it out 
   snakehead.x-=5;
    break;
  case RIGHT:
    // mystery code goes here 
    snakehead.x+=5;
    break;
  }
  checkBoundaries();
}

void checkBoundaries() {
 //If the snake leaves the frame, make it reappear on the other side
 if(snakehead.x>500){
   snakehead.x=0;
 }
 else if(snakehead.x<0){
  snakehead.x=500; 
 }
 if(snakehead.y>500){
  snakehead.y=0; 
 }
 else if(snakehead.y<0){
  snakehead.y=500; 
 }
}



void eat() {
  //When the snake eats the food, its tail should grow and more food appear
  if(snakehead.x>foodX-10 && snakehead.x<foodX+10 && snakehead.y>foodY-10 && snakehead.y<foodY+10){

   dropFood();
    numberofpieces+=1;
   tail.add(new Segment(snakehead.x, snakehead.y));
    
  }
  
}
