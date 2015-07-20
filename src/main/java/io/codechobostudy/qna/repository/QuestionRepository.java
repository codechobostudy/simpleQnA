package io.codechobostudy.qna.repository;

import io.codechobostudy.qna.domain.Question;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends PagingAndSortingRepository<Question, Long>{
}
