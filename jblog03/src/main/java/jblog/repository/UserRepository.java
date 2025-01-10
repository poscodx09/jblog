package jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.UserVo;

@Repository
public class UserRepository {

	private SqlSession sqlSession;
	
	public UserRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(UserVo vo) {
		try {
			return sqlSession.insert("user.insert", vo);	
		} catch(RuntimeException e) {
			System.out.println("error:" + e);
		}
		
		return 0;
	}
}
