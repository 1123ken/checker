package beans;

import java.util.List;

//titleIdから情報を取得するbeans

public class GameCharacter {
	private int titleId; //タイトルID
	private String selectedTitle; //選択したタイトル名
	private String[] Titles; //登録されているタイトルリスト
	private String[] characters; //タイトルに登録されているキャラクターリスト
	private int characterId;
	private String characterName;

	//デフォルトコンストラクタ
	public GameCharacter() {
	}

	// コンストラクタ
	public GameCharacter(
			int titleId,
			String selectedtitle,
			String[] Titles,
			String[] characters,
			int characterId,
			String characterName) {
		this.setTitleId(titleId);
		this.setSelectedTitle(selectedtitle);
		this.setTitles(Titles);
		this.setCharacters(characters);
		this.setCharacterId(characterId);
		this.setCharacterName(characterName);
	}
	
	public GameCharacter(
			int characterId,
			String characterName) {
		this.setCharacterId(characterId);
		this.setCharacterName(characterName);
	}
	
	public GameCharacter(
			String selectedTitle
			) {
		this.setCharacterName(selectedTitle);
	}
	
	public int getTitleId() {
		return titleId;
	}

	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

	public String getSelectedTitle() {
		return selectedTitle;
	}

	public void setSelectedTitle(String selectedTitle) {
		this.selectedTitle = selectedTitle;
	}

	public String[] getTitles() {
		return Titles;
	}

	public void setTitles(String[] titles) {
		Titles = titles;
	}

	public String[] getCharacters() {
		return characters;
	}

	public void setCharacters(String[] characters) {
		this.characters = characters;
	}

	public int getCharacterId() {
		return characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public void setCharacters(List<String> characters) {
        // List<String>からString[]に変換してセットする処理
        this.characters = characters.toArray(new String[characters.size()]);
    }
}
