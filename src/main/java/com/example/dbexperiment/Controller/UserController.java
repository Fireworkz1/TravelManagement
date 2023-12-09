package com.example.dbexperiment.Controller;

import com.example.dbexperiment.Config.UserContext;
import com.example.dbexperiment.Entity.Bus;
import com.example.dbexperiment.Entity.Flight;
import com.example.dbexperiment.Entity.Hotel;
import com.example.dbexperiment.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     *
     * /login 登陆页面相关逻辑
     */
    @GetMapping("/login")
    public String loginPage(Model model) {
        // 向模板传递数据
        model.addAttribute("message", "用户，您好!!\n请选择登录或注册");
        model.addAttribute("usernamePlaceholder", "账号");
        model.addAttribute("passwordPlaceholder", "密码");

        // 指定使用的模板文件（templates/login.html）
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        @RequestParam(required = false, defaultValue = "") String action,
                        Model model) {
        if ("register".equals(action)) {
            // 处理注册逻辑...
            // 这里可以添加注册相关的业务逻辑
            boolean is=userService.createUser(username,password);
            if(!is){
                model.addAttribute("error", "已存在账号");
            }
            model.addAttribute("message", "注册成功，请登录！");
            return "login";
        }


        // 处理登录逻辑...
        boolean is;
        is=userService.userLogin(username,password);
        if (is) {
            UserContext.getInstance().setUsername(username);
            return "redirect:/user/dashboard"; // 重定向到仪表盘页面
        } else {
            model.addAttribute("error", "账号或密码错误");
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        String user=UserContext.getInstance().getUsername();
        String user_info=user + ",欢迎您！";
        model.addAttribute("user_info", user_info);//title
        String route_str=userService.travelRoute(user).getRoute_str();
        model.addAttribute("user_route", route_str);//用户当前旅行路线

        return "userdashboard";
    }

    @GetMapping("/reserve")
    public String getReserve(@RequestParam String type,Model model) throws JsonProcessingException {
        List<?> self_info = null; // 使用通配符 (?) 表示不确定的类型
        List<?> all_info = null;
        List<String> self_label=new ArrayList<>();
        String page_info="";
        String user=UserContext.getInstance().getUsername();
        if(type.equals("flight")){
            self_info=userService.travelRoute(user).getFlightList();
            all_info=userService.searchFlight();
            page_info+="飞机";
            self_label.add("航班编号");
            self_label.add("航班价格");
            self_label.add("座位数");
            self_label.add("剩余座位数");
            self_label.add("起飞城市");
            self_label.add("降落城市");
        } else if (type.equals("bus")) {
            self_info=userService.travelRoute(user).getBusList();
            all_info=userService.searchBus();
            page_info+="公交";
            self_label.add("地点");
            self_label.add("票价");
            self_label.add("座位数");
            self_label.add("剩余座位数");
        } else if (type.equals("hotel")) {
            self_info=userService.travelRoute(user).getHotelList();
            all_info=userService.searchHotel();
            page_info+="酒店";
            self_label.add("地点");
            self_label.add("价格");
            self_label.add("房间数");
            self_label.add("剩余房间数");
        }else{
            System.out.println("fail");}

        // 生成新的 List other
        List<Object> other_info = new ArrayList<>();
        for (Object element : all_info) {
            if (!self_info.contains(element)) {
                other_info.add(element);
            }
        }
        model.addAttribute("type",type);
        model.addAttribute("page_msg",page_info);
        model.addAttribute("self_info",self_info);
        model.addAttribute("self_label",self_label);
        model.addAttribute("other_info",other_info);
        model.addAttribute("other_info_json", new ObjectMapper().writeValueAsString(other_info));
        // 假设 other_info 是一个 List<Bus> 对象


        return "reserve";
    }
    @PostMapping("/reserve")
    public String postReserve(@RequestParam String resvid,@RequestParam String type){


        return "reserve";
    }

}


