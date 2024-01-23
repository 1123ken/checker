package beans;

import java.io.Serializable;

public class Beans implements Serializable{
	
	private String title;		//ゲームタイトル名
	private int worl;			//勝敗　winner or loserの略
	private String myChara;		//自キャラ
	private String yourChara;	//相手キャラ
	private String point;		//ピンポイントな対策
	private String cpoint;		//全体的な対策
	
	//空のコンストラクタ
	private Beans() {}
	
	//home画面表示用のコンストラクタ
	private Beans(
			 String title,		//ゲームタイトル名
			 int worl,			//勝敗　winner or loserの略
			 String myChara,		//自キャラ
			 String yourChara,	//相手キャラ
			 String point,		//ピンポイントな対策
			 String cpoint
			) {
		this.title= title;
		this.worl= worl;
		this.myChara= myChara;
		this.yourChara= yourChara;
		this.point= point;
		this.cpoint= cpoint;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWorl() {
		return worl;
	}

	public void setWorl(int worl) {
		this.worl = worl;
	}

	public String getMyChara() {
		return myChara;
	}

	public void setMyChara(String myChara) {
		this.myChara = myChara;
	}

	public String getYourChara() {
		return yourChara;
	}

	public void setYourChara(String yourChara) {
		this.yourChara = yourChara;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getCpoint() {
		return cpoint;
	}

	public void setCpoint(String cpoint) {
		this.cpoint = cpoint;
	}
}
