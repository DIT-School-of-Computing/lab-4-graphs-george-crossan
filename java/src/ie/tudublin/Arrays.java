package ie.tudublin;

import processing.core.PApplet;



public class Arrays extends PApplet
{
	String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

	float[] rainfall = {200, 260, 300, 150, 100, 50, 10, 40, 67, 160, 400, 420};

	int maxIndex;
	int minIndex;

	public float map1(float a, float b, float c, float d, float e)
	{
		float r1 = c -b;
		float r2 = e - d;

		float howFar = a - b;
		return d + (howFar / r1) * r2;
	}

	void randomize()
	{
		for (int i = 0; i < rainfall.length; i++) {
			rainfall[i] = random(500);
		}
	}

	public void settings()
	{
		size(500, 500);

		String[] m1 = months;
		months[0] = "XXX";
		print(m1[0]);
		for(int i = 0; i < months.length; i ++)
		{
			println("Month: " + months[i] + "\t" + rainfall[i]);
		}
		for (String s : m1) {
			println(s);
		}

		minIndex = 0;
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] < rainfall[minIndex])
			{
				minIndex = i;
			}
		}
		
		maxIndex = 0;
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] > rainfall[maxIndex])
			{
				maxIndex = i;
			}
		}

		println("The month with the minimum rainfall was " + months[minIndex] + " with " + rainfall[minIndex] + "rain");
		println("The month with the max rainfall was " + months[maxIndex] + " with " + rainfall[maxIndex] + "rain");
		
		
		float tot = 0;
		for(float f:rainfall)
		{
			tot += f;
		}

		float avg = tot / (float) rainfall.length;

		// a b-c d-e;
		println(map1(5, 0, 10, 0, 100));
		// 50

		println(map1(25, 20, 30, 200, 300));
		// 250

		println(map1(26, 25, 35, 0, 100));
		// 10 


	}

	public void setup() {
		colorMode(HSB);
		background(0);
		// randomize();
		
		
	}

	
	public void draw()
	{	

		background(0);
		stroke(255);
		line(40,50,40,450);
		line(40,450,450,450);
		int chart_width = 400;


		int scale = 30;
		float increase = 30;

		for (int i = 0; i <= 15; i++){
			line(30,450 - (i * increase),40,450 - (i * increase));
			textAlign(CENTER,CENTER);
			fill(255);
			text(i * scale,20,450 - (i * increase));
		}

		float w = chart_width / (float)months.length;
		for(int i = 0 ; i < months.length ;  i ++)
		{
			fill(255,255,255);
			float x = map1(i, 0, months.length, 40, 450);
			rect(x, 450, w, -rainfall[i]);
		}
	}
}
