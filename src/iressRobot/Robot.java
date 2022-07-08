package iressRobot;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simulation of a toy robot on a square table top of 5*5 dimensions
 * 
 * 
 * @author Abhishek Karthik Nagaraja
 * @version 1.0.0 08-07-2022
 */
public class Robot {
	
	//variables to hold the location of the robot on the table
	private static String position;
	private static int x;
	private static int y;
	private static String face; //Stores which direction the robot is facing

	/**
     * Constructor for objects of class Robot
     */
	public Robot()
	{
		x = 0;
		y = 0;
		position = x + "," + y + "," + face;
	}
	
	/**
     * Method to get the direction the robot is facing
     *
     * @return    the direction of the robot (N, E, W or S)
     */
	public static String getFace()
	{
		return face;
	}
	
	/**
     * Method to get the position of the robot
     *
     * @return    the co-ordinates of the robot with the face direction
     */
	public static String getPosition()
	{
		return position;
	}
	
	/**
     * Method to get the X coordinate of the robot
     *
     * @return    the  X coordinate of the robot
     */
	public int getX()
	{
		return x;
	}
	
	/**
     * Method to get the Y coordinate of the robot
     *
     * @return    the  Y coordinate of the robot
     */
	public int getY()
	{
		return y;
	}
	
	/**
     * Method to set the direction of the robot to face North or South or East or West
     *
     *@param newFace The direction of the robot as specified by the user
     */
	public static void setFace(String newFace)
	{
		face =  newFace;
	}
	
	/**
     * Mutator method to set the position of the robot
     *
     * @param  face  The current direction the robot is facing
     */
	public static void setPosition(String face)
	{
				position = x + "," + y + "," + face;		
			
	}
	
	/**
     * A method to set the X coordinate of the robot
     *
     * @param  newX    The X coordinate specified by the user input for the robot
     * Display an error message if the robot is asked to move off the table
     */
	public static void setX(int newX)
	{
		if (x < 4 && x > 0) //Prevents the robot from falling off the table
			x += newX;
		else
			System.out.println("Oops! I will fall off the table if I move there \n");
	}
	
	/**
     * A method to set the Y coordinate of the robot
     *
     * @param  newY    The Y coordinate specified by the user input for the robot
     * Display an error message if the robot is asked to move off the table
     */
	public static void setY(int newY)
	{
		if (y < 4 && y > 0) //Prevents the robot from falling off the table
			y += newY;
		else
			System.out.println("Oops! I will fall off the table if I move there \n");
	}
	
	/**
     * A method to PLACE the robot on the table based on user inputs
     *
     * @param  X    The X coordinate specified by the user input for the robot
     * @Y			The Y coordinate specified by the user input for the robot
     * Display an error message if the robot is asked to move off the table
     */
	public static void placeRobot(int X, int Y)
	{
		if ((X <= 4 && X >= 0) && (Y <= 4 && Y >= 0)) //Prevents the user from making the robot fall off the table
		{
			x = X;
			y = Y;
		}
		else
			System.out.println("Oops! I will fall off the table if I move there \n");
	}
	
	/**
     * A method to rotate the robot 90 degrees to the left without changing its position
     *
     * @param  face  The current direction the robot is facing
     */	
	public static void turnLeft(String face)
	{
		//Turns the robot 90 degrees to the left based on the current direction it is facing
		if ("NORTH".contains(face))
			setPosition("WEST");
		else if ("EAST".contains(face))
			setPosition("NORTH");
		else if ("SOUTH".contains(face))
			setPosition("EAST");
		else if ("WEST".contains(face))
			setPosition("SOUTH");
	}
	
	/**
     * A method to rotate the robot 90 degrees to the right without changing its position
     *
     * @param  face  The current direction the robot is facing
     */	
	public static void turnRight(String face)
	{
		//Turns the robot 90 degrees to the left based on the current direction it is facing
		if ("NORTH".contains(face))
			setPosition("EAST");
		else if ("EAST".contains(face))
			setPosition("SOUTH");
		else if ("SOUTH".contains(face))
			setPosition("WEST");
		else if ("WEST".contains(face))
			setPosition("NORTH");

	}
	
	/**
     * A method to PLACE the robot with x y coordinates and face direction as specified by the user
     *
     * @param  position An array consisting of x and y coordinates and face direction
     * @param  found   Set to true if face is present in the directions[] array
     * @return true    if the position specified by the user is valid
     * @return false   if the position specified by the user is invalid
     */	
	public static boolean placeRobot(String[] position)
	{
		String[] directions = {"NORTH", "EAST", "WEST", "SOUTH"};
		boolean found = false;
		//Check if the direction specified by the user is in the directions[] array
		for (String s : directions)
		{
			if (position[2].contentEquals(s))
			{
				//place robot on the x and y coordinates
				placeRobot(Integer.parseInt(position[0]), Integer.parseInt(position[1]));
				//set the position with x and y coordinates and the face directions
				setPosition(position[2]);
				found = true;
				break;
			}
		}

		if (!found)
		{
			System.out.println("Invalid direction \n");
			return false;
		}
		return true;
	}
	
