//package com.example.controller;
//
//import com.example.common.Result;
//import com.example.entity.Account;
//import com.example.entity.NewsMenuInfo;
//import com.example.service.NewsMenuInfoService;
//import com.example.vo.NewsMenuInfoVo;
//import com.github.pagehelper.PageInfo;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//@RestController
//@RequestMapping("/newsMenuInfo")
//public class NewsMenuInfoController {
//
//    @Resource
//    private NxSystemFileController nxSystemFileController;
//    @Resource
//    private NewsMenuInfoService newsMenuInfoService;
//
//    @PostMapping
//    public Result<NewsMenuInfo> add(@RequestBody NewsMenuInfo info, HttpServletRequest request) {
//        Account account = (Account) request.getSession().getAttribute("user");
//        info.setUserName(account.getName());
//        info.setLevel(account.getLevel());
//        info.setUploadUserId(account.getId());
//        newsMenuInfoService.add(info);
//        return Result.success(info);
//    }
//
//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Long id, HttpServletRequest request) {
//        Account account = (Account) request.getSession().getAttribute("user");
//        NewsMenuInfo info = newsMenuInfoService.findById(id);
//        if (!account.getLevel().equals(info.getLevel()) || !account.getId().equals(info.getUploadUserId())) {
//            return Result.error("1001", "不能删除他人的记录");
//        }
//        newsMenuInfoService.delete(id);
//        // 删除对应文件记录
//        if (info.getFileId() != null) {
//            nxSystemFileController.deleteFile(info.getFileId().toString());
//        }
//        return Result.success();
//    }
//
//    @PutMapping
//    public Result update(@RequestBody NewsMenuInfo info, HttpServletRequest request) {
//        Account account = (Account) request.getSession().getAttribute("user");
//        if (!account.getLevel().equals(info.getLevel()) || !account.getId().equals(info.getUploadUserId())) {
//            return Result.error("1001", "不能修改他人的记录");
//        }
//        newsMenuInfoService.update(info);
//        return Result.success();
//    }
//
//    @GetMapping("/{id}")
//    public Result<NewsMenuInfoVo> detail(@PathVariable Long id) {
//        NewsMenuInfoVo info = newsMenuInfoService.findById(id);
//        return Result.success(info);
//    }
//
//    @GetMapping
//    public Result<List<NewsMenuInfoVo>> all() {
//        return Result.success(newsMenuInfoService.findAll());
//    }
//
//    @GetMapping("/page/{name}")
//    public Result<PageInfo<NewsMenuInfoVo>> page(@PathVariable String name,
//                                                  @RequestParam(required = false) Long classifyId,
//                                                  @RequestParam(defaultValue = "1") Integer pageNum,
//                                                  @RequestParam(defaultValue = "5") Integer pageSize,
//                                                  HttpServletRequest request) {
//        return Result.success(newsMenuInfoService.findPage(name, classifyId, pageNum, pageSize));
//    }
//
//    @GetMapping("/page/user/{name}")
//    public Result<PageInfo<NewsMenuInfoVo>> userPage(@PathVariable String name,
//                                                      @RequestParam(required = false) String username,
//                                                      @RequestParam(required = false) Integer level,
//                                                      @RequestParam(defaultValue = "1") Integer pageNum,
//                                                      @RequestParam(defaultValue = "5") Integer pageSize,
//                                                      HttpServletRequest request) {
//        return Result.success(newsMenuInfoService.findPageByUser(name, username, level, pageNum, pageSize));
//    }
//
//}
