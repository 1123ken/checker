package beans;

import java.io.Serializable;

public class Beans implements Serializable{
	
	private String title;		//ゲームタイトル名
	private String myChara;		//自キャラ
	private String yourChara;	//相手キャラ
	private String point;		//ピンポイントな対策
	private String cpoint;		//全体的な対策
	
	//home画面表示用のコンストラクタ
	
	public Beans() {}
	
	public Beans(
            String title,
            String myChara,
            String yourChara,
            String point,
            String cpoint
    ) {
        this.title = title;
        this.myChara = myChara;
        this.yourChara = yourChara;
        this.point = point;
        this.cpoint = cpoint;
    }
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
