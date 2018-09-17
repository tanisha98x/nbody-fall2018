	

/**
 * @TanishaNalavadi
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;

public class NBody {
	

	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static double readRadius(String fname) throws FileNotFoundException  {//Uses a scanner and returns the radius of the planet (second line)
		Scanner s = new Scanner(new File(fname));
		double radius=0;
		int value=0;
		value= s.nextInt();
		radius= s.nextDouble();
		s.close();
		return radius;	
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static Body[] readBodies(String fname) throws FileNotFoundException {//creates an array with body objects
		
			Scanner s = new Scanner(new File(fname));
			Body[] bodies= new Body[s.nextInt()];
			s.nextDouble();
			for(int k=0; k < bodies.length; k++) {
				bodies[k]=new Body (s.nextDouble(), s.nextDouble(), 
							        		s.nextDouble(),s.nextDouble(),s.nextDouble(),s.next()); }	
			s.close();
			return bodies;
	}
			
	
	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = 1000000000;
		double dt = 1000000.0;
		
		String fname= "./data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}	
		
		Body[] bodies = readBodies(fname);
		double radius = readRadius(fname);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
	
		for(double t = 0.0; t < totalTime; t += dt) {//Creating a for loop that updates the xforces and yforces arrays that are used in the next for loop
			StdDraw.picture(0,0,"images/starfield.jpg");
			double[] xforces=new double[bodies.length];
			double[] yforces=new double[bodies.length];
			
			for (int k=0; k<bodies.length; k++) {
				xforces[k]=bodies[k].calcNetForceExertedByX(bodies);
				yforces[k]=bodies[k].calcNetForceExertedByY(bodies);	
			}
			
			for (int i=0;  i<bodies.length; i++) //Creating a for loop that calls the method update for each body
				{bodies[i].update (dt,  xforces[i],  yforces[i]);} 
						
			for (int s=0; s<bodies.length; s++) //Creating a for loop to 	iterate over each planet and draw it 
				{bodies[s].draw();}
			
			StdDraw.show(10);}
			
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}
}
