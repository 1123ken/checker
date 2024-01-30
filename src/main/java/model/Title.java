package model;

/**
 * タイトルを
 * @author 7d03
 *
 */
public class Title {
    private int titleId;
    private String title;
    private String[] titles;
    
    /**
     * デフォルトコンストラクタ
     */
    public Title() {}
    
    /**
     * タイトル登録のコンストラクタ
     * @param titleId
     * @param title
     * @param titles
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
     * @param titleId
     * @param title
     */
    public Title(
    		int titleId,
    		String title
    		) {
        this.titleId = titleId;
        this.title = title;
    }
    
	/**
	 * タイトルを文字列として取得する
	 */
	@Override
    public String toString() {
        return "タイトルID=" + titleId + ", タイトル名='" + title + '\'';
    }
	
	/**
	 * @return titlesリストをゲット
	 */
	public String[] getTitles() {
		return titles;
	}

	/**
	 * @param titlesリストをセットする
	 */
	public void setTitles(String[] titles) {
		this.titles = titles;
	}
	
	/**
	 * @return titleIdをゲット
	 */
	public int getTitleId() {
		return titleId;
	}
	
	/**
	 * @param titleId をセット
	 */
	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}
	
	/**
	 * @return title名をゲット
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @param title タイトル名をセット
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}

