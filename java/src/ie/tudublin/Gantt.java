package ie.tudublin;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	ArrayList<Task> tsk = new ArrayList<Task>();

	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{

        Table table = loadTable("tasks.csv", "header");

        for(TableRow row:table.rows())
        {   
            Task p = new Task(row);

            tsk.add(p);
        }
	}

	public void printTasks()
	{
        for(Task t:tsk)
        {
            System.out.println(t);
        }
	}

	public void displayTasks() {

		float textBorder = width * 0.05f;	// 40
		float textDown = height * 0.1f;		// 60
		float maxDown = height * 0.8f;      // 480
		float maxAcross = width * 0.95f;     // 720
		float numberMinDown = height * 0.05f;  // 30
		float lineMaxDown = height * 0.9f;
		float numberDiff = (maxAcross - textBorder * 3.0f) / 30;
		float colours = 255.0f / 30.0f;

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
		println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	public void setup() 
	{
		colorMode(HSB, 100);
		loadTasks();
		printTasks();
	}
	
	public void draw()
	{			
		background(0);
		displayTasks();
	}
}
