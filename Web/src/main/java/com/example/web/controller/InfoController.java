package com.example.web.controller;

import com.example.web.enm.Message;
import com.example.web.entity.EmployInfo;
import com.example.web.entity.Result;
import com.example.web.service.InfoService;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @Author Memory
 * @Date 2021/7/12 16:52
 * @Version 1.0
 */
@RestController
@RequestMapping("/comment")
public class InfoController {
    @Autowired
    InfoService infoService;

    /**
     * 添加员工评价
     * @param employInfo
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Result add(EmployInfo employInfo){
        return infoService.add(employInfo);
    }

    /**
     * 返回评价总数
     * @param company
     * @return
     */
    @RequestMapping(value = "/num",method = RequestMethod.GET)
    public Result num(String company){
        return infoService.num(company);
    }
    /**
     * 查找本公司员工的评价
     * @param company
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/queryCompany",method = RequestMethod.GET)
    public Result myCompany(String company,int page, int pageSize){
        return infoService.myCompany(company, page, pageSize);
    }

    /**
     * 找到最新的员工评价
     * @param name
     * @return
     */
    @RequestMapping(value = "/findNew",method = RequestMethod.GET)
    public Result findOne(String name){
        return infoService.findOne(name);
    }

    /**
     * 查询处于离职状态的某员工在以往公司的评价信息
     * @param name
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
        public Result query(String name,int page, int pageSize){
            return infoService.query(name,page,pageSize);
    }

    /**
     * 员工评价上传附件
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result fileUpload(
            MultipartFile file) {
        return infoService.fileUpload(file);
    }

    /**
     * 下载文件
     * @param fileName
     * @param response
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public Result downLoad(String fileName, HttpServletResponse response){
        return infoService.downLoad(fileName,response);
    }
}