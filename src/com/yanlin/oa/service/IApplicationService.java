package com.yanlin.oa.service;

import java.io.InputStream;

import com.yanlin.oa.domain.Application;
import com.yanlin.oa.domain.PageBean;
import com.yanlin.oa.utils.HQLHelper;

public interface IApplicationService {

	PageBean pageBean(HQLHelper hql, int currentPage);

	InputStream getInputStreamById(Long applicationId);

	Application getById(Long applicationId);


}
