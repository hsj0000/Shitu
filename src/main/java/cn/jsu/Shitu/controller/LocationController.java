package cn.jsu.projectmanage.controller;


import cn.jsu.projectmanage.pojo.dto.CatalogueInfo;
import cn.jsu.projectmanage.pojo.vo.CommonResult;
import cn.jsu.projectmanage.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping(path = "fileIn")
    @ApiOperation(value = "上传文件")
    public CommonResult fileIn(HttpServletRequest request) {
        return fileService.fileIn(request);

    }

    @PostMapping(path = "fileDelete")
    @ApiOperation(value = "删除文件")
    public CommonResult fileDelete(@RequestParam("url") String url) {
        return fileService.fileDelete(url);

    }

    @GetMapping(path = "fileOut")
    @ApiOperation(value = "下载文件")
    public CommonResult fileOut(@RequestParam("url")String url,HttpServletResponse response) {

      return fileService.fileOut(url,response);
    }

    @GetMapping(path = "createCatalogue")
    @ApiOperation(value = "创建目录")
    public CommonResult createCatalogue(@RequestBody CatalogueInfo catalogueInfo) {
        return   fileService.createCatalogue(catalogueInfo);
    }

    @GetMapping(path = "updateCatalogue")
    @ApiOperation(value = "修改目录名")
    public CommonResult updateCatalogue(@RequestBody CatalogueInfo catalogueInfo) {
        return   fileService.updateCatalogue(catalogueInfo);
    }

    @GetMapping(path = "deleteCatalogue")
    @ApiOperation(value = "删除目录")
    public CommonResult deleteCatalogue(@RequestParam("catalogueId") Integer catalogueId) {
        return   fileService.deleteCatalogue(catalogueId);
    }

    @GetMapping(path = "queryCatalogueByCatalogue")
    @ApiOperation(value = "根据目录查询子目录")
    public CommonResult queryCatalogueByCatalogue(@RequestParam("catalogueId") Integer catalogueId) {
        return   fileService.queryCatalogueByCatalogue(catalogueId);
    }

    @GetMapping(path = "queryFileByCatalogue")
    @ApiOperation(value = "查询目录中的文件")
    public CommonResult queryFileByCatalogue(@RequestParam("catalogueId") Integer catalogueId) {
        return   fileService.queryFileByCatalogue(catalogueId);
    }

    @GetMapping(path = "createCatalogueFile")
    @ApiOperation(value = "上传目录文件")
    public CommonResult createCatalogueFile(HttpServletRequest request,
                                            @RequestParam("catalogueId") Integer catalogueId) {
        return   fileService.createCatalogueFile(catalogueId,request);
    }

    @GetMapping(path = "deleteCatalogueFile")
    @ApiOperation(value = "删除目录文件")
    public CommonResult deleteCatalogueFile(@RequestParam("catalogueId") Integer catalogueId,
                                            @RequestParam("fileId") Integer fileId) {
        return   fileService.deleteCatalogueFile(catalogueId,fileId);
    }
}
