package beans;

/**
 * ・コンストラクタ<br>
 *   <ul>
 *     <li>{@link #Title()} - デフォルトコンストラクタ</li>
 *     <li>{@link #Title(int, String, String[])} - タイトル登録のコンストラクタ</li>
 *     <li>{@link #Title(int, String)} - タイトル検索のコンストラクタ</li>
 *   </ul>
 * <br>
 * ・メソッド<br>
 *   <ul>
 *     <li>{@link #toString()} - オブジェクトの文字列表現を取得</li>
 *     <li>{@link #getTitles()} - 登録されているタイトルリストを取得</li>
 *     <li>{@link #setTitles(String[])} - 登録されているタイトルリストを設定</li>
 *     <li>{@link #getTitleId()} - タイトルIDを取得</li>
 *     <li>{@link #setTitleId(int)} - タイトルIDを設定</li>
 *     <li>{@link #getTitle()} - タイトル名を取得</li>
 *     <li>{@link #setTitle(String)} - タイトル名を設定</li>
 *   </ul>
 * 
 * @author 馬場 健太朗
 */
public class Title {
    
    private int titleId;        // タイトルID
    private String title;       // タイトル名
    private String[] titles;    // 登録されているタイトルリスト
    
    /**
     * デフォルトコンストラクタ
     */
    public Title() {}
    
    /**
     * タイトル登録のコンストラクタ
     * 
     * @param titleId タイトルID
     * @param title タイトル名
     * @param titles 登録されているタイトルリスト
     */
    public Title(
            int titleId,
            String title,
            String[] titles
            ) {
        this.titleId = titleId;
        this.title = title;
        this.setTitles(titles);
    }
    
    /**
     * タイトル検索のコンストラクタ
     * 
     * @param titleId タイトルID
     * @param title タイトル名
     */
    public Title(
            int titleId,
            String title
            ) {
        this.titleId = titleId;
        this.title = title;
    }
    
    /**
     * オブジェクトの文字列表現を取得します。
     * 
     * @return タイトルIDとタイトル名を含む文字列
     */
    @Override
    public String toString() {
        return "タイトルID=" + titleId + ", タイトル名='" + title + '\'';
    }
    
    /**
     * 登録されているタイトルリストを取得します。
     * 
     * @return 登録されているタイトルリスト
     */
    public String[] getTitles() {
        return titles;
    }

    /**
     * 登録されているタイトルリストを設定します。
     * 
     * @param titles 登録されているタイトルリスト
     */
    public void setTitles(String[] titles) {
        this.titles = titles;
    }
    
    /**
     * タイトルIDを取得します。
     * 
     * @return タイトルID
     */
    public int getTitleId() {
        return titleId;
    }
    
    /**
     * タイトルIDを設定します。
     * 
     * @param titleId タイトルID
     */
    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }
    
    /**
     * タイトル名を取得します。
     * 
     * @return タイトル名
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * タイトル名を設定します。
     * 
     * @param title タイトル名
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
