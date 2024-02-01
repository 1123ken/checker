package beans;

import java.util.List;

/**
 * ・コンストラクタ<br>
 *   <ul>
 *     <li>{@link #GameCharacter()} - デフォルトコンストラクタ</li>
 *     <li>{@link #GameCharacter(int, String, String[], String[], int, String)} - パラメータつきコンストラクタ</li>
 *     <li>{@link #GameCharacter(int, String)} - キャラクターを検索するコンストラクタ</li>
 *   </ul>
 * <br>
 * 
 * ・メソッド<br>
 *   <ul>
 *     <li>{@link #setCharacters(List)} - List&lt;String&gt;からString[]に変換してcharactersをセットする</li>
 *     <li>{@link #getTitleId()} - タイトルIDを取得</li>
 *     <li>{@link #setTitleId(int)} - タイトルIDを設定</li>
 *     <li>{@link #getSelectedTitle()} - 選択したタイトル名を取得</li>
 *     <li>{@link #setSelectedTitle(String)} - 選択したタイトル名を設定</li>
 *     <li>{@link #getTitles()} - 登録されているタイトルリストを取得</li>
 *     <li>{@link #setTitles(String[])} - 登録されているタイトルリストを設定</li>
 *     <li>{@link #getCharacters()} - タイトルに登録されているキャラクターリストを取得</li>
 *     <li>{@link #setCharacters(String[])} - タイトルに登録されているキャラクターリストを設定</li>
 *     <li>{@link #getCharacterId()} - 個々のキャラクターのIDを取得</li>
 *     <li>{@link #setCharacterId(int)} - 個々のキャラクターのIDを設定</li>
 *     <li>{@link #getCharacterName()} - 個々のキャラクター名を取得</li>
 *     <li>{@link #setCharacterName(String)} - 個々のキャラクター名を設定</li>
 *   </ul>
 * 
 */
/** 
* @author 馬場 健太朗
*/
public class GameCharacter {
    
    /**
     * ゲッターセッターで呼び出すために
     * privateで各変数を宣言
     */
    private int titleId;            // タイトルID
    private String selectedTitle;   // 選択したタイトル名
    private String[] Titles;        // 登録されているタイトルList
    private String[] characters;    // タイトルに登録されているキャラクターList
    private int characterId;        // 個々のキャラクターに登録されているid
    private String characterName;   // 個々のキャラクター名

    /**
     * デフォルトコンストラクタ
     */
    public GameCharacter() {
    }

    /**
     * パラメータつきコンストラクタ
     * 
     * @param titleId タイトルID
     * @param selectedtitle 選択したタイトル名
     * @param Titles 登録されているタイトルリスト
     * @param characters タイトルに登録されているキャラクターリスト
     * @param characterId 個々のキャラクターのID
     * @param characterName 個々のキャラクター名
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
     * 
     * @param characterId 個々のキャラクターのID
     * @param characterName 個々のキャラクター名
     */
    public GameCharacter(
            int characterId,
            String characterName) {
        this.setCharacterId(characterId);
        this.setCharacterName(characterName);
    }

    /**
     * List&lt;String&gt;からString[]に変換してセットする処理
     * 
     * @param characters セットするキャラクターのリスト
     */
    public void setCharacters(List<String> characters) {
        this.characters = characters.toArray(new String[characters.size()]);
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
     * 選択したタイトル名を取得します。
     * 
     * @return 選択したタイトル名
     */
    public String getSelectedTitle() {
        return selectedTitle;
    }

    /**
     * 選択したタイトル名を設定します。
     * 
     * @param selectedTitle 選択したタイトル名
     */
    public void setSelectedTitle(String selectedTitle) {
        this.selectedTitle = selectedTitle;
    }

    /**
     * 登録されているタイトルリストを取得します。
     * 
     * @return 登録されているタイトルリスト
     */
    public String[] getTitles() {
        return Titles;
    }

    /**
     * 登録されているタイトルリストを設定します。
     * 
     * @param titles 登録されているタイトルリスト
     */
    public void setTitles(String[] titles) {
        Titles = titles;
    }

    /**
     * タイトルに登録されているキャラクターリストを取得します。
     * 
     * @return タイトルに登録されているキャラクターリスト
     */
    public String[] getCharacters() {
        return characters;
    }

    /**
     * タイトルに登録されているキャラクターリストを設定します。
     * 
     * @param characters タイトルに登録されているキャラクターリスト
     */
    public void setCharacters(String[] characters) {
        this.characters = characters;
    }

    /**
     * 個々のキャラクターのIDを取得します。
     * 
     * @return 個々のキャラクターのID
     */
    public int getCharacterId() {
        return characterId;
    }

    /**
     * 個々のキャラクターのIDを設定します。
     * 
     * @param characterId 個々のキャラクターのID
     */
    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    /**
     * 個々のキャラクター名を取得します。
     * 
     * @return 個々のキャラクター名
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * 個々のキャラクター名を設定します。
     * 
     * @param characterName 個々のキャラクター名
     */
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}
