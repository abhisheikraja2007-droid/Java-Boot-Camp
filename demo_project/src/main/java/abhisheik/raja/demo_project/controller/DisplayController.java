package abhisheik.raja.demo_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import abhisheik.raja.demo_project.service.DisplayService;

@RestController 
public class DisplayController {
    @Autowired 
    DisplayService displayService;
    @GetMapping("/display/{name}")
    public String display(@PathVariable String name){
        return displayService.display(name);
    } 
}
