package com.training.repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.training.model.Chat;

@Repository
public interface ChatRepo extends MongoRepository<Chat, String> {

	List<Chat> findByPidAndUid(int pid, int uid);

	List<Chat> findByPid(int pid);
}
