package beans;

import java.io.Serializable;

/**
 * ・フィールド<br>
 *   <ul>
 *     <li>{@code title} - ゲームのタイトル名</li>
 *     <li>{@code myChara} - 自キャラ</li>
 *     <li>{@code yourChara} - 対策したい相手キャラ名</li>
 *     <li>{@code point} - 相手をする上できつい所を示すポイント</li>
 *     <li>{@code cpoint} - 全体的な対策</li>
 *   </ul>
 * <br> 
 * ・コンストラクタ<br>
 *   <ul>
 *     <li>{@link #Beans()} - デフォルトコンストラクタ</li>
 *     <li>{@link #Beans(String, String, String, String, String)} - パラメータつきコンストラクタ</li>
 *   </ul>
 * <br>
 * ・メソッド<br>
 *   <ul>
 *     <li>{@link #getTitle()} - ゲームのタイトル名を取得</li>
 *     <li>{@link #setTitle(String)} - ゲームのタイトル名を設定</li>
 *     <li>{@link #getMyChara()} - 自分の操作キャラ名を取得</li>
 *     <li>{@link #setMyChara(String)} - 自分の操作キャラ名を設定</li>
 *     <li>{@link #getYourChara()} - 対策したい相手キャラ名を取得</li>
 *     <li>{@link #setYourChara(String)} - 対策したい相手キャラ名を設定</li>
 *     <li>{@link #getPoint()} - 相手をする上できつい所を示すポイントを取得</li>
 *     <li>{@link #setPoint(String)} - 相手をする上できつい所を示すポイントを設定</li>
 *     <li>{@link #getCpoint()} - 全体的なキャラ対策を示すポイントを取得</li>
 *     <li>{@link #setCpoint(String)} - 全体的なキャラ対策を示すポイントを設定</li>
 *   </ul>
 * 
 * @author 馬場 健太朗
 */
public class Beans implements Serializable {
	
	/**
	 * ゲームのタイトル名
	 */
    private String title;		//ゲームのタイトル名
    
    /**
     * 自分の操作キャラクター名
     */
    private String myChara;		//自分の操作キャラ名
    
    /**
     * 対策したい相手のキャラクター名
     */
    private String yourChara;	//対策したい相手キャラ名
    
    /**
     * 相手をする上でキツイところ
     */
    private String point;		//相手をする上できつい所を示すポイント
    
    /**
     * 全体的な対策を書くところ
     */
    private String cpoint;		//全体的なキャラ対策を示すポイント

    /**
     * デフォルトコンストラクタ
     */
    public Beans() {
    }

    /**
     * パラメータつきコンストラクタ
     * 
     * @param title     ゲームのタイトル名
     * @param myChara   自分の操作キャラ名
     * @param yourChara 対策したい相手キャラ名
     * @param point     相手をする上できつい所を示すポイント
     * @param cpoint    全体的なキャラ対策を示すポイント
     */
    public Beans(String title, String myChara, String yourChara, String point, String cpoint) {
        this.setTitle(title);
        this.setMyChara(myChara);
        this.setYourChara(yourChara);
        this.setPoint(point);
        this.setCpoint(cpoint);
    }

    /**
     * ゲームのタイトル名を取得します。
     * 
     * @return ゲームのタイトル名
     */
    public String getTitle() {
        return title;
    }

    /**
     * ゲームのタイトル名を設定します。
     * 
     * @param title ゲームのタイトル名
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 自分の操作キャラ名を取得します。
     * 
     * @return 自分の操作キャラ名
     */
    public String getMyChara() {
        return myChara;
    }

    /**
     * 自分の操作キャラ名を設定します。
     * 
     * @param myChara 自分の操作キャラ名
     */
    public void setMyChara(String myChara) {
        this.myChara = myChara;
    }

    /**
     * 対策したい相手キャラ名を取得します。
     * 
     * @return 対策したい相手キャラ名
     */
    public String getYourChara() {
        return yourChara;
    }

    /**
     * 対策したい相手キャラ名を設定します。
     * 
     * @param yourChara 対策したい相手キャラ名
     */
    public void setYourChara(String yourChara) {
        this.yourChara = yourChara;
    }

    /**
     * 相手をする上できつい所を示すポイントを取得します。
     * 
     * @return ピンポイントな対策ポイント
     */
    public String getPoint() {
        return point;
    }

    /**
     * 相手をする上できつい所を示すポイントを設定します。
     * 
     * @param point ピンポイントな対策ポイント
     */
    public void setPoint(String point) {
        this.point = point;
    }

    /**
     * 全体的なキャラ対策を示すポイントを取得します。
     * 
     * @return 全体的なキャラ対策ポイント
     */
    public String getCpoint() {
        return cpoint;
    }

    /**
     * 全体的なキャラ対策を示すポイントを設定します。
     * 
     * @param cpoint 全体的なキャラ対策ポイント
     */
    public void setCpoint(String cpoint) {
        this.cpoint = cpoint;
    }
}
