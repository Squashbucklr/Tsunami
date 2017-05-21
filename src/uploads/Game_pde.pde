int Currency;
int ClickWorth;
int x;
int y;
float r;
PImage bubble;
PImage Sky;
ArrayList<Circle> circles;
int clicks;
void setup () {
  imageMode(CENTER);
  bubble = loadImage ("BLOOBLE.png");
  Sky = loadImage ("SKOOTLE.png");
  circles = new ArrayList<Circle>();
  y = 55;
  x = 55;
  ClickWorth = 1;
  Currency = 0;
  size (1000, 750);
  background(Sky);
}

void draw () {
  if (clicks > 10) {
    ClickWorth = 2;
  }
  background(Sky);
  textSize (45);
  text (" " + Currency, x -5,y);
  textSize (30);
  text (" " + clicks, x +100,y);
  r = random(0 , 1);
  if (r < .05f) {
    circles.add(new Circle(random(0, width), random(0, height), random(180, 240)));
  }
  for(int i = 0; i < circles.size(); i++){
    if(circles.get(i).update()){
      circles.remove(i);
      i--;
    }else{
     circles.get(i).draw(); 
    }
  }
}

void mousePressed () {
  for(int i = 0; i < circles.size(); i++) {
    if(circles.get(i).collides()){
      circles.remove(i);
      i--;
      Currency += ClickWorth;
      clicks ++;
    }
    else {
      clicks = 0;
    }
  }
}

float dist(int x1, int y1, int x2, int y2){
  int xdist = abs(x1 - x2);
  int ydist = abs(y1 - y2);
  return sqrt(pow(xdist, 2) + pow(ydist, 2));
}

class Circle{
  float x;
  float y;
  float timeOut;
  Circle(float x, float y, float t){
    this.x = x;
    this.y = y;
    timeOut = t;
  }
  boolean update(){
   if(timeOut > 0){
     timeOut --;
     return false;
  }else{
   return true;
    }
  }
  boolean collides() {
    return dist(x, y, mouseX, mouseY) <= 25;
  }
   
  
  void draw(){
    image(bubble,x,y);
  }
}