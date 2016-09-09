package com.yanlin.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.oa.dao.IApplicationDao;
import com.yanlin.oa.domain.Application;
import com.yanlin.oa.domain.PageBean;
import com.yanlin.oa.service.IApplicationService;
import com.yanlin.oa.utils.HQLHelper;

/**
 * 申请的service实现
 * @author bubblelin
 *
 */
@Service
@Transactional
public class ApplicationServiceImpl implements IApplicationService{

	@Resource
	private IApplicationDao applicationDao;

	public PageBean pageBean(HQLHelper hql, int currentPage) {
		return applicationDao.getPageBean(hql, currentPage);
	}
	//获取文件的输入流
	public InputStream getInputStreamById(Long applicationId) {
		Application application = applicationDao.getById(applicationId);
		String filePath = application.getFilePath();
		File file = new File(filePath);
		InputStream in = null;
		try {
			if(file.exists()){
				in = new FileInputStream(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
	}

	public Application getById(Long applicationId) {
		return applicationDao.getById(applicationId);
	}

	
}
