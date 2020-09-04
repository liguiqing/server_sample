/**
 * <p><b>© 2020 深圳市国智共通科技有限公司</b></p>
 **/
package com.gz.sample.service.mapper;

import com.gz.sample.domain.Sample;
import com.gz.sample.service.dto.SampleDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Sample} and its DTO {@link SampleDTO}.
 *
 * @author liguiqing created at 2020-09-03
 * @since V1.0.0
 **/
@Mapper(componentModel = "spring", uses = {SampleChildMapper.class})
public interface SampleMapper extends EntityMapper<Long,SampleDTO, Sample> {

    @Override
    default Class<Sample> getEntityClass(){
        return Sample.class;
    }
}