	/**
     * A method to report the position of the robot to the user
     *
     */	
	public static void reportPosition()
	{
		System.out.println(getPosition());
	}
	
	/**
     * A method to validate user commands and execute PLACE, MOVE, LEFT, RIGHT, REPORT
     *
     * @param  userInput[]  The arrayList consisting the user input
     * @param  command[]    The arrayList consisting the command (PLACE, MOVE, LEFT, RIGHT, REPORT)
     */	
	public static boolean validateInput(ArrayList<String> userInput)
	{

		String input = "";
		String pos = "";
		// Converting array list to String
		for (String i: userInput)
			input += i;
		// Splitting the string to separate the commands based on space delimiter
		String[] command = input.split(" ");
		
		switch(command[0]) // Check the first element of 
		{
		// Case when the command is PLACE
		case "PLACE":
					try
					{
					pos = command[1].toString(); // Converting array to String
					String[] temp2 = pos.split(","); // Splitting the string by comma delimiter
					// Check if the command is valid with correct coordinates and direction
					if(!placeRobot(temp2))
						return false;
					}
					// Catch exception if the PLACE command is invalid
					catch (Exception e)
					{
						System.out.println("PLACE command invalid \n");
						return false;
					}
		break;
		
		// Case when the command is MOVE
		case "MOVE": 
					String dir = getPosition();
					String[] facing = dir.split(","); // Get the current direction from the position
					// Move the robot one place in the direction it is facing
					if ("NORTH".contains(facing[2]))
						setY(1);
					else if ("EAST".contains(facing[2]))
						setX(1);
					else if ("SOUTH".contains(facing[2]))
						setY(-1);
					else if ("WEST".contains(facing[2]))
						setX(-1);						
					setPosition(facing[2]);	// Set the position based on the new x and y coordinates	
		break;
		
		// Case when the command is LEFT			
		case "LEFT": 
					String dir1 = getPosition(); // Get the current direction from the position
					String[] facing1 = dir1.split(",");
					// Turn the robot left by sending its current face as a parameter
					turnLeft(facing1[2]);
		break;
		
		// Case when the command is RIGHT
		case "RIGHT": 
					String dir11 = getPosition();// Get the current direction from the position
					String[] facing11 = dir11.split(",");
					// Turn the robot right by sending its current face as a parameter
					turnRight(facing11[2]);
		break;
		
		// Case when the command is REPORT
		case "REPORT": 
					reportPosition();
		break;
		
		// Case when the command is INVALID
		default: 
				System.out.print("Invalid Command \n");
				return false;
		}
		return true;
	}
	
	/**
     * Main method of the simulation to scan user input and execute commands on the robot
     *
     */	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in); // Scanner variable to record user inputs	
		boolean terminate = false; // Variable to terminate the console
		boolean placeFound = false;
		
		System.out.println("Welcome! I am a toy Robot! Command me to move around the table");
		System.out.println("I only obey the following commands" + "\n" + "1. PLACE X,Y,F (x & y coordinates with face direction)");
		System.out.println("2. MOVE (One step in the direction I am facing)");
		System.out.println("3. LEFT (Turn 90 degrees to my left)");
		System.out.println("4. RIGHT (Turn 90 degrees to my right)");
		System.out.println("5. REPORT (Report my current direction)");
		System.out.println("6. END (Stop your commands) \n");
		System.out.println("Note: The commands are case sensitive and please start with a valid PLACE command only \n");
		// Keep recording user inputs until condition fails
		while (!terminate)
		{
			ArrayList<String> userInput = new ArrayList<>();
			ArrayList<String> buffer = new ArrayList<>(); // Buffer arrayList 
			// A loop to keep recording multiple lines of user inputs 
			while(scan.hasNextLine())
			{
				String read = scan.nextLine();
				// Condition to terminate the console when user enters "END"
				if("END".equals(read))
				{
					terminate = true;
					break;
				}				
				String[] command = read.split(" "); // Split the user command
				// Condition to check if the first command is PLACE when userInput array is 0
				if((!("PLACE".contains(command[0]))) && userInput.size() == 0)
				{
					System.out.println("Please start with a PLACE command");
				}
				else
				{
					placeFound = true;
					userInput.add(read); // Add command to userInput array
					buffer.add(read);
					if(validateInput(buffer)) // Validate userInput										
						buffer.clear(); // Clear the buffer for new line of command from the user
					// Condition to reset the arrays if command is invalid
					else
					{
						if (!placeFound)
							userInput.clear();
						buffer.clear();
					}
				}
			}			
		}		
	}
}
