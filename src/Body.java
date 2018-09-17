
public class Body {
	private double myXPos; //current x position
	private double myYPos; //current y position
	private double myXVel; //velocity along the x axis
	private double myYVel; //velocity along the y axis
	private double myMass; //mass of the planet
	private String myFileName; //name of the file to be accessed 
	
	public double getX(){//get method to access the private instance variable myXPos
		return myXPos;
	}
	
	public double getY(){//get method to access the private instance variable myYPos
		return myYPos;
	}
	
	public double getXVel(){//get method to access the private instance variable myXVel
		return myXVel;
	}
	
	public double getYVel(){//get method to access the private instance variable myYVel
		return myYVel;
	}
	
	public double getMass(){//get method to access the private instance variable myMass
		return myMass;
	}
	
	public String getName() {//get method to access the private instance variable myFileName
		return myFileName;
	}

	public Body(double xp, double yp, double xv, double yv, double mass, String filename ) {//constructor
		myXPos=xp;
		myYPos=yp;
		myXVel=xv;
		myYVel=yv;
		myMass=mass;
		myFileName=filename;}
			
	public Body(Body b) {//constructor
		myXPos=b.myXPos;
		myYPos=b.myYPos;
		myXVel=b.myXVel;
		myYVel=b.myYVel;
		myMass=b.myMass;
		myFileName=b.myFileName;
	}
	
	public double calcDistance(Body b) {//method that calculates the distance between two bodies using their given coordinates and the distance formula
		double deltax= this.myXPos-b.myXPos;
		double deltaxsq= deltax*deltax;
		double deltay= this.myYPos-b.myYPos;
		double deltaysq= deltay*deltay;
		return Math.sqrt(deltaysq+deltaxsq);
	}
	
	public double calcForceExertedBy(Body b) {//method calculates and returns the force exerted on this body by the body specified by the parameter
		double numerator= b.myMass*this.myMass*6.67*Math.pow(10,-11);
		double denominator= Math.pow(this.calcDistance(b), 2);
		return numerator/denominator;
	}
	
	public double calcForceExertedByX(Body b) {//method calculates the x component of the force
		double numerator= this.calcForceExertedBy(b)*(b.myXPos - this.myXPos);
		double denominator= this.calcDistance(b);
		return numerator/denominator;	
	}
	
	public double calcForceExertedByY(Body b) {//method calculates the y component of the force
		double numerator= this.calcForceExertedBy(b)*(b.myYPos - this.myYPos);
		double denominator= this.calcDistance(b);
		return numerator/denominator;
	}
	
	public double calcNetForceExertedByX(Body [] bodies) {//method returns the total/net force exerted on this body by all the bodies in the array parameter
		double netforce=0;
		for (Body b: bodies) {
			if (!b.equals(this)) {
				netforce=netforce+ this.calcForceExertedByX(b);
			}
		}
		return netforce;
	}
	
	public double calcNetForceExertedByY(Body [] bodies) {//method returns the total/net force exerted on this body by all the bodies in the array parameter
		double netforce=0;
		for (Body b: bodies) {
			if (!b.equals(this)) {
				netforce=netforce+ this.calcForceExertedByY(b);
			}
		}
		return netforce;
	}
 
	public void update(double deltaT, double xforce, double yforce) {//method that updates the values of the x and y coordinates and velocities using the time step provided
		double ax=xforce/this.myMass;
		double ay=yforce/this.myMass;
		double nvx= this.myXVel+(deltaT*ax);
		double nvy=this.myYVel+(deltaT*ay);
		double nx= this.myXPos + (deltaT*nvx);
		double ny= this.myYPos + (deltaT*nvy);
		this.myXPos=nx;
		this.myYPos=ny;
		this.myXVel=nvx;
		this.myYVel=nvy;

	}
	
	public void draw(){//creating a method for drawing a body
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
		
}
	
	
	
	
	

