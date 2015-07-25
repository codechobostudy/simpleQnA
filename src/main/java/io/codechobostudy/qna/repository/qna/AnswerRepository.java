package io.codechobostudy.qna.repository.qna;

import io.codechobostudy.qna.domain.qna.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long>{
}
