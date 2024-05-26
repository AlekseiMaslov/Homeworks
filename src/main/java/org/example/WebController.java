package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

  @GetMapping("/hello")
  public String hello() {
    return "Hello guys, its ok, isn't it?";
  }
}
