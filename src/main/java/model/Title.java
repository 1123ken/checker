package model;

public class Title {
    private int titleId;
    private String title;

    public Title(int titleId, String title) {
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
}
