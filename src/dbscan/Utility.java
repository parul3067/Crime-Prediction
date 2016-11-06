package dbscan;

import java.util.Iterator;
import java.util.Vector;

public class Utility{
	
	
	public static Vector<Point> VisitList = new Vector<Point>();

	public static double getDistance (Point p, Point q)
	{

		double dx = p.getX()-q.getX();

		double dy = p.getY()-q.getY();

		double distance = Math.sqrt (dx * dx + dy * dy);

		return distance;

	}
	
	public static double getDistanceFromLatLong (Point p, Point q)
	{

		final int R = 6371; // Radious of the earth
        Double lat1 = p.getX();
        Double lon1 = p.getY();
        Double lat2 = q.getX();
        Double lon2 = q.getY();
        Double latDistance = toRad(lat2-lat1);
        Double lonDistance = toRad(lon2-lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
                   Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * 
                   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double distance = R * c;
        
        return distance;
	}
	
	private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }
	
/**
neighbourhood points of any point p
**/


	public static Vector<Point> getNeighbours(Point p)
	{
		Vector<Point> neigh =new Vector<Point>();
		Iterator<Point> points = dbscan.pointList.iterator();
		while(points.hasNext()){
				Point q = points.next();
				//System.out.println("Distance between points "+getDistanceFromLatLong(p,q));
				if(getDistanceFromLatLong(p,q)<= dbscan.e){
				neigh.add(q);
				}
		}
		return neigh;
	}

	public static void Visited(Point d){
	VisitList.add(d);
	
	}

	public static boolean isVisited(Point c)
	{
		if (VisitList.contains(c))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static Vector<Point> Merge(Vector<Point> a,Vector<Point> b)
	{
	
	Iterator<Point> it5 = b.iterator();
	while(it5.hasNext()){
		Point t = it5.next();
		if (!a.contains(t) ){
			a.add(t);
		}
	}
	return a;
	}



//  Returns PointsList to DBscan.java 

	/*public static Vector<Point> getList() {
	
	Vector<Point> newList =new Vector<Point>();
	newList.clear();
	newList.addAll(Gui.hset);
	return newList;
	}		
*/
	public static Boolean equalPoints(Point m , Point n) {
	if((m.getX()==n.getX())&&(m.getY()==n.getY()))
		return true;
	else
		return false;
	}	

	}







