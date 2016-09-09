package com.yanlin.oa.dao.impl;

import java.io.File;

import org.springframework.stereotype.Repository;

import com.yanlin.oa.base.BaseDaoImpl;
import com.yanlin.oa.dao.ITemplateDao;
import com.yanlin.oa.domain.Template;

/**
 * 流程模板的dao实现
 * @author bubblelin
 *
 */
@Repository
public class templateDaoImpl extends BaseDaoImpl<Template> implements ITemplateDao{

	//重写删除模板的方法，同时删除模板对应的文件
	public void delete(Long id) {
		Template template = super.getById(id);
		String filePath = template.getFilePath();
		//删除文件
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
		super.delete(id);
	}

	
}
