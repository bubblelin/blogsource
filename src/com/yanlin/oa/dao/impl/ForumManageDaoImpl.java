package com.yanlin.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yanlin.oa.base.BaseDaoImpl;
import com.yanlin.oa.dao.IForumManageDao;
import com.yanlin.oa.domain.Forum;
/**
 * 板块管理dao的实现类
 * @author bubblelin
 *
 */
@Repository
public class ForumManageDaoImpl extends BaseDaoImpl<Forum> implements IForumManageDao{

	/**
	 * 重写父类的save()方法，设置position的值和id一致
	 */
	public void save(Forum entity) {
		super.save(entity);		//由瞬时对象变为持久的对象，id已经赋值
		entity.setPosition(entity.getId().intValue());		//设置position的值与id一致
	}
	
	/**
	 * 重写父类的findAll()方法，实现按position排序
	 */
	@SuppressWarnings("unchecked")
	public List<Forum> findAll() {
		String hql = "from Forum f order by f.position";
		return this.getSession().createQuery(hql).list();
	}
	/**
	 * 上移板块
	 */
	public void moveUp(Long id) {
		//select f.* from oa_forum f where f.position_ < 8 order by f.position_ desc limit 0,1;
		//获取需要移动的板块的position
		Forum forum1 = this.getById(id);
		int position1 = forum1.getPosition();
		String hql = "from Forum f where f.position <? order by f.position desc";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, position1);
		//获取查询到的（位置在需要移动过的板块的上方）第一个板块[0,1)
		query.setFirstResult(0);
		query.setMaxResults(1);
		Forum forum2 = (Forum)query.uniqueResult();
		//交换位置
		forum1.setPosition(forum2.getPosition());
		forum2.setPosition(position1);
	}
	
	/**
	 * 板块下移
	 */
	public void moveDown(Long id) {
		
		//获取需要下移的板块的id
		Forum forum1 = this.getById(id);
		int position1 = forum1.getPosition();
		String hql ="from Forum f where f.position > ? order by f.position";
		//获取查询到的（位置在需要移动的板块的下方）第一个板块
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, position1);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Forum forum2 = (Forum)query.uniqueResult();
		//交换位置
		forum1.setPosition(forum2.getPosition());
		forum2.setPosition(position1);
	}

	
}
