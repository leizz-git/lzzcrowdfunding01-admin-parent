package com.lzz.crowd.mvc.handler;

import com.lzz.crowd.entity.Admin;
import com.lzz.crowd.entity.ParamData;
import com.lzz.crowd.entity.Student;
import com.lzz.crowd.service.api.AdminService;
import com.lzz.crowd.util.CrowdUtil;
import com.lzz.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping("/send/compose/object.json")
    public ResultEntity<Student> testReceiveComposeObject(@RequestBody Student student, HttpServletRequest request){
        boolean judgeResult = CrowdUtil.judgeRequestType(request);

        logger.info("judgeResult="+judgeResult);

        logger.info(student.toString());
        ResultEntity<Student> resultEntity = ResultEntity.successWithoutData(student);
        String a = null;

        System.out.println(a.length());
        return resultEntity;
    }

    @ResponseBody
    @RequestMapping("/send/array/three.html")
    public String testReceiveArrayThree(@RequestBody List<Integer> array){
        for (Integer number : array){
            logger.info("number="+number);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/array/two.html")
    public String testReceiveArrayTwo(ParamData paramData){
        List<Integer> array = paramData.getArray();
        for (Integer number : array){
            System.out.println("number="+number);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/array/one.html")
    public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array){
        for (Integer number : array){
            System.out.println("number="+number);
        }
        return "target";
    }

    @RequestMapping("/test/ssm.html")
    public String testSsm(ModelMap modelMap, HttpServletRequest request){

        boolean judgeResult = CrowdUtil.judgeRequestType(request);

        logger.info("judgeResult="+judgeResult);

        List<Admin> adminList = adminService.getAll();

        modelMap.addAttribute("adminList", adminList);

//        String a = null;
//        System.out.println(a.length());

        System.out.println(10/ 0);
        return "target";
    }

}
