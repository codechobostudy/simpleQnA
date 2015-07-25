package io.codechobostudy.qna.repository.qna;

import io.codechobostudy.qna.domain.qna.Question;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends PagingAndSortingRepository<Question, Long>{
}
