package com.yanlin.test;

import com.yanlin.oa.domain.Topic;
import com.yanlin.oa.utils.HQLHelper;

public class HQLHelpTest {

	
	public static void main(String[] args) {
		HQLHelper hql = new HQLHelper(Topic.class);
		
		
		hql.addWhere(" o.title=? ", "spring");
		hql.addWhere(" o.type=? ", "2");
		hql.addWhere(" o.content=? ", "你好");
		hql.addWhere(" o.postTime=? ", "123456");
		hql.addOrderBy(" (case o.type when 2 then 2 else 1 end) ", true);
		hql.addOrderBy(" o.postTime ", false);
		
		System.out.println(hql.getListHQL());
		System.out.println(hql.getCountHQL());
		System.out.println(hql.getArgs());
	}
}
