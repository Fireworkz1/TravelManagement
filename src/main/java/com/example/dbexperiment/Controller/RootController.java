package com.example.dbexperiment.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/root")
public class RootController {

    @GetMapping("")
    public String hello(Model model) {
        // 向模板传递数据
        model.addAttribute("message", "管理员，您好!");

        // 指定使用的模板文件（templates/root.html）
        return "root";
    }

}
