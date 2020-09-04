package com.gz.sample.repository;

import com.gz.sample.domain.Sample;
import com.gz.sample.domain.SampleChild;
import com.gz.sample.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
class SampleRepositoryTest {

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void test(){
        var user = new User();
        user.setId(1L);
        var t = new Sample().active(true)
            .user(user)
            .deadline(LocalDate.now().plusDays(100))
            .title("title")
            .child(new SampleChild().active(true).joinedTime(ZonedDateTime.now()).name("child1"))
            .child(new SampleChild().active(false).joinedTime(ZonedDateTime.now()).name("child2"));
        this.sampleRepository.save(t);
        var t1 = this.sampleRepository.getOne(t.getId());
        assertThat(t1).isEqualTo(t);
        var query = this.em.createNativeQuery("select id from sample_children where sample_id =?");
        query.setParameter(1,t1.getId());
        var result = query.getResultList();
        assertThat(result.size()).isEqualTo(2);
        this.sampleRepository.delete(t1);
        var t2 = this.sampleRepository.findById(t1.getId());
        assertThat(t2).isEmpty();
        result = query.getResultList();
        assertThat(result.size()).isEqualTo(0);
        query = this.em.createNativeQuery("select * from sample_user where id =?");
        query.setParameter(1,1);
        assertThat(query.getResultList().size()).isEqualTo(1);
    }
}
