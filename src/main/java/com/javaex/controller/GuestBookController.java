package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping(value = "/gbc")
public class GuestBookController {

	// fields
	@Autowired
	private GuestBookDao gDao;

	// constructors

	// get/set method

	// general method
	// addList
	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model) {

		System.out.println("[Ctrl]: addList 진입");

		// list
		List<GuestBookVo> gList = gDao.dbList();

		// model → data 전송
		model.addAttribute("GuestList", gList);

		return "/addlist";

	}

	// http://localhost:8088/guestbook3/gbc/add?name=[]&password=[]&content=[]
	// add
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestBookVo gVo) {

		System.out.println("[Ctrl]: add 진입");

		gDao.dbIsrt(gVo);

		return "redirect:/gbc/addList";

	}

	// dForm
	@RequestMapping(value = "/dForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String dForm() {

		System.out.println("[Ctrl]: dForm 진입");

		return "/deleteForm";

	}

	// http://localhost:8088/guestbook3/gbc/delete?no=[]
	// delete
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute GuestBookVo gVo) {

		System.out.println("[Ctrl]: delete 진입");

		int cnt = gDao.dbDle(gVo);

		if (cnt == 1) {
			return "redirect:/gbc/addList";
		} else {
			System.out.println("[Ctrl]: password 오류");
			return "/pswError";
		}

	}

}
