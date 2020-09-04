/**
 * <p><b>© 2020 深圳市国智共通科技有限公司</b></p>
 **/
package com.gz.sample.service.mapper;

import com.gz.sample.domain.Sample;
import com.gz.sample.domain.SampleChild;
import com.gz.sample.service.dto.SampleChildDTO;
import com.gz.sample.service.dto.SampleDTO;
import org.mapstruct.Mapper;

import java.io.Serializable;

/**
 *Mapper for the entity {@link Sample} and its DTO {@link SampleDTO}.
 *
 * @author liguiqing created at 2020-09-03
 * @since V1.0.0
 **/
@Mapper(componentModel = "spring", uses = {})
public interface SampleChildMapper extends EntityMapper<Long,SampleChildDTO,SampleChild> {

    @Override
    default Class<SampleChild> getEntityClass(){
        return SampleChild.class;
    }
}
