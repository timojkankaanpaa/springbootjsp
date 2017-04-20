package vsg.model;

import java.util.ArrayList;

public class LocationFactory {
	public static void main(String[] args){
		//ArrayList<MyLocation> aList = getPoints(new MyLocation(21.57,63.09), 0.01);
		MyLocation a = new MyLocation(21.553115844726562, 63.089553465232996);
		MyLocation b = new MyLocation(21.64958953857422, 63.109203272591294);
		MyLocation substation = calcSubstationPosition(a, b,0.01);
		System.out.println("Voimalaitos:(" + a.getX() + "," + a.getY() + "), kuluttaja (" + b.getX() + ", " +b.getY() + "), ala-asema ("+ substation.getX() + ", " + substation.getY() + ")");
		boolean isOnLine = test(a, b, substation);
		System.out.println(isOnLine);
		
	}
	
	/* Returns 12 points around the aPint with the given radius */
	public static ArrayList getPoints(MyLocation aLocation, double radius){
		//clock zero
		MyLocation xLocation = new MyLocation();
		//kello on nolla
		int angle =0;
		System.out.println("\"coordinates\": [[");
		for(angle=0;angle<361;angle=angle+30){
			double x=aLocation.getX() + Math.sin(Math.toRadians(angle))*radius;
			double y=aLocation.getY() + Math.cos(Math.toRadians(angle))*radius*0.45;
			if(angle!=360)
				System.out.println("["+ x +"," +y +"],");
			else
				System.out.println("["+ x +"," +y +"]");
		}
		System.out.println("]]");
		return null;
	}
	
	public static double getAngle(MyLocation a, MyLocation b){
		double kulma = Math.toDegrees(Math.atan2((b.getY()-a.getY()),(b.getX()-a.getX())));
		return kulma;
	}
	
	public static MyLocation calcSubstationPosition(MyLocation _a, MyLocation _b, double distance){
		//Let's calculate in what angle the b is compared to a
		double angle = getAngle(_a,_b);
		System.out.println("Kulma " + angle);
		System.out.println("Delta x on " +Math.cos(Math.toRadians(angle))*distance);
		System.out.println("Delta y on " +Math.sin(Math.toRadians(angle))*distance);
		double x = _a.getX() + Math.cos(Math.toRadians(angle))*distance;
		double y= _a.getY()  + Math.sin(Math.toRadians(angle))*distance;
		return new MyLocation(x,y);
	}
	
	public static boolean test(MyLocation powerPlant, MyLocation consumer, MyLocation substation){
		//f(x)=((y2-y1)/(x2-x1))*x-x1*((y2-y1)/(x2-x1))+y1
		//x1,y1 voimalaitos, x2, y2 käyttöpaikka, x, y substation
		double y = ((consumer.getY()-powerPlant.getY())/(consumer.getX()-powerPlant.getX()))*substation.getX()-powerPlant.getX()*((consumer.getY()-powerPlant.getY())/(consumer.getX()-powerPlant.getX()))+powerPlant.getY();
		System.out.println("Testattu y: " + y);
		if(y==substation.getY())
			return true;
		else
			return false;
	}
}

