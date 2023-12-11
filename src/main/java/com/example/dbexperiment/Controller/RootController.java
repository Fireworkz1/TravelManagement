package com.example.dbexperiment.Controller;

import com.example.dbexperiment.Config.UserContext;
import com.example.dbexperiment.Entity.Bus;
import com.example.dbexperiment.Entity.Flight;
import com.example.dbexperiment.Entity.Hotel;
import com.example.dbexperiment.Entity.Route;
import com.example.dbexperiment.Service.RootService;
import com.example.dbexperiment.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/root")
public class RootController {
    @Resource
    UserService userService;
    @Resource
    RootService rootService;
    @GetMapping("")
    public String hello(Model model) {
        // 向模板传递数据
        model.addAttribute("message", "管理员，您好!欢迎登录系统");

        // 指定使用的模板文件（templates/root.html）
        return "root";
    }
    @GetMapping("/flight")
    public String getFlight(Model model) throws JsonProcessingException {
        List<Flight> flightList=userService.searchFlight();
        List<String> self_label=new ArrayList<>();
        self_label.add("航班编号");
        self_label.add("航班价格");
        self_label.add("座位数");
        self_label.add("剩余座位数");
        self_label.add("起飞城市");
        self_label.add("降落城市");
        self_label.add("起飞时间");
        model.addAttribute("self_label",self_label);
        model.addAttribute("flights_list",flightList);
        model.addAttribute("flights_list_json", new ObjectMapper().writeValueAsString(flightList));
        return "roothtml/flight";
    }
    @PostMapping("/flight/add")
    public String postFlight(Model model, @ModelAttribute Flight flight){
        rootService.addFlight(flight);
        return "redirect:/root/flight";
    }
    @PostMapping("/flight/del")
    public String delFlight(Model model, @RequestParam String name){
        rootService.deleteFlight(name);
        return "redirect:/root/flight";
    }
    @GetMapping("/hotel")
    public String getHotel(Model model) throws JsonProcessingException {
        List<Hotel> hotelList=userService.searchHotel();
        List<String> self_label=new ArrayList<>();
        self_label.add("地点");
        self_label.add("价格");
        self_label.add("房间数");
        self_label.add("剩余房间数");
        model.addAttribute("self_label",self_label);
        model.addAttribute("hotels_list",hotelList);
        model.addAttribute("hotels_list_json", new ObjectMapper().writeValueAsString(hotelList));
        return "roothtml/hotel";
    }
    @PostMapping("/hotel/add")
    public String postHotel(Model model, @ModelAttribute Hotel hotel){
        rootService.addHotel(hotel);
        return "redirect:/root/hotel";
    }
    @PostMapping("/hotel/del")
    public String delHotel(Model model, @RequestParam String name){
        rootService.deleteHotel(name);
        return "redirect:/root/hotel";
    }
    @GetMapping("/bus")
    public String getBus(Model model) throws JsonProcessingException {
        List<Bus> busList=userService.searchBus();
        List<String> self_label=new ArrayList<>();
        self_label.add("地点");
        self_label.add("票价");
        self_label.add("座位数");
        self_label.add("剩余座位数");
        self_label.add("发车时间");
        model.addAttribute("self_label",self_label);
        model.addAttribute("buses_list",busList);
        model.addAttribute("buses_list_json", new ObjectMapper().writeValueAsString(busList));
        return "roothtml/bus";
    }
    @PostMapping("/bus/add")
    public String postBus(Model model, @ModelAttribute Bus bus){
        rootService.addBus(bus);
        return "redirect:/root/bus";
    }
    @PostMapping("/bus/del")
    public String delBus(Model model, @RequestParam String name){
        rootService.deleteBus(name);
        return "redirect:/root/bus";
    }

    @GetMapping("/customer")
    public String getCustomer(Model model) throws JsonProcessingException {
        List<String> self_label=new ArrayList<>();
        self_label.add("用户名");
        self_label.add("密码");
        model.addAttribute("self_label",self_label);
        model.addAttribute("user_list",rootService.selectAllUser());
        model.addAttribute("user_list_json", new ObjectMapper().writeValueAsString(rootService.selectAllUser()));
        return "roothtml/customer";
    }
    @PostMapping("/customer/del")
    public String delCustomer(Model model, @RequestParam String name,@RequestParam(required = false, defaultValue = "") String action){
        if ("default".equals(action)) {
            rootService.defaultPswd(name);
        } else if ("changeresv".equals(action)) {
            return "redirect:/root/reservation?user="+name;

        } else {
            rootService.delUser(name);
        };
        return "redirect:/root/customer";
    }
    @GetMapping("/reservation")
    public String getResv(Model model,@RequestParam String user) throws JsonProcessingException {
        String user_info="以下是"+user+"的行程,请在输入框内输入要删除的预约id";
        List<String> route_label=new ArrayList<>();
        route_label.add("预约id");
        route_label.add("编号/名字");
        route_label.add("种类");
        route_label.add("时间");
        Route route=userService.travelRoute(user);
        model.addAttribute("user_info", user_info);//title
        model.addAttribute("tuple_route", route.getResvTupleList());//用户当前全部预定信息
        model.addAttribute("user_route","您当前的预约如下");//用户当前旅行路线
        model.addAttribute("route_label", route_label);
        model.addAttribute("route_info_json", new ObjectMapper().writeValueAsString(route.getResvTupleList()));

        return "/roothtml/reservation";
    }
}
