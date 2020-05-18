package c18423664;

import ie.tudublin.Visual;
import processing.core.PFont;

public class intro extends Visual{
//Declaring and Initializing Variables
    String introSentence = "OOP Assignment 2019-2020" + "\n\n" + "Created by Djibril Coulybaly" + "\n\n" +"지브릴 쿨리 발리" + "\n\n" + "Music: Sunday - GroovyRoom ft. Heize, Jay Park" + "\n\n" + "Press Spacebar to cue music!";
    String introKorean = "OOP 과제 2019-2020 " ;
    String introKorean1 = " 지브릴 쿨리 발리 제작 "; 
    String introKorean2 = " 음악 : 일요일-GroovyRoom ft. Heize, Jay Park ";
    PFont font;
    int textCounter = 0;
    int textCounter1 = 0;
    int time = 0;
    int time1 = 10000;
    boolean timer = false;

    // Base of the flag
    final int BASE = 160;

    // Dimentions of the official Korean Flag
    final int BAR_HEIGHT = BASE / 12;
    final int LONG_BAR_WIDTH = BASE / 2;
    final int SHORT_BAR_VERTICAL_INTERVAL = BASE / 24;
    final int SHORT_BAR_WIDTH = (LONG_BAR_WIDTH - SHORT_BAR_VERTICAL_INTERVAL) / 2;
    final int BAR_VERTICAL_INTERVAL = BASE / 24;
    final int BAR_INTERVAL = BAR_HEIGHT + BAR_VERTICAL_INTERVAL;
    final float DISTANCE_TRIGRAM_CENTER = BASE * (1.0f / 2 + 1.0f / 4);
    final int LONG_BAR = 1;
    final int TWO_BARS = 2;
    final int TOP_LEFT = 1;
    final int TOP_RIGHT = 2;
    final int BOTTOM_LEFT = 3;
    final int BOTTOM_RIGHT = 4;
    final float baseAngle = atan(2.0f / 3.0f);
    int flagCenterX, flagCenterY;
    
    final boolean DEBUG = false;

    //Variables to loop the display of the flag
    int ipos;
    int jpos;
    
    //Method to draw the bar on the Trigram
    public void drawLongBar(int pos)
    {
        //Drawing the bars that are on every corner of the trigram on the flag apart from the top left corner 
        rect(0, pos *  BAR_INTERVAL, LONG_BAR_WIDTH, BAR_HEIGHT);
    }

    //Method to draw the 2 seperate bars on the Trigram
    public void drawTwoBars(int pos)
    {
        //Drawing the 2 seperate bars that are on every corner of the trigram on the flag apart from the top left corner 
        rect(0, pos * BAR_INTERVAL, SHORT_BAR_WIDTH, BAR_HEIGHT);
        rect(SHORT_BAR_WIDTH + SHORT_BAR_VERTICAL_INTERVAL, pos * BAR_INTERVAL, SHORT_BAR_WIDTH, BAR_HEIGHT);
    }
    
    //Method to draw the Trigram of the Korean Flag
    public void drawTrigram(int x, int j, int trigramPosition, int... types)
    {
        // Save transformation state of the flag
        pushMatrix();
        
        float angle = 0;
        switch (trigramPosition)
        {
        case TOP_LEFT:
            angle = baseAngle - HALF_PI;
            break;
        case TOP_RIGHT:
            angle = HALF_PI - baseAngle;
            break;
        case BOTTOM_LEFT:
            angle = -HALF_PI - baseAngle;
            break;
        case BOTTOM_RIGHT:
            angle = HALF_PI + baseAngle;
            break;
        default:
            assert false : "Bad trigram position!";
        }
        final float posX = flagCenterX + DISTANCE_TRIGRAM_CENTER * sin(angle);
        final float posY = flagCenterY - DISTANCE_TRIGRAM_CENTER * cos(angle);
        if (DEBUG)
        {
            println(degrees(angle));
            println(cos(angle) + " " + sin(angle));
            println(posX + " " + posY);
        }
        
        // Put the trigram in place
        translate(posX + j, posY + x);

        // Rotating the trigram
        rotate(angle);

        // Compensate to rotate relative to the bottom-center
        translate(-LONG_BAR_WIDTH / 2, -BASE / 3);
        
        // Draw the bars for the trigram
        for (int i = 0; i < types.length; i++)
        {
            if (types[i] == LONG_BAR)
            {
            drawLongBar(i);
            }
            else
            {
            drawTwoBars(i);
            }
        }
        
        // Restore transformation state of the flag
        popMatrix();
    }
    
