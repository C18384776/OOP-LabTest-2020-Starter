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
		loadTasks();
		printTasks();
	}
	
	public void draw()
	{			
		background(0);
	}
}
