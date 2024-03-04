package com.example.demo03.controller;

import com.example.demo03.service.ProductService.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/product")
    public String home () {
        return "product/index";
    }
    @GetMapping("/addCSV")
    public String  addCSV(){
        productService.saveCustomerData();
        return "home/index";
    }
    @GetMapping("/export")
    public void exportExcel (HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Product.xls";

        response.setHeader(headerKey, headerValue);
        productService.generateExcel(response);
    }
}
