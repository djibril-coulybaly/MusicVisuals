package c18423664;

import ie.tudublin.Visual;

public class SpinningLocusFlower extends Visual{

    float angle;
    float angleNeg;
    int startTime = 0;

    public void setup() {
        surface.setLocation(957, 0);
        rectMode(CENTER);
        stroke(0,15,30);
        strokeWeight(25);
        startMinim();
        loadAudio("GroovyRoom-Sunday.mp3");
    }
    
    public void settings() {
        //size(800, 600);
        fullScreen(P3D, SPAN);
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }

    public void drawLocus(float angleDirection){
        for(int i = 0; i < 100; i++)
        {
            fill(255, 200-i*25, 50-i*10);
            scale(0.95f);
            rotate(radians(angleDirection));
            rect(0, 0, 600, 600);
        }
    }
    
    public void draw() {
        background(0, 15, 30);
        float mx = width/2.5f;
        float my = height/2.5f;

        float scaleVar = map(20, 0, width, 0.5f, 5);
        scale(scaleVar);
        
        if (millis() - startTime > 2000) {
            pushMatrix();
            translate(mx, my);
            drawLocus(angle);
            popMatrix();
        }
        
        if (millis() - startTime > 3000) {
            pushMatrix();
            translate(mx + 2*(width/2), my);
            drawLocus(angleNeg);
            popMatrix();
        }
        
        if (millis() - startTime > 4000) {
            pushMatrix();
            translate(mx, my + height * 0.9f);
            drawLocus(angleNeg);
            popMatrix();
        }
        
        if (millis() - startTime > 5000) {
            pushMatrix();
            translate(mx + 2*(width/2), my + height * 0.9f);
            drawLocus(angle);
            popMatrix();
        }
        
        if (millis() - startTime > 6000) {
            pushMatrix();
            translate(mx+width/2, height-50);
            drawLocus(angle);
            popMatrix();
        }
        
        if (millis() - startTime > 9000) { 
            startTime = millis(); 
        }
        angle+=0.3;
        angleNeg-=0.3;
    }
}