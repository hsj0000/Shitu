package cn.jsu.projectmanage.service;



import cn.jsu.projectmanage.pojo.dto.CatalogueInfo;
import cn.jsu.projectmanage.pojo.vo.CommonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {

    public CommonResult fileIn(HttpServletRequest request);

    public CommonResult fileOut(String url, HttpServletResponse response);

    public CommonResult fileDelete(String url);

    public CommonResult createCatalogue(CatalogueInfo catalogueInfo);

    public CommonResult updateCatalogue(CatalogueInfo catalogueInfo);

    public CommonResult deleteCatalogue(Integer catalogueId);

    public CommonResult queryCatalogueByCatalogue(Integer catalogueId);

    public CommonResult queryFileByCatalogue(Integer catalogueId);

    public CommonResult createCatalogueFile(Integer catalogueId, HttpServletRequest request);

    public CommonResult deleteCatalogueFile(Integer catalogueId,Integer fileId);
}
