package vsg.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vsg.model.LocationFactory;
import vsg.model.MyLocation;

@RestController
public class GridController {
    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }
    
    @RequestMapping(value = "/location", method = RequestMethod.GET,produces={"application/json","application/xml"})
    public MyLocation showLocation(@RequestParam(value="name", required=false, defaultValue="World") String name) {
    	MyLocation l = new MyLocation();
    	l.setX(21.553115844726562);
    	l.setY(63.089553465232996);
        return l;
    }

    @RequestMapping(value = "/locations", method = RequestMethod.GET,produces={"application/json","application/xml"})
    public MyLocation[] showLocations(@RequestParam(value="name", required=false, defaultValue="World") String name) {
    	MyLocation l = new MyLocation();
    	l.setX(21.553115844726562);
    	l.setY(63.089553465232996);
    	MyLocation l2 = new MyLocation();
    	l2.setX(21.64958953857422);
    	l2.setY(63.109203272591294);
    	MyLocation[] locations = {l, l2};
        return locations;
    }

    
    @RequestMapping(value = "/calculateAndReturnAllTree" , method = RequestMethod.POST,produces={"application/json","application/xml"})
    public @ResponseBody MyLocation[] addProject(@RequestBody MyLocation[] xmlLocation) {
    	//Always the first location is powerplant, next consumer. Late the substation will be in the middle
    	MyLocation powerplant = xmlLocation[0];
    	MyLocation consumer = xmlLocation[1];
    	MyLocation substation = LocationFactory.calcSubstationPosition(powerplant, consumer, 0.01);
    	MyLocation[] locations = {powerplant, substation, consumer};
    	return locations;
    }  
}
