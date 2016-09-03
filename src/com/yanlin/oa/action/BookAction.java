package com.yanlin.oa.action;

import org.springframework.stereotype.Controller;

import com.yanlin.oa.base.BaseAction;
import com.yanlin.oa.domain.Book;

@Controller
public class BookAction extends BaseAction<Book>{

	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		System.out.println(model);
		bookService.save(model);
		return NONE;
	}
}
