package dbscan;

import java.sql.Date;

public class CrimeEntity {

	/**
	 * @param args
	 */
	String mCrimeID;
	String mCrimeDate;
	String mBlock;
	String mCrimeDescription;
	String mCrimeYear;
	String mCrimeLatitude;
	String mCrimelongitude;
	Point mCrimePoint;
	
	public CrimeEntity(String crimeID, String crimeDate, String block, String crimeDescription,
			String year, Point point) {
		mCrimeID = crimeID;
		mCrimeDate = mCrimeDate;
		mBlock = block;
		mCrimeDescription = crimeDescription;
		mCrimeYear = year;
		mCrimePoint = point;
	}
	
	public String getmCrimeID() {
		return mCrimeID;
	}

	public void setmCrimeID(String mCrimeID) {
		this.mCrimeID = mCrimeID;
	}

	public String getmCrimeDate() {
		return mCrimeDate;
	}

	public void setmCrimeDate(String mCrimeDate) {
		this.mCrimeDate = mCrimeDate;
	}

	public String getmBlock() {
		return mBlock;
	}

	public void setmBlock(String mBlock) {
		this.mBlock = mBlock;
	}

	public String getmCrimeDescription() {
		return mCrimeDescription;
	}

	public void setmCrimeDescription(String mCrimeDescription) {
		this.mCrimeDescription = mCrimeDescription;
	}

	public String getmCrimeYear() {
		return mCrimeYear;
	}

	public void setmCrimeYear(String mCrimeYear) {
		this.mCrimeYear = mCrimeYear;
	}

	public String getmCrimeLatitude() {
		return mCrimeLatitude;
	}

	public void setmCrimeLatitude(String mCrimeLatitude) {
		this.mCrimeLatitude = mCrimeLatitude;
	}

	public Point getmCrimePoint() {
		return mCrimePoint;
	}

	public void setmCrimePoint(Point mCrimePoint) {
		this.mCrimePoint = mCrimePoint;
	}
	
}
