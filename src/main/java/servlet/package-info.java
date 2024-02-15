/**
 * 　servletフォルダ<br>
 * ・CharacterServlet<br>
 * 　キャラクター情報を取得するためのサーブレットです。<br>
 * ・GetDataServlet<br>
 * 　 pointテーブル内の各キャラクターの対策等の情報を取得するサーブレット<br>
 * ・HomeServlet<br>
 * 　top.jspで表示されたタイトル名からhome.jspに画面遷移する為のサーブレット<br>
 * ・SavaDataServlet<br>
 * 　home.jspの入力フォームで入力したデータをSaveDataDAOに送るサーブレット<br>
 * ・TitleCharaRegiServlet<br>
 * 　top.jspでタイトルやキャラクターを登録する際にデータの受け渡しをするサーブレット<br>
 * ・TopServlet<br>
 * 　TOPページに画面遷移するためのサーブレットです。<br>
 * 　top.jspがWEB-INF内にあるため、ここからしか画面遷移できません。<br>
 */
package servlet;