    //Method to draw the Taegeuk of the Korean Flag
    public void drawTaegeuk(int i, int j)
    {
        // Official red colour of the Korean flag
        int red = 0xffC60C30;

        // Official blue colour of the Korean flag
        int blue = 0xff003478; 
        
        // Save transformation state of the Taeguek
        pushMatrix();
        
        // Put the Taeguek in place
        translate(flagCenterX + j, flagCenterY + i);

        // Rotating the Taeguek
        rotate(baseAngle);
        
        // Drawing the Taeguek
        fill(blue);
        arc(0, 0, BASE, BASE, 0, PI);
        fill(red);
        arc(0, 0, BASE, BASE, -PI, 0);
        fill(blue);
        ellipse(BASE / 4, 0, BASE / 2, BASE / 2);
        fill(red);
        ellipse(-BASE / 4, 0, BASE / 2, BASE / 2);
        
        // Restore transformation state of the flag
        popMatrix();
    }
    
    //Method to draw the Korean Flag
    public void koreanFlag(int i, int j)
    {
        //Drawing the top left Trigram
        drawTrigram(i, j, TOP_LEFT, LONG_BAR, LONG_BAR, LONG_BAR);

        //Drawing the top right Trigram
        drawTrigram(i, j, TOP_RIGHT, TWO_BARS, LONG_BAR, TWO_BARS);
        
        //Drawing the bottom left Trigram
        drawTrigram(i, j, BOTTOM_LEFT, LONG_BAR, TWO_BARS, LONG_BAR);
        
        //Drawing the bottom right Trigram
        drawTrigram(i, j, BOTTOM_RIGHT, TWO_BARS, TWO_BARS, TWO_BARS);

        //Drawing the Taegeuk
        drawTaegeuk(i, j);
    }

    public void setup() { 
        //Custom font to display Korean Symbol
        font = loadFont("MalgunGothic-40.vlw");
        frameRate(40);
        textFont(font, 40);
        startMinim();
        loadAudio("GroovyRoom-Sunday.mp3");
        time = millis();
        jpos = width;
    }
    
    public void settings() {
        // size(800, 600); 
        fullScreen(P3D, SPAN);
        smooth();
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
        background(0);
        
        flagCenterX = 100;
        flagCenterY = 80;
        
        int flagWidth  = 3 * BASE;
        int flagHeight = 2 * BASE;
        
        int posX = flagCenterX - flagWidth / 2;
        int posY = flagCenterY - flagHeight / 2;

        //Displaying the Korean Flag at the top left corner
        noStroke();
        fill(255);
        rect(posX + jpos/2 - 100, posY + 100, flagWidth, flagHeight);
        fill(0);
        koreanFlag(100, jpos/2 -100);

        //Displaying the Introduction Text
        textAlign(CENTER, CENTER);
        text(introSentence.substring(0, textCounter), 0, 40, width-20, height);

        if (textCounter < introSentence.length()) 
        {
            if (frameCount%4==0)
            {
            textCounter++; 
            }
            
        }  
        
        //Displaying the Introduction Text in Korean
        // textAlign(CENTER, BOTTOM);
        // text(introKorean.substring(0, textCounter1), 0, 400000, width-20, height);

        // if (textCounter1 < introKorean.length()) 
        // {
        //     if (frameCount%4==0)
        //     {
        //     textCounter++; 
        //     }
            
        // }
    }
}