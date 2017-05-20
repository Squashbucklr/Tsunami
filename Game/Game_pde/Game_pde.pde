int Currency;
int ClickWorth;
int x;
int y;
float r;
ArrayList<Circle> circles;
void setup () {
  circles = new ArrayList<Circle>();
  y = 55;
  x = 55;
  ClickWorth = 1;
  Currency = 0;
  size (1000, 750);
  background(125);
}

void draw () {
  
  background(125);
  text (" " + Currency, x,y);
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
    ellipse(x,y, 50, 50);
  }
}