package dbscan;

import java.awt.Label;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class DBScanMainClass {

	/**
	 * @param args
	 * 
	 */
	static ArrayList<String> valuesCSV = new ArrayList<String>();
    static Vector<Point> hset = new Vector<Point>();
    static Vector<CrimeEntity> crimeEntityList = new Vector<CrimeEntity>();
    public static Vector<List> trl = new Vector<List>();
	
	public static ArrayList<String> crunchifyCSVtoArrayList(String crunchifyCSV) {
        ArrayList<String> crunchifyResult = new ArrayList<String>();
        
        if (crunchifyCSV != null) {
            String[] splitData = crunchifyCSV.split("\\s*,\\s*");
            for (int i = 0; i < splitData.length; i++) {
                if (!(splitData[i] == null) || !(splitData[i].length() == 0)) {
                    crunchifyResult.add(splitData[i].trim());
                }
            }
        }
        
        return crunchifyResult;
    }
	
	public static Vector<Point> getPointList() {
		Vector<Point> tempVect = new Vector<Point>();
		tempVect.addAll(hset);
		return tempVect;
	}
	
	public static void applyDBAlgo() throws Exception {
		trl.add(dbscan.applyDbscan());
		double centroid[] = new double[2];
		double x = 0;
        double y = 0;
        double z = 0;
		BufferedWriter output = null;
	    File file = new File("D:\\SE project\\example.txt");
	    FileOutputStream is = new FileOutputStream(file);
	    OutputStreamWriter osw = new OutputStreamWriter(is);
	    Writer writer = new BufferedWriter(osw);
         
		int index1 = 0;
		//System.out.print("no of clusters :"+trl.size());
		for(List l : trl){
			System.out.print("no of clusters"+l.size());
			for(int j=0;j<l.size();j++) {
				//System.out.println("cluster size "+l.size());	
				Vector<Point> clusterVector = (Vector<Point>) l.get(j);
				System.out.println("Cluster  :" + (index1 + 1));
				writer.write("Cluster  :" + (index1 + 1));
				writer.write("\n");
				//Iterator<Point> j = l.iterator();
				for (int i=0; i<clusterVector.size();i++) {
					Point w = clusterVector.get(i);
					double latitude = w.getX() * Math.PI / 180;
		            double longitude = w.getY() * Math.PI / 180;

		            x += Math.cos(latitude) * Math.cos(longitude);
		            y += Math.cos(latitude) * Math.sin(longitude);
		            z += Math.sin(latitude);
					System.out.println((w.getX() + "," + w.getY()));
					writer.write(w.getX() + "," + w.getY());
					writer.write("\n");
				}
				int total = clusterVector.size();
				x = x / total;
		        y = y / total;
		        z = z / total;
		        double centralLongitude = Math.atan2(y, x);
		        double centralSquareRoot = Math.sqrt(x * x + y * y);
		        double centralLatitude = Math.atan2(z, centralSquareRoot);
		        centralLatitude = centralLatitude * 180 / Math.PI;
		        centralLongitude = centralLongitude * 180 / Math.PI;
		        writer.write("Centroid of Cluster: "+(index1 + 1)+" "+centralLatitude+" "+centralLongitude);
		        writer.write("\n");
				System.out.println("***************");
				writer.write("***************");
				writer.write("\n");
				index1++;

			}
						
		}
		writer.flush();
		writer.close();
	}
	
	public static void main(String[] args) throws Exception {
        
        BufferedReader crunchifyBuffer = null;
        
         
        try {
            String crunchifyLine;
            
            crunchifyBuffer = new BufferedReader(new FileReader("D:\\SE project\\tempCsv.txt"));
            
            // How to read file in java line by line?
            while ((crunchifyLine = crunchifyBuffer.readLine()) != null) {
                
                valuesCSV.add(crunchifyLine);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (crunchifyBuffer != null) crunchifyBuffer.close();
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }
        
        for(String csvString : valuesCSV) {
        	ArrayList<String> tempList = crunchifyCSVtoArrayList(csvString);
        	if(tempList.size() > 20) {
        		Double latitude = Double.parseDouble(tempList.get(19));
            	Double longitude = Double.parseDouble(tempList.get(20));
            	Point pt = new Point(latitude, longitude);
            	CrimeEntity crimeEntity = new CrimeEntity(tempList.get(0), tempList.get(2), tempList.get(3),
            			tempList.get(5), tempList.get(17), pt);
            	crimeEntityList.add(crimeEntity);
            	hset.add(pt);
        	}
        }
        
        applyDBAlgo();
    }
    
    

}
