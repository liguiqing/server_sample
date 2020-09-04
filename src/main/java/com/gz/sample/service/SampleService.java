/**
 * <p><b>© 2020 深圳市国智共通科技有限公司</b></p>
 **/
package com.gz.sample.service;

import com.gz.sample.repository.SampleRepository;
import com.gz.sample.repository.UserRepository;
import com.gz.sample.security.SecurityUtils;
import com.gz.sample.service.dto.SampleDTO;
import com.gz.sample.service.mapper.SampleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Sample Service
 *
 * @author liguiqing created at 2020-09-03
 * @since V1.0.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class SampleService  {
    private final static Logger log = LoggerFactory.getLogger(SampleService.class);

    private final SampleRepository sampleRepository;

    private final SampleMapper sampleMapper;

    private final UserRepository userRepository;

    public SampleService(SampleRepository sampleRepository, SampleMapper sampleMapper, UserRepository userRepository) {
        this.sampleRepository = sampleRepository;
        this.sampleMapper = sampleMapper;
        this.userRepository = userRepository;
    }

    /**
     * Save a sample.
     * if dto.id is not null,update dto or create.
     * @param dto
     */
    public SampleDTO saveSample(SampleDTO dto){
        log.debug("Request to save a sample");
        var user = this.userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
        var entity = this.sampleMapper.toEntity(dto);
        entity.setUser(user.get());
        var sample = this.sampleRepository.save(entity);
        return this.sampleMapper.toDto(sample);
    }

    /**
     * find all sample by page
     * @param pageable
     * @return Page<SampleDTO>
     */
    @Transactional(readOnly = true)
    public Page<SampleDTO> findAll(Pageable pageable){
        log.debug("Request get samples by page");
       return this.sampleRepository.findAll(pageable).map(sampleMapper::toDto);
    }
}
