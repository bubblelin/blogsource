package com.yanlin.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.oa.dao.ITemplateDao;
import com.yanlin.oa.domain.Template;
import com.yanlin.oa.service.ITemplateService;

/**
 * 流程模板的service实现
 * @author bubblelin
 *
 */
@Service
@Transactional
public class TemplateServiceImpl implements ITemplateService{
	@Resource
	private ITemplateDao templateDao;

	public List<Template> findAll() {
		return templateDao.findAll();
	}

	public void save(Template model) {
		templateDao.save(model);
	}

	public void delete(Long id) {
		templateDao.delete(id);
	}

	public Template getById(Long id) {
		return templateDao.getById(id);
	}

	public void update(Template template) {
		templateDao.update(template);
	}
	//根据模板的id，读取相应的文件
	public InputStream getInputStreamById(Long id) {
		Template template = templateDao.getById(id);
		String filePath = template.getFilePath();
		InputStream in = null;
		try {
			in = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return in;
	}
}
