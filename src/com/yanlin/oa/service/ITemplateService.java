package com.yanlin.oa.service;

import java.io.InputStream;
import java.util.List;

import com.yanlin.oa.domain.Template;

public interface ITemplateService {

	List<Template> findAll();

	void save(Template model);

	void delete(Long id);

	Template getById(Long id);

	void update(Template template);

	InputStream getInputStreamById(Long id);

}
