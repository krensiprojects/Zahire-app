package al.polis.zahire.controller;

import al.polis.zahire.dto.CalculationResponseDto;
import al.polis.zahire.dto.SingleNumberDto;
import al.polis.zahire.dto.TwoNumberDto;
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

    @PostMapping("/calculate")
    public CalculationResponseDto calculate (@RequestBody TwoNumberDto request){
        double sum = request.getNumber1() + request.getNumber2();
        double product = request.getNumber1() * request.getNumber2();
        double division;
        if (request.getNumber2() !=0) {
            division = request.getNumber1() / request.getNumber2();
        }else{
            division=0;
        }
        return new CalculationResponseDto(sum,product,division);
    }

}
