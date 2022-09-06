package com.training.repo;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.model.Chat;

@Repository
public interface ChatRepo extends JpaRepository<Chat,Integer> {
	
	@Query(value="select * from chat where pid=?1 and uid=?2",nativeQuery = true)
	List<Chat> findByPidAndUid(int pid,int uid);
	
	@Query(value="select * from chat where pid=?1",nativeQuery = true)
	List<Chat> findByPid(int pid);
}
