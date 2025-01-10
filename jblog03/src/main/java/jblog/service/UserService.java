package jblog.service;

import org.springframework.stereotype.Service;

import jblog.repository.UserRepository;
import jblog.vo.UserVo;

@Service
public class UserService {
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void join(UserVo userVo) {
		userRepository.insert(userVo);
	}
	
	

}
