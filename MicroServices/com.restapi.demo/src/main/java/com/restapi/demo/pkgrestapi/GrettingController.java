package com.restapi.demo.pkgrestapi;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GrettingController {
//private long id;
private AtomicLong counter = new AtomicLong();
private String nameformat="Hello, %s!";
@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
		Greeting grt = new Greeting(counter.incrementAndGet(), String.format(nameformat, name));
		return grt;
	}
}



