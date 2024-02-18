package ie.tudublin;

import processing.core.PApplet;



public class Arrays extends PApplet
{
	String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

	float[] rainfall = {200, 260, 300, 150, 100, 50, 10, 40, 67, 160, 400, 420};

	int maxIndex;
	int minIndex;

	int mode = 0;

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
		// months[0] = "XXX";
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

	public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		println(mode);
	}

	
	public void draw()
	{	

		switch (mode) {
			case 0:
				{
				background(0);
				stroke(255);
				float chart_width = width * .9f;
				int scale = Math.round(rainfall[maxIndex] / 12);
				line(40,chart_width - ((11 + 1)* scale),40,chart_width);
				line(40,chart_width,chart_width,chart_width);

				
				line(30,chart_width,40,chart_width);
				textAlign(CENTER,CENTER);
				fill(255);
				text(0,20,chart_width);

				float w = chart_width / (float)months.length;
				for(int i = 0 ; i < months.length;  i ++)
				{
					// graph ticks and numbers
					line(30,chart_width - ((i + 1) * scale) ,40,chart_width - ((i + 1) * scale));
					textAlign(CENTER,CENTER);
					fill(255);
					text((i + 1)* scale,20,chart_width - ((i + 1)* scale));

					float x = map1(i, 0, months.length, 40, chart_width);
					
					// month names
					text(months[i],x + (w/2),chart_width + 10);
					
					// graph bars
					fill(i * (255 / rainfall.length),255,255);
					rect(x, chart_width, w, -rainfall[i]);
				}
				break;
			}
			case 1:
				{
				background(0);
				stroke(255);
				fill(255);
				//width
				float chart_width = width * .9f;
				float space_width = width * .1f;
				//height
				float chart_height = height * .9f;
				float space_height = height * .1f;
				// x and y axis
				line(space_width,space_height,space_width,chart_height);
				line(space_width,chart_height,chart_width,chart_height);
				// title
				textAlign(CENTER,CENTER);
				text("Rainfall Trend Chart", width / 2, space_height / 2);
	
				for(int i = 0 ; i < months.length;  i++){
					//ticks
					float tick_height = map1(i + 1, 0, months.length, chart_height, space_height);
					line(space_width, tick_height,space_width - 10, tick_height);
					// numbers
					int line_val = (int) map1(i + 1, 0, months.length,0,(float) rainfall[maxIndex]);
					text(line_val,space_width - 20, tick_height);
					//months
					float point_width = map1(i, 0, months.length,space_width,chart_width) + 25;
					text(months[i],point_width,chart_height + 10);
					//points
					if(i != 0){
						float point_height = map1(rainfall[i], 0, rainfall[maxIndex], chart_height,space_height);

						// height and width of last point
						float previous_point_width = map1(i-1, 0, months.length,space_width,chart_width) + 25;
						float previous_point_height = map1(rainfall[i-1], 0, rainfall[maxIndex], chart_height,space_height);

						line(previous_point_width,previous_point_height,point_width,point_height);
					}
				}	
				// lock = false;
				break;
			}
			case 3:
				{
				background(0);
				stroke(255);
				fill(255);
				// title
				textAlign(CENTER,CENTER);
				text("Rainfall Pie Chart", width / 2, (height * .1f) / 2);
				//
				int center_width = width / 2;
				int center_height = height / 2;

				float total_angle = 0;

				float sum = 0;
				for(int i = 0 ; i < rainfall.length;  i++){
					sum += rainfall[i];
				}
				
				for(int i = 0 ; i < months.length;  i ++){
					fill(i * (255 / rainfall.length),255,255);

					float angle = map(rainfall[i], 0, sum, 0, TWO_PI);
					arc(center_width,center_height,width / 2, height / 2, total_angle, angle + total_angle);
					total_angle += angle;

					fill(255);
					float distance = dist(center_width,center_height,width * .29f,height * .29f);
					text(months[i],center_width + cos(total_angle - (angle / 2)) * distance,center_height + sin(total_angle -(angle / 2)) * distance);
				}
			}
		}
	}
}
