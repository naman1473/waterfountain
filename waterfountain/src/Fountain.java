//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
    //
    // Title:    P02 Water Fountain
    // Course:   CS 300 Spring 2022
    //
    // Author:   Naman Parekh
    // Email:    ncparekh@wisc.edu
    // Lecturer: Hobbes LeGault 
    //
    //////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
    //
    // Partner Name:    (name of your pair programming partner)
    // Partner Email:   (email address of your programming partner)
    // Partner Lecturer's Name: (name of your partner's lecturer)
    // 
    // VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
    //   ___ Write-up states that pair programming is allowed for this assignment.
    //   ___ We have both read and understand the course Pair Programming Policy.
    //   ___ We have registered our team prior to the team registration deadline.
    //
    ///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
    //
    // Persons: None (identify each by name and describe how they helped)
    // Online Sources: None (identify each by URL and describe how it helped)
    //
    ///////////////////////////////////////////////////////////////////////////////

import java.util.Random;
import processing.core.PImage;
import java.io.File;


/**
* This class implements various methods to build a water fountain using a graphical implementation of 
* a particle system.
* <p>
* @return true when all the test cases in the method class run successfully
*/

public class Fountain {
	
	private static Random randGen;
	private static PImage fountainImage;
	private static int positionX;
	private static int positionY;
	public static float velocityX;
    public static float velocityY;
    private static int startColor; // blue: Utility.color(23,141,235)
	private static int endColor; // lighter blue: Utility.color(23,200,255)
	
	private static Droplet[] droplets;
	
	/**
	 * This method initializes all the fields defined at the top of the class and makes a new droplet's array
	 * with 800 null values
	 * <p>
	 * 
	 */	
	
	public static void setup() {
		
		testUpdateDroplet();
        testRemoveOldDroplets();

        if (testUpdateDroplet() == false) {
            System.out.println("UpdateDroplet() failed");
        }

        if (testRemoveOldDroplets() == false) {
            System.out.println("removeOldDroplets() failed");
        }
		
        randGen = new Random();
        startColor = Utility.color(23,141,235);
		endColor = Utility.color(23,200,255);
		positionX = Utility.width()/2;
		positionY = Utility.height()/2;
		
		
		fountainImage = Utility.loadImage("images" + File.separator + "fountain.png");
		
		droplets = new Droplet[800];
		
		}
	
	
	/**
	 * This method draws the background colour, image and colour of the droplets displayed on the application 
	 * display window 
	 * <p>
	 * 
	 */	
	
	public static void draw() {
		
		createNewDroplets(10);	
		
		Utility.background(Utility.color(253,245,230));
	    Utility.fill(Utility.color(23,141,235));
	    Utility.image(fountainImage, positionX, positionY);
		
		for (int i = 0; i < droplets.length; i++) {
			if (droplets[i] != null)  {
					updateDroplet(i);
				}
			}
				
		removeOldDroplets(80);
		
	}
	
	/**
	 * This method updates the age, position and the y-velocity of the droplet at a given index
	 * <p>
	 * @param index of a particular droplet in the array
	 * 
	 */	
	
	private static void updateDroplet(int index) {
		
		Utility.circle(droplets[index].getPositionX(), droplets[index].getPositionY(), droplets[index].getSize());
		Utility.fill(Utility.color(23, 141, 235), droplets[index].getTransparency());
		
		
		droplets[index].setVelocityY(droplets[index].getVelocityY() + 0.3f);
		
		droplets[index].setPositionX(droplets[index].getPositionX() + droplets[index].getVelocityX());
		
		droplets[index].setPositionY(droplets[index].getPositionY() + droplets[index].getVelocityY());
		
		droplets[index].setAge(droplets[index].getAge() + 1);
		
	}
	
	/**
	 * This method uses a for loop to iterate over the array, looking for null references. As soon as it finds
	 * null references it changes it to reference a new droplet object. It also updates the properties of each droplet. 
	 * <p> 
	 * @param numOfNewDropletsCreated - number of droplets created
	 *  
	 */	
	
