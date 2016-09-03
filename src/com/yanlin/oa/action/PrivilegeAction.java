package com.yanlin.oa.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yanlin.oa.base.BaseAction;
import com.yanlin.oa.domain.Privilege;

/**
 * 权限的action类
 * @author bubblelin
 *
 */
@Controller
@Scope("prototype")
public class PrivilegeAction extends BaseAction<Privilege>{

	private static final long serialVersionUID = 1L;

}
