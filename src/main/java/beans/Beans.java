package beans;

import java.io.Serializable;

/**
 * JavaBeansクラス
 * @author 馬場 健太朗
 */

public class Beans implements Serializable {
	/** javabeansなのでprivateで使用する変数を宣言  */
	private String title; //ゲームタイトル名
	private String myChara; //自キャラ
	private String yourChara; //相手キャラ
	private String point; //ピンポイントな対策
	private String cpoint; //全体的な対策

	/** デフォルトコンストラクタ */
	public Beans() {
	}

	/**
	 * home.jspで使用するコンストラクタ
	 * @param title 		ゲームのタイトル名
	 * @param myChara		自分の操作キャラ名
	 * @param yourChara	対策したいキャラ名
	 * @param point		相手をする上できつい所
	 * @param cpoint		全体的なキャラ対策
	 */
	public Beans(
			String title,
			String myChara,
			String yourChara,
			String point,
			String cpoint) {
		this.setTitle(title);
		this.setMyChara(myChara);
		this.setYourChara(yourChara);
		this.setPoint(point);
		this.setCpoint(cpoint);
	}

	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title セットする title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return myChara
	 */
	public String getMyChara() {
		return myChara;
	}

	/**
	 * @param myChara セットする myChara
	 */
	public void setMyChara(String myChara) {
		this.myChara = myChara;
	}

	/**
	 * @return yourChara
	 */
	public String getYourChara() {
		return yourChara;
	}

	/**
	 * @param yourChara セットする yourChara
	 */
	public void setYourChara(String yourChara) {
		this.yourChara = yourChara;
	}

	/**
	 * @return point
	 */
	public String getPoint() {
		return point;
	}

	/**
	 * @param point セットする point
	 */
	public void setPoint(String point) {
		this.point = point;
	}

	/**
	 * @return cpoint
	 */
	public String getCpoint() {
		return cpoint;
	}

	/**
	 * @param cpoint セットする cpoint
	 */
	public void setCpoint(String cpoint) {
		this.cpoint = cpoint;
	}

}
