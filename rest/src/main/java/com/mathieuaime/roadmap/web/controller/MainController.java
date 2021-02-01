package com.mathieuaime.roadmap.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
  // Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
  // Required because of 'mode: history' usage in frontend routing, see README for further details
  @RequestMapping(value = "{_:^(?!index\\.html|api).*$}")
  public String redirectApi() {
    return "forward:/";
  }
}
