package al.polis.zahire.controller;

import al.polis.zahire.dto.SingleNumberDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyFirstController {

    @GetMapping("/trace")
    public void traceRequest() {
        System.out.println("Request received!");
    }

    @PostMapping("/doubleTheNumber")
    public @ResponseBody double doubleTheNumber(@RequestBody SingleNumberDto dto) {
        System.out.println("Received a number " + dto.getNumber());
        return dto.getNumber() * 2;
    }

}
