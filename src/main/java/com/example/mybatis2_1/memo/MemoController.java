package com.example.mybatis2_1.memo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class MemoController {
  @Autowired
  private MemoDao memoDao;

  @GetMapping("/memo/list")
  public ModelAndView findAll() {
    return new ModelAndView("memo/list").addObject("memos", memoDao.findAll());
  }

  @PostMapping("/memo/add")
  public ModelAndView save(@ModelAttribute Memo memo) {
    memoDao.save(memo);
    return new ModelAndView("redirect:/memo/list");
  }

  @PostMapping("/memo/update")
  public ModelAndView update(@RequestParam String content, @RequestParam int mno) {
    memoDao.update(content,mno);
    return new ModelAndView("redirect:/memo/list");
  }

  @PostMapping("/memo/delete")
  public ModelAndView delete(@RequestParam int mno) {
    memoDao.delete(mno);
    return new ModelAndView("redirect:/memo/list");
  }
}
