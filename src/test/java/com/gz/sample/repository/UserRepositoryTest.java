package com.gz.sample.repository;

import com.gz.sample.domain.Authority;
import com.gz.sample.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.Instant;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test(){
        var t  = new User();
        t.setLogin("login1");
        t.setPassword(passwordEncoder.encode("login1"));
        t.setEmail("abc@abc.com");
        t.setMobile(13323456789L);
        t.setAuthorities(Set.of(new Authority("ROLE_ADMIN")));
        t.setCreatedDate(Instant.now().minusSeconds(1000L));
        t.setLastModifiedDate(Instant.now());
        this.repository.save(t);
        var t1 = this.repository.getOne(t.getId());
        assertThat(t1).isEqualTo(t);
        assertTrue(t1.hasAuthority("ROLE_ADMIN"));

        var query = this.em.createNativeQuery("select * from sample_user_authority where user_id=?");
        query.setParameter(1,t1.getId());
        var result = query.getResultList();
        assertThat(result.size()).isEqualTo(1);
    }
}
