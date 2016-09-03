package com.yanlin.oa.dao;

import com.yanlin.oa.base.IBaseDao;
import com.yanlin.oa.domain.Forum;

public interface IForumManageDao extends IBaseDao<Forum>{

	void moveUp(Long id);

	void moveDown(Long id);

}
