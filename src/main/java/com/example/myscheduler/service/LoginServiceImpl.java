package com.example.myscheduler.service;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myscheduler.domain.User;
import com.example.myscheduler.mapper.UserMapper;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 正しいログインID、パスワードの組み合わせかチェックする
	 * → 正しい場合、セッションにユーザー情報を格納する
	 * @param loginId
	 * @param loginPass
	 * @return 正しい場合にはtrueを返す
	 */
	@Override
	public boolean isCorrectIdPass(String loginId, String loginPass) {
		User user = userMapper.selectByLoginId(loginId);
		
		// ログインIDが不正
		if(user == null) {
			System.out.println("ログインIDが不正");
			return false;
		}
		
		// パスワードが不正
		if(!BCrypt.checkpw(loginPass, user.getLoginPass())) {
			System.out.println("パスワードIDが不正");
			return false;
		}
		
		// ログインID、パスワードの組み合わせが正しい
		session.setAttribute("user", user);	
		return true;
	}

	/**
	 * ログアウト処理を行う
	 */
	@Override
	public void logout() {
		session.invalidate();
	}

}
