package jblog.repository;

import java.util.Map;

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

	public UserVo findByIdAndPassword(String id, String password) {
		return sqlSession.selectOne("user.findByIdAndPassword", Map.of("id", id, "password", password));
	}

	public UserVo findById(String id) {
		return sqlSession.selectOne("user.findById", id);
	}


}
