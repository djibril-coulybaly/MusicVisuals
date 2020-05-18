package c18423664;

import ie.tudublin.Visual;


public class Crane extends Visual {

  //Declaring and Initialising my variables
  float xDirection;
  float xDirectionNeg;
  float ySpeed = 0.9f;

  public void settings() { 
    //size(640, 640); 
    fullScreen(P3D, SPAN); 
    smooth(); 
  }

  public void setup() {
  }

  public void leftCrane(float spacing){
    //Drawing the head of the crane
    fill(217, 4, 41);
    arc(xDirection + 40, height/3.9f + spacing, 80, 80, radians(180), radians(270));
    arc(xDirection + 40, height/3.9f + spacing, 80, 80, radians(270), radians(360));
    //Drawing the eyes of the crane
    fill(0);
    ellipse(xDirection + 50, height/4.2f + spacing, 10, 10);
    //Drawing the neck of the crane
    fill(0);
    arc(xDirection, height/3.9f + spacing, 80, 80, radians(0), radians(90));
    //Drawing the wing and body of the crane
    fill(255);
    rectMode(CORNER);
    rect(xDirection, height/3.9f + spacing, -60, 40);
  }

  public void rightCrane(float spacing){
    //Drawing the head of the crane
    fill(217, 4, 41);
    arc(xDirectionNeg - 40, (height/7) + spacing, 80, 80, radians(180), radians(270));
    arc(xDirectionNeg - 40, (height/7) + spacing, 80, 80, radians(270), radians(360));

    //Drawing the eyes of the crane
    fill(0);
    ellipse(xDirectionNeg - 50, (height/8.5f) + spacing, 10, 10);
    
    //Drawing the body of the crane
    fill(255);
    rectMode(CORNER);
    rect(xDirectionNeg, (height/7) + spacing, -60, 40);
    
    //Drawing the wings of the crane
    fill(0);
    arc(xDirectionNeg, (height/7) + spacing, 80, 80, radians(0), radians(90));
  }

  public void draw() {
    //Setting the background colour
    background(152, 200, 214);
    noStroke();
    
    //Incrementing the speed of the crane 
    xDirection = xDirection + ySpeed;
    
    //Decrementing the speed of the crane
    xDirectionNeg = xDirectionNeg + ySpeed * (-1);
    
    //Drawing the cranes in a for loop so that it will draw x amount of cranes until it reaches the edge of the screen
    for (int i = 0; i < height; i+=250) {
      leftCrane(i);
      rightCrane(i);
    }

    //Bring the crane back to the left-side of the screen when it reaches the right-side of the screen
    if(xDirection >= width){
      xDirection = 0;
    }
    
    //Bring the crane back to the right-side of the screen when it reaches the left-side of the screen
    if(xDirectionNeg <= 0){
      xDirectionNeg = width;
    }
  }

}
