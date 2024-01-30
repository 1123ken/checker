package model;

import java.util.List;

/**
 * titleIdから情報を取得するbeans
 * @author 馬場 健太朗
 */

public class GameCharacter {
	
	/**
	 * ゲッターセッターで呼び出すために
	 * privateで各変数を宣言
	 */
	private int titleId; //タイトルID
	private String selectedTitle; //選択したタイトル名
	private String[] Titles; //登録されているタイトルList
	private String[] characters; //タイトルに登録されているキャラクターList
	private int characterId;	//個々のキャラクターに登録されているid
	private String characterName;	//個々のキャラクター名

	/**
	 * デフォルトコンストラクタ
	 */
	public GameCharacter() {
	}

	/**
	 * コンストラクタ
	 * @param titleId
	 * @param selectedtitle
	 * @param Titles
	 * @param characters
	 * @param characterId
	 * @param characterName
	 */
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
	
	/**
	 * キャラクターを検索するコンストラクタ
	 * @param characterId
	 * @param characterName
	 */
	public GameCharacter(
			int characterId,
			String characterName) {
		this.setCharacterId(characterId);
		this.setCharacterName(characterName);
	}

	public void setCharacters(List<String> characters) {
        /**
         * List<String>からString[]に変換してセットする処理
         */
        this.characters = characters.toArray(new String[characters.size()]);
    }

	/**
	 * @return titleId
	 */
	public int getTitleId() {
		return titleId;
	}

	/**
	 * @param titleId セットする titleId
	 */
	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

	/**
	 * @return selectedTitle
	 */
	public String getSelectedTitle() {
		return selectedTitle;
	}

	/**
	 * @param selectedTitle セットする selectedTitle
	 */
	public void setSelectedTitle(String selectedTitle) {
		this.selectedTitle = selectedTitle;
	}

	/**
	 * @return titles
	 */
	public String[] getTitles() {
		return Titles;
	}

	/**
	 * @param titles セットする titles
	 */
	public void setTitles(String[] titles) {
		Titles = titles;
	}

	/**
	 * @return characters
	 */
	public String[] getCharacters() {
		return characters;
	}

	/**
	 * @param characters セットする characters
	 */
	public void setCharacters(String[] characters) {
		this.characters = characters;
	}

	/**
	 * @return characterId
	 */
	public int getCharacterId() {
		return characterId;
	}

	/**
	 * @param characterId セットする characterId
	 */
	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	/**
	 * @return characterName
	 */
	public String getCharacterName() {
		return characterName;
	}

	/**
	 * @param characterName セットする characterName
	 */
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
}
