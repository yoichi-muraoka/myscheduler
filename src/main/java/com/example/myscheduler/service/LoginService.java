package com.example.myscheduler.service;

public interface LoginService {

	/**
	 * 正しいログインID、パスワードの組み合わせかチェックする
	 * → 正しい場合、セッションにユーザー情報を格納する
	 * @param loginId
	 * @param loginPass
	 * @return 正しい場合にはtrueを返す
	 */
	boolean isCorrectIdPass(String loginId, String loginPass);

	/**
	 * ログアウト処理を行う
	 */
	void logout();

}
