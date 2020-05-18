package c18423664;

import ie.tudublin.Visual;

public class japaneseMountain extends Visual 
{
  //Initializing and declaring variables
  float radius = 300;
  float circleY = 25;
  float ySpeed = 0.5f;
  int Purple = 0xffab77b3;
  int Lavender = 0xffe0afdb;
  int cloudColor = 0xffe8dce8;
  int sky = color(236, 143, 161);
  int sun = color(255, 111, 1);
  float bottom;
  float cloudSpeedPos;
  float cloudSpeedNeg;

  public void settings() { 
    //size(640, 640);
    fullScreen(P3D, SPAN); 
    smooth(); 
  }

  public void setup() {
    bottom = height;
    startMinim();
    loadAudio("GroovyRoom-Sunday.mp3");
  }

  public void keyPressed()
  {
      if (key == ' ')
      {
          getAudioPlayer().cue(0);
          getAudioPlayer().play();
      }
  }

  public void draw() {
    //Setting the background
    background(sky);
    
    //Incrementing the speed of the cloud 
    cloudSpeedPos = cloudSpeedPos + 0.9f;
    
    //Decrementing the speed of the cloud 
    cloudSpeedNeg = cloudSpeedNeg + (0.9f)*(-1);
    
    //Drawing the clouds coming in from left to right
    fill(cloudColor);
    ellipse(cloudSpeedPos, 150, 100, 50);
    ellipse(cloudSpeedPos-70, 150, 80, 30);
    ellipse(cloudSpeedPos+100, 200, 75, 40);
    ellipse(cloudSpeedPos+300, 175, 50, 20);
    ellipse(cloudSpeedPos-100, 250, 50, 10);
    ellipse(cloudSpeedPos - 600, 150, 100, 50);
    ellipse(cloudSpeedPos-70-600, 150, 80, 30);
    ellipse(cloudSpeedPos+100-600, 200, 75, 40);
    ellipse(cloudSpeedPos+300-600, 175, 50, 20);
    ellipse(cloudSpeedPos-100-600, 250, 50, 10);

    //Drawing the clouds coming in from right to left
    ellipse(cloudSpeedNeg, 150, 100, 50);
    ellipse(cloudSpeedNeg+70, 150, 80, 30);
    ellipse(cloudSpeedNeg-100, 200, 75, 40);
    ellipse(cloudSpeedNeg-300, 175, 50, 20);
    ellipse(cloudSpeedNeg+100, 250, 50, 10);
    ellipse(cloudSpeedNeg + 600, 150, 100, 50);
    ellipse(cloudSpeedNeg+70+600, 150, 80, 30);
    ellipse(cloudSpeedNeg-100+600, 200, 75, 40);
    ellipse(cloudSpeedNeg-300+600, 175, 50, 20);
    ellipse(cloudSpeedNeg+100+600, 250, 50, 10);

    if (cloudSpeedPos >= width) {
      cloudSpeedPos = 0;
    }
    
    if (cloudSpeedNeg <= 0) {
      cloudSpeedNeg = width;
    }
    

    // Drawing the Sun 
    pushMatrix();
    fill(sun);
    ellipse(width/2,circleY,radius,radius);
    circleY = circleY + ySpeed;

    if(circleY < 0 || circleY > height/2+100)
    {
      ySpeed = ySpeed * -1; 
    }
    popMatrix();
    
    
    //Drawing the mountains 
    pushMatrix();
    noStroke();
    fill(Lavender);
    triangle(0, height/2-height*0.1f, -13, bottom, width, bottom);
    fill(Purple);
    triangle(0, height/2-height*0.1f, -13, bottom, width*0.2f, bottom);
    fill(Lavender);
    triangle(width-width*0.3f, 323, width-width*0.2f, bottom, 153, bottom);
    fill(Lavender);
    triangle(width-width*0.3f, 323, width-width*0.2f, bottom, width, bottom);
    fill(Lavender);
    triangle(width-width*0.7f, height/2-height*0.05f, width-width*0.8f, bottom, -30, bottom);
    fill(Purple);
    triangle(width-width*0.7f, height/2-height*0.05f, width-width*0.8f, bottom, width, bottom);
    fill(Lavender);
    triangle(width, 366, width/2-width*0.2f, bottom, width/2-width*(-0.2f), bottom);
    fill(Purple);
    triangle(width, 366, width/2-width*(-0.2f), bottom, width, bottom);
    popMatrix();
  }
}
