package ie.tudublin;

import example.CubeVisual;
import example.MyVisual;

import c18423664.*;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new CubeVisual());		
	}

	public void intro()
	{
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch(a, new intro());
	}

	public void flags()
	{
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch(a, new Flags());
	}

	public void japaneseMountain()
	{
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch(a, new japaneseMountain());
	}

	public void SpinningLocusFlower()
	{
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch(a, new SpinningLocusFlower());
	}

	public void Crane()
	{
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch(a, new Crane());
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		//main.startUI();	
		// main.intro();	
		main.flags();
		// main.SpinningLocusFlower();
		// main.Crane();
		// main.japaneseMountain();	
	}
}