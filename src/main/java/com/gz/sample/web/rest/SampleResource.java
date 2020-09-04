/**
 * <p><b>© 2020 深圳市国智共通科技有限公司</b></p>
 **/
package com.gz.sample.web.rest;

import com.gz.sample.security.AuthoritiesConstants;
import com.gz.sample.service.SampleService;
import com.gz.sample.service.dto.SampleDTO;
import com.gz.sample.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * This is a sample how to for java coder
 *
 * @author liguiqing created at 2020-09-04
 * @since V1.0.0
 **/
@RestController
@RequestMapping("/api")
public class SampleResource {
    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SampleService sampleServiceService;

    public SampleResource(SampleService sampleServiceService) {
        this.sampleServiceService = sampleServiceService;
    }

    /**
     * {@code POST  /samples}  : Creates a new sample.
     *
     * @param sampleDTO the sample to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sample, or with status {@code 400 (Bad Request)} if the sample have ayn question.
     * @throws URISyntaxException if the save have any problem.
     * @throws BadRequestAlertException {@code 400 (Bad Request)} if the sample is already in use.
     */
    @PostMapping("/samples")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<SampleDTO> createUser(@RequestBody SampleDTO sampleDTO) throws URISyntaxException {
        log.debug("REST request to save Sample : {}", sampleDTO);

        if (sampleDTO.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
        }
        SampleDTO saved = this.sampleServiceService.saveSample(sampleDTO);
        return ResponseEntity.created(new URI("/api/samples/" + sampleDTO.getTitle()))
            .headers(HeaderUtil.createAlert(applicationName,  "sample.created", sampleDTO.getTitle()))
            .body(saved);
    }

    /**
     * {@code GET /samples} : get all samples and their's children
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all samples.
     */
    @GetMapping("/samples")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<List<SampleDTO>> getAllSamples(Pageable pageable) {
        final Page<SampleDTO> page = sampleServiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
