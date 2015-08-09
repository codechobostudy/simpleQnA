package io.codechobostudy.qna.repository.qna;

import io.codechobostudy.qna.domain.qna.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long>{
    Tag findOneByName(String name);
}
