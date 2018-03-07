package dao;
public class SimpleTirage implements Tirage {

	@Override
	public String toString() {
		return "SimpleTirage [b1=" + b1 + ", b2=" + b2 + ", b3=" + b3 + ", b4=" + b4 + ", b5=" + b5 + ", e1=" + e1
				+ ", e2=" + e2 + "]";
	}

	private int b1;
	private int b2;
	private int b3;
	private int b4;
	private int b5;
	private int e1;
	private int e2;
	
	/**
	private String b1;
	private String b2;
	private String b3;
	private String b4;
	private String b5;
	private String e1;
	private String e2;
	 */
	
	// CONSTRUCTEURS
	public SimpleTirage() {

	}

	// METHODES

	// GET SET
	public int getB1() {
		return b1;
	}

	public void setB1(int b1) {
		this.b1 = b1;
	}

	public int getB2() {
		return b2;
	}

	public void setB2(int b2) {
		this.b2 = b2;
	}

	public int getB3() {
		return b3;
	}

	public void setB3(int b3) {
		this.b3 = b3;
	}

	public int getB4() {
		return b4;
	}

	public void setB4(int b4) {
		this.b4 = b4;
	}

	public int getB5() {
		return b5;
	}

	public void setB5(int b5) {
		this.b5 = b5;
	}

	public int getE1() {
		return e1;
	}

	public void setE1(int e1) {
		this.e1 = e1;
	}

	public int getE2() {
		return e2;
	}

	public void setE2(int e2) {
		this.e2 = e2;
	}

}
