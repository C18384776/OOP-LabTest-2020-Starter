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
			stroke(176, 51, 43);
			line(x+4, numberMinDown + 10, x+4, lineMaxDown);
		}

		// Generate colour bars.
		for (int i = 0; i < tsk.size() ; i++)
		{
			fill(colours * i, 100, 100);
			for(int j = 1 ; j <= 30 ; j++)
			{
				Task t = tsk.get(i);
	
				// down text
				float y = map(i, 0, tsk.size(), textDown, maxDown);
				
				// Displays the chart rects.
				if(j >= t.getStart() && j < t.getEnd())
				{
					float x = map(j, 1, 30, textBorder * 3.0f, maxAcross);
					rect(x+4, y-15, numberDiff, 20);
				}
			}	// End inner for
		}	// End outer for

	}
	
	public void mousePressed()
	{

		float textBorder = width * 0.05f;	// 40
		float maxAcross = width * 0.95f;     // 720
		float boxyArea = 45;

		// Going through all items.
		for (int i = 0 ; i < tsk.size() ; i++ )
		{
			Task t = tsk.get(i);

			int j = i + 1;

			int valueClickedOn = mouseX - 120;

			int finalValue;

			int startVal = t.getStart();

			int endVal = t.getEnd();

			if(i == 0)
			{
				if(mouseX > textBorder * 3.0f && mouseX < maxAcross &&
				mouseY > 45   && mouseY < 70)
				{
					finalValue = valueClickedOn / 20 + 1; // 20 is 30 / 20 --- will explain better later...

					if (finalValue > t.getEnd())
					{
						t.setEnd(finalValue);
					}

					if (finalValue < t.getEnd() && finalValue > t.getStart())
					{
						t.setStart(finalValue);
					}			
				}
			}
			else if ( i == 1 )
			{
				if(mouseX > textBorder * 3.0f && mouseX < maxAcross &&
				mouseY > 90 && mouseY < 115)
				{
					finalValue = valueClickedOn / 20 + 1; // 20 is 30 / 20 --- will explain better later...
					if (finalValue > t.getEnd())
					{
						t.setEnd(finalValue);
					}

					if (finalValue < t.getEnd() && finalValue > t.getStart())
					{
						t.setStart(finalValue);
					}		
				}
			}
			else
			{
				if ( mouseX > textBorder * 3.0f && mouseX < maxAcross &&
				mouseY > (boxyArea*j)  && mouseY < (boxyArea*j) + 25)
				{
					finalValue = valueClickedOn / 20 + 1; // 20 is 30 / 20 --- will explain better later...
					if (finalValue > t.getEnd())
					{
						t.setEnd(finalValue);
					}

					if (finalValue < t.getEnd() && finalValue > t.getStart())
					{
						t.setStart(finalValue);
					}	
				}
			}
		}
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
		float textBorder = width * 0.05f;	// 40
		float maxAcross = width * 0.95f;     // 720
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
					finalValue = valueClickedOn / 20 + 1; // 20 is 30 / 20 --- will explain better later...
					if (finalValue > t.getEnd())
					{
						t.setEnd(finalValue);
					}

					if (finalValue < t.getEnd() && finalValue > t.getStart())
					{
						t.setStart(finalValue);
					}				
				}
			}
			else if ( i == 1 )
			{
				if(mouseX > textBorder * 3.0f && mouseX < maxAcross &&
				mouseY > 90 && mouseY < 115)
				{
					finalValue = valueClickedOn / 20 + 1; // 20 is 30 / 20 --- will explain better later...
					if (finalValue > t.getEnd())
					{
						t.setEnd(finalValue);
					}

					if (finalValue < t.getEnd() && finalValue > t.getStart())
					{
						t.setStart(finalValue);
					}	
				}
			}
			else
			{
				if ( mouseX > textBorder * 3.0f && mouseX < maxAcross &&
				mouseY > (boxyArea*j)  && mouseY < (boxyArea*j) + 25)
				{
					finalValue = valueClickedOn / 20 + 1; // 20 is 30 / 20 --- will explain better later...
					if (finalValue > t.getEnd())
					{
						t.setEnd(finalValue);
					}

					if (finalValue < t.getEnd() && finalValue > t.getStart())
					{
						t.setStart(finalValue);
					}	
				}
			}
		}
	}

	public void setup() 
	{
		colorMode(HSB, 100);
		loadTasks();
		printTasks();
		displayTasks();

	}
	
	public void draw()
	{			
		background(0);
		displayTasks();
	}
}
