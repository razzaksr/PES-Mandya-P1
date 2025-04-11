package org.pes.web_crud;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic")
public class BasicAPI {
    // @GetMapping("/")
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String greetings(){
        return "Welcome To DLithe";
    }
    // @GetMapping("/{height}/{weight}")
    @RequestMapping(value="/{height}/{weight}", method = RequestMethod.GET)
    public double calculateBMI(@PathVariable double height,@PathVariable double weight){
        height = height/100;
        return weight/(height*height);
    }

    // @PostMapping("/")
    @RequestMapping(value="/", method = RequestMethod.POST)
    public double BMIViaBody(@RequestBody double[] scales){
        double height = scales[0];double weight=scales[1];
        height = height/100;
        return weight/(height*height);
    }
}
