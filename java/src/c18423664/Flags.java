package c18423664;

import ie.tudublin.Visual;


public class Flags extends Visual
{
    // Base of the flag
    final int BASE = 50;

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
    
    //Method to draw the Taegeuk of the Japanese Flag
    public void drawTaegeukJapanese(int i, int j)
    {
        // Official red colour of the Korean flag
        int red = 0xffC60C30;

        // Save transformation state of the Taeguek
        pushMatrix();
        
        // Put the Taeguek in place
        translate(flagCenterX + j, flagCenterY + i);

        // Rotating the Taeguek
        rotate(baseAngle);
        
        // Drawing the Taeguek
        fill(red);
        arc(0, 0, BASE, BASE, 0, PI);
        fill(red);
        arc(0, 0, BASE, BASE, -PI, 0);

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

    //Method to draw the Japanese Flag
    public void japaneseFlag(int i, int j)
    {
        //Drawing the Taegeuk
        drawTaegeukJapanese(i, j);
    }

    public void setup()
    {
        ipos = height;
        jpos = width;
    }
    
    public void settings() 
    {
        // size(800, 600); 
        fullScreen(P3D, SPAN); 
        smooth(); 
    }

    public void draw()
    {
        background(0);
    
        flagCenterX = 100;
        flagCenterY = 80;
        
        int flagWidth  = 3 * BASE;
        int flagHeight = 2 * BASE;
        
        int posX = flagCenterX - flagWidth / 2;
        int posY = flagCenterY - flagHeight / 2;

        //Displaying the Korean Flag at the top left corner
        for (int i = 0; i <= ipos/2; i+=150) 
        {
            for (int j = 0; j <= jpos/2; j+=215) 
            {
                noStroke();
                fill(255);
                rect(posX + j, posY + i, flagWidth, flagHeight);
                fill(0);
                koreanFlag(i, j);
            }     
        }

        //Displaying the Japanese Flag at the top right corner
        for (int i = 0; i <= ipos/2; i+=150) 
        {
            for (int j = jpos/2 + 100; j <= jpos; j+=215) 
            {
                noStroke();
                fill(255);
                rect(posX + j, posY + i, flagWidth, flagHeight);
                fill(0);
                japaneseFlag(i, j);
            }     
        }
        
        //Displaying the Japanese Flag at the bottom left corner
        for (int i = ipos/2 + 100; i <= ipos; i+=150) 
        {
            for (int j = 0; j <= jpos/2; j+=215) 
            {
                noStroke();
                fill(255);
                rect(posX + j, posY + i, flagWidth, flagHeight);
                fill(0);
                japaneseFlag(i, j);
            }     
        }

        //Displaying the Korean Flag at the bottom right corner
        for (int i = ipos/2 + 100; i <= ipos; i+=150) 
        {
            for (int j = jpos/2 + 100; j <= jpos; j+=215) 
            {
                noStroke();
                fill(255);
                rect(posX + j, posY + i, flagWidth, flagHeight);
                fill(0);
                koreanFlag(i, j);
            }     
        }
    }
}