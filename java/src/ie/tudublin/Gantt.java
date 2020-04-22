package ie.tudublin;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	

	// Declare an arraylist.
	ArrayList<Task> tsk = new ArrayList<Task>();

	// Settings method.
	public void settings()
	{

		// The size of the window.
		size(800, 600);

	}
	// End settings method.

	// loadTask method.
	public void loadTasks()
	{

		// Loads table.
        Table table = loadTable("tasks.csv", "header");

		// Adds each row into the arraylist.
        for(TableRow row:table.rows())
        {   

            Task p = new Task(row);

			tsk.add(p);
			
		}	
		// End for loop.

	}
	// End loadTask method.

	// printTasks method.
	public void printTasks()
	{	
		// Displays each row in the terminal.
        for(Task t:tsk)
        {
            System.out.println(t);
		}
		// End for loop
	}

	// displayTasks method.
	public void displayTasks() {
		// All measurments are done for size(800, 600); but feel free to change the size ; it should work on any screen.

		// The left side padding that the text has.
		float textBorder = width * 0.05f;	// 40

		// Top side padding that text has.
		float textDown = height * 0.1f;		// 60

		// The maximum distance that the line bars can go to.
		float maxAcross = width * 0.95f;     // 720
		
		// Used for colours. The max colour range divided by the arraylist to achieve different colours.
		float colours = 255.0f / 30;

		// The maximum distance that the text can travel going down the list.
		float maxDown = height * 0.8f;      // 480

		// The x co - ord of the numbers.
		float numberMinDown = height * 0.05f;  // 30

		// The maximum distance that the lines drop.
		float lineMaxDown = height * 0.9f;

		// The small boxes that measure one bar.
		float numberDiff = (maxAcross - textBorder * 3.0f) / 30;
		
		// Generates list of tasks on screen.
		for (int i = 0 ; i < tsk.size() ; i ++)
		{
			Task t = tsk.get(i);

			float y = map(i, 0, tsk.size(), textDown, maxDown);

			fill(0, 0, 100);
			text(t.getTask(), textBorder, y);
		}

		// Generates numbers 1 to 30.
		// Also generates lines going down the screen under the numbers drawn.
		for (int i = 1 ; i <= 30 ; i++ )
		{
			// Make numbers.
			float x = map(i, 1, 30, textBorder * 3.0f, maxAcross);
			text(i, x, numberMinDown);

			// Make lines.
			if (i % 2 == 1)
			{
				//Nearly white.
				stroke(0, 0, 100);
				line(x+4, numberMinDown + 10, x+4, lineMaxDown);
			}
			else
			{	
				// Gray.
				stroke(0, 0, 50);
				line(x+4, numberMinDown + 10, x+4, lineMaxDown);
			}
		}

		// Generate colour bars.
		for (int i = 0; i < tsk.size() ; i++)
		{
			// Sets colours for rects.
			fill(colours * i, 100, 100);
			for(int j = 1 ; j <= 30 ; j++)
			{
				Task t = tsk.get(i);
	
				// down text
				float y = map(i, 0, tsk.size(), textDown, maxDown);
				
				noStroke();
				// Displays the chart rects.
				if(j >= t.getStart() && j < t.getEnd())
				{
					float x = map(j, 1, 30, textBorder * 3.0f, maxAcross);
					rect(x+4, y-15, numberDiff, 20);
				}
			}	// End inner for
		}	// End outer for

	}
	// End displayTasks method.
	
	// mousePressed Method.
	public void mousePressed()
	{

		float textBorder = width * 0.05f;	// 40
		float maxAcross = width * 0.95f;     // 720

		// The y co-ordinate of the rects.
		float boxyArea = 45;

		// Going through all items.
		for (int i = 0 ; i < tsk.size() ; i++ )
		{
			Task t = tsk.get(i);

			int j = i + 1;

			int valueClickedOn = mouseX - 120;

			int finalValue;

			if(i == 0)
			{
				if(mouseX > textBorder * 3.0f && mouseX < maxAcross &&
				mouseY > 45   && mouseY < 70)
				{
					// Finds what value in the row the user clicks on (this is the value from 1 to 30).
					finalValue = valueClickedOn / 20 + 1;
				
					if (finalValue < t.getStart())
					{
						t.setStart(finalValue);
					}
					else
					{
						t.setEnd(finalValue);
					}
	
				}
			}
			else if ( i == 1 )
			{
				if(mouseX > textBorder * 3.0f && mouseX < maxAcross &&
				mouseY > 90 && mouseY < 115)
				{
					// Finds what value in the row the user clicks on (this is the value from 1 to 30).
					finalValue = valueClickedOn / 20 + 1;
					if (finalValue < t.getStart())
					{
						t.setStart(finalValue);
					}
					else
					{
						t.setEnd(finalValue);
					}
				}
			}
			else
			{
				if ( mouseX > textBorder * 3.0f && mouseX < maxAcross &&
				mouseY > (boxyArea*j)  && mouseY < (boxyArea*j) + 25)
				{
					// Finds what value in the row the user clicks on (this is the value from 1 to 30).
					finalValue = valueClickedOn / 20 + 1;
					if (finalValue < t.getStart())
					{
						t.setStart(finalValue);
					}
					else
					{
						t.setEnd(finalValue);
					}

				}
			}
		}
	}
	// End MousePressed method.

	// Start mouseDragged method.
	public void mouseDragged()
	{
		float textBorder = width * 0.05f;	// 40
		float maxAcross = width * 0.95f;     // 720

		// The y co-ordinate of the rects.
		float boxyArea = 45;

		// Going through all items.
		for (int i = 0 ; i < tsk.size() ; i++ )
		{
			Task t = tsk.get(i);

			int j = i + 1;

			int valueClickedOn = mouseX - 120;

			int finalValue;

			if(i == 0)
			{
				if(mouseX > textBorder * 3.0f && mouseX < maxAcross &&
				mouseY > 45   && mouseY < 70)
				{
					// Finds what value in the row the user clicks on (this is the value from 1 to 30).
					finalValue = valueClickedOn / 20 + 1;

					if (finalValue < t.getStart())
					{
						t.setStart(finalValue);
					}
					else
					{
						t.setEnd(finalValue);
					}		
				}
			}
			else if ( i == 1 )
			{
				if(mouseX > textBorder * 3.0f && mouseX < maxAcross &&
				mouseY > 90 && mouseY < 115)
				{
					// Finds what value in the row the user clicks on (this is the value from 1 to 30).
					finalValue = valueClickedOn / 20 + 1;

					if (finalValue < t.getStart())
					{
						t.setStart(finalValue);
					}
					else
					{
						t.setEnd(finalValue);
					}
				}
			}
			else
			{
				if ( mouseX > textBorder * 3.0f && mouseX < maxAcross &&
				mouseY > (boxyArea*j)  && mouseY < (boxyArea*j) + 25)
				{
					// Finds what value in the row the user clicks on (this is the value from 1 to 30).
					finalValue = valueClickedOn / 20 + 1;
					
					if (finalValue < t.getStart())
					{
						t.setStart(finalValue);
					}
					else
					{
						t.setEnd(finalValue);
					}
				}
			}
		}
	}
	// End mouseDragged method.

	public void setup() 
	{
		// HSB colour scheme.
		colorMode(HSB, 100);

		// Loads table
		loadTasks();

		// Displays table in terminal
		printTasks();
	}
	
	public void draw()
	{			
		// Black background.
		background(0);

		// Displays tasks on screen.
		displayTasks();
	}
}
