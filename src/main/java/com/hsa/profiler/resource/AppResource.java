package com.hsa.profiler.resource;

import com.hsa.profiler.service.AvlTreeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avl")
public class AppResource {

  final AvlTreeService avlTreeService = new AvlTreeService();

  @PostMapping
  public void create() {
    for (int i = 0; i < 1000; i++) {
      // purpose of i only for graph creation, elements inside method generated randomly
      avlTreeService.insertElement(i);
    }
  }

  @GetMapping
  public void find() {
    for (int i = 0; i < 1000; i++) {
      // purpose of i only for graph creation, elements inside method generated randomly
      avlTreeService.searchElement(i);
    }
  }
}
