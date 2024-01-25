package model;

public class Title {
    private int titleId;
    private String title;
    private String[] titles;
    
    public Title() {}
    
    public Title(
    		int titleId,
    		String title,
    		String[] titles
    		) {
        this.titleId = titleId;
        this.title = title;
        this.titles = titles;
    }
    
    public Title(
    		int titleId,
    		String title
    		) {
        this.titleId = titleId;
        this.title = title;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public String[] getTitles() {
		return titles;
	}

	public void setTitles(String[] titles) {
		this.titles = titles;
	}
	
	@Override
    public String toString() {
        return "タイトルID=" + titleId + ", タイトル名='" + title + '\'';
    }
}

