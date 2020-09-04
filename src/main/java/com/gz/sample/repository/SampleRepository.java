/**
 * <p><b>© 2020 深圳市国智共通科技有限公司</b></p>
 **/
package com.gz.sample.repository;

import com.gz.sample.domain.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SampleRepository
 *
 * @author liguiqing created at 2020-09-03
 * @since V1.0.0
 **/
@Repository
public interface SampleRepository extends JpaRepository<Sample,Long> {
}
