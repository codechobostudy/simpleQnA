package io.codechobostudy.qna.repository;

import io.codechobostudy.qna.domain.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long>{
}
