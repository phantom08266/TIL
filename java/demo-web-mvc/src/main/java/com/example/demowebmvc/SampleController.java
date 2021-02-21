package com.example.demowebmvc;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class SampleController {

    @GetMapping("/events/form")
    public String eventsForm(Model model) {
        model.addAttribute("event", new Event());
        return "/events/form";
    }

    @PostMapping(value = "/events")
    public String getEvent(@Validated @ModelAttribute Event event,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "/events/form";
        }
        //보통 여기서 DB 에 저장
//        List<Event> eventList =new ArrayList<>();
//        eventList.add(event);
//        model.addAttribute(eventList);

        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model){
        //보통 여기서 DB에 저장된 데이터 읽어오기
        Event event = new Event();
        event.setName("spring");
        event.setLimit(10);

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        model.addAttribute(eventList);
        return "/events/list";
    }
}
