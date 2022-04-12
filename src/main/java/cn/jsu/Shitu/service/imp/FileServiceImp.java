package cn.jsu.projectmanage.service.imp;


import cn.jsu.projectmanage.config.ResourceConfig;
import cn.jsu.projectmanage.dao.fileSystem.FileDao;
import cn.jsu.projectmanage.pojo.dto.CatalogueInfo;
import cn.jsu.projectmanage.pojo.dto.FileInfo;
import cn.jsu.projectmanage.pojo.vo.CommonResult;
import cn.jsu.projectmanage.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService {

    @Autowired
    FileDao fileDao;

    //基础文件路径
//    @Value("${web.upload-path}")
    private static String basePath;

    static {
        basePath = ResourceConfig.getConfigString("base.path");
    }


    @Override
    public CommonResult fileIn(HttpServletRequest request) {

        List<MultipartFile> multipartFiles = ((MultipartHttpServletRequest) request).getFiles("multipartFiles");
        List<String> urls = new ArrayList<>();
        int len = multipartFiles.size();

        FileInfo fileInfo = new FileInfo();

        for (int i = 0; i < len; i++) {
            MultipartFile multipartFile = multipartFiles.get(i);

            //为该文件生成UUID
            String uuid32 = getUUID32();

            String filePath = getFilePath(uuid32);

            File path = new File(filePath);
            //获取存储文件路径
            try {
                multipartFile.transferTo(path);
            } catch (IOException e) {
                e.printStackTrace();
                return new CommonResult(444,"存储文件错误");
            }

            //获取原文件名称
            String name = multipartFile.getOriginalFilename();
            fileInfo.setFileName(name);
            fileInfo.setFileType(multipartFile.getContentType());
            fileInfo.setSize(multipartFile.getSize());
            fileInfo.setCreateTime(new Date());
            fileInfo.setUrl(uuid32);
            try {
                fileDao.insertFile(fileInfo);
                urls.add(uuid32);
            } catch (Exception e) {
                e.printStackTrace();
                new File(filePath).delete();
                return new CommonResult(444,"文件插入失败");
            }
        }

        return new CommonResult(200,"文件存储成功",urls);
    }

    @Override
    public CommonResult fileOut(String url, HttpServletResponse response) {

        String filePath = getFilePath(url);
        try {
            InputStream fileInputStream = new FileInputStream(new File(filePath));

            response.setContentType("image/png");
//            response.reset();
//            response.setHeader("Content-type","image/png");
//            response.addHeader("Content-Length",""+fileInputStream.toString().length());
            response.getOutputStream().write(IOUtils.toString(fileInputStream,
                            String.valueOf(StandardCharsets.UTF_8)).getBytes(StandardCharsets.UTF_8));
//            response.flushBuffer();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new CommonResult(200,"下载成功",filePath);
    }

    @Override
    public CommonResult fileDelete(String url) {

        String filePath = getFilePath(url);
        new File(filePath).delete();
        fileDao.deleteFile(url);

        return new CommonResult(200,"删除成功");
    }


    /**
     * 将目录信息插入目录表，父id可以为空
     * @param catalogueInfo
     * @return
     */
    @Override
    public CommonResult createCatalogue(CatalogueInfo catalogueInfo) {
        try {
            fileDao.createCatalogue(catalogueInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(444,"创建失败");
        }
        return new CommonResult(200,"创建成功");
    }

    /**
     * 修改目录名
     * @param catalogueInfo
     * @return
     */
    @Override
    public CommonResult updateCatalogue(CatalogueInfo catalogueInfo) {
        try {
            fileDao.updateCatalogue(catalogueInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(444,"更新失败");
        }
        return new CommonResult(200,"更新成功");
    }

    /**
     * done
     * 删除目录下的所有目录和目录中的文件，已设置文件id为外键，只需删除目录下的所有目录
     * @param catalogueId
     * @return
     */
    @Override
    public CommonResult deleteCatalogue(Integer catalogueId) {
        try {
//            List<Integer> idList = new ArrayList<>();
//            this.getChildIdList(idList, catalogueId);
            fileDao.deleteCatalogue(catalogueId);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(444,"删除失败");
        }
        return new CommonResult(200,"删除成功");
    }
//  递归查子节点
//    public void getChildIdList(List<Integer> idList, Integer parentId) {
//        idList.add(parentId);
//        List<CatalogueInfo> childList = fileDao.queryCatalogueChildByParentId(parentId);
//        for (CatalogueInfo child : childList) {
//            idList.add(child.getCatalogueId());
//            getChildIdList(idList, child.getCatalogueId());
//        }
//    }

    @Override
    public CommonResult queryCatalogueByCatalogue(Integer catalogueId) {
        List<CatalogueInfo> catalogueInfos = fileDao.queryCatalogueByCatalogue(catalogueId);
        return new CommonResult(200,"查询成功",catalogueInfos);
    }

    @Override
    public CommonResult queryFileByCatalogue(Integer catalogueId) {
        List<FileInfo> fileInfos = fileDao.queryFileByCatalogue(catalogueId);
        return new CommonResult(200,"查询成功",fileInfos);
    }

    /**
     * 先将文件存好，再将目录与文件关联
     * @param catalogueId
     * @param request
     * @return
     */
    @Override
    public CommonResult createCatalogueFile(Integer catalogueId, HttpServletRequest request) {
        CommonResult commonResult = fileIn(request);
        if (commonResult.getCode()  == 200)
        {
            List<String> urls =  (List<String>)commonResult.getData();
            //查询对应fileId
            FileInfo fileInfo = fileDao.queryFileByUrl(urls.get(0));
            fileDao.insertCatalogueFile(catalogueId,fileInfo.getFileId());
        }
        return new CommonResult(200,"创建成功");
    }


    @Override
    public CommonResult deleteCatalogueFile(Integer catalogueId,Integer fileId) {
        fileDao.deleteCatalogueFile(catalogueId,fileId);
        return new CommonResult(200, "删除成功");
    }


    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    /**
     * 获取文件路径(后端生成)
     */
    public static String getFilePath(String uuid32){

        return basePath +File.separator+ uuid32;
    }


}