	private static void createNewDroplets(int numOfNewDropletsCreated) {
		
		int numOfDroplets = 0;
		
		for (int i = 0; i < droplets.length; i++) {
			
			if (droplets[i] == null) {
				++numOfDroplets;
			    droplets[i] = new Droplet();
				
				
				droplets[i].setPositionX(positionX + randGen.nextFloat() * 6 - 3);
				droplets[i].setPositionY(positionY + randGen.nextFloat() * 6 - 3); 
				float sizeOfDroplet = (randGen.nextFloat() * 7 + 4);
				droplets[i].setSize(sizeOfDroplet);
			    float velocityX = (randGen.nextFloat() * 2 - 1);
				droplets[i].setVelocityX(velocityX);
				float velocityY = (randGen.nextFloat() * 5 - 10);
				droplets[i].setVelocityY(velocityY);
				int ageOfDroplet = (randGen.nextInt(40));
				droplets[i].setAge(ageOfDroplet);
				int transparencyOfDroplet = (randGen.nextInt() * 96 + 32);
				droplets[i].setTransparency(transparencyOfDroplet);
				int colorOfDroplet = Utility.lerpColor(startColor, endColor, randGen.nextFloat());
				droplets[i].setColor(colorOfDroplet);
				
			}
			
			if (numOfDroplets >= numOfNewDropletsCreated) {
				break;
			}
		
		}
				
	}

	
	/**
	 * This method checks if a droplet's age is greater than 88 and if it is, the reference of that particular droplet
	 * in the array is changed to null
	 * @param maxAge - value of the maximum age a droplet can have (88).
	 *  
	 */	
	
	private static void removeOldDroplets(int maxAge) {
	
		for (int i = 0; i < droplets.length; i++) {
			if (droplets[i] != null) {
				if (droplets[i].getAge() > maxAge) {
					droplets[i] = null;
				}
				
			}
		}
		
	}
	
	/**
	 * This method moves the fountain's positionX and positionY based on the position of the mouse
	 *  
	 */	
	
	
	public static void mousePressed() {
		
		Fountain.positionX = Utility.mouseX();
		Fountain.positionY = Utility.mouseY();
				
	}
	
	/**
	 * This method allows the user to take a screenshot whenever 's' or 'S' key is pressed
	 *  @param keyPressed - stores the value of the key pressed
	 */
	
	
	public static void keyPressed(char keyPressed) {
		
		if (keyPressed == 's' || keyPressed == 'S') {
			Utility.save("screenshot.png");
		}
		
	}
	
	/**
	* This tester initializes the droplets array to hold at least three droplets.
	* Creates a single droplet at position (3,3) with velocity (-1,-2). Then checks
	* whether calling updateDroplet() on this droplet’s index correctly results in
	* changing its position to (2.0, 1.3).
	*
	* @return true when no defect is found, and false otherwise
	*/
	
	private static boolean testUpdateDroplet() {
		
		droplets = new Droplet[1];
		droplets[0] = new Droplet(3, 3, 4, 4);
        droplets[0].setVelocityX(-1);
        droplets[0].setVelocityY(-2);


        updateDroplet(0);

        if (Math.abs(droplets[0].getPositionX() - 2.0) < 0.001 && 
        		(Math.abs(droplets[0].getPositionY() - 1.3) < 0.001)) {
            return true;
        }

        return false; 
	}
	
	/**
	* This tester initializes the droplets array to hold at least three droplets.
	* Calls removeOldDroplets(6) on an array with three droplets (two of which have
	* ages over six and another that does not). Then checks whether the old droplets
	* were removed and the young droplet was left alone.
	*
	* @return true when no defect is found, and false otherwise
	*/
	
	private static boolean testRemoveOldDroplets() {
		
		droplets = new Droplet[3];
        droplets[0] = new Droplet();
        droplets[1] = new Droplet();
        droplets[2] = new Droplet();
        droplets[0].setAge(9);
        droplets[1].setAge(11);
        droplets[2].setAge(2);

        removeOldDroplets(6);

        if (droplets[0] == null && droplets[1] == null && droplets[2].getAge() == 2) {
            return true;
        }
	
        return false; 
	}
	
	public static void main(String[] args) {
		Utility.runApplication();
		

	}
	

}




