package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {

	@Autowired
	private SqlSession sql;

	// 전체 리스트
	public List<GuestBookVo> dbList() {

		System.out.println("[Dao]: dbList() 실행");

		List<GuestBookVo> gList = sql.selectList("guestbook.selectList");

		System.out.println(gList.toString());

		return gList;
	}

	// 방문록 등록
	public int dbIsrt(GuestBookVo gVo) {

		System.out.println("[Dao]: dbIsrt() 실행");

		int count = sql.insert("guestbook.insert", gVo);

		System.out.println(gVo.toString());

		System.out.println("[Dao]: " + count + " 건이 저장되었습니다.");

		return count;
	}

	// 방문록 삭제
	public int dbDle(GuestBookVo gVo) {

		System.out.println("[Dao]: dbDle() 실행");

		int count = sql.delete("guestbook.delete", gVo);

		System.out.println(gVo.toString());

		System.out.println("[Dao]: " + count + " 건이 삭제되었습니다.");

		return count;

	}
}
