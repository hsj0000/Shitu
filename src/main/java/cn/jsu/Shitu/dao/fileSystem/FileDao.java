package cn.jsu.projectmanage.dao.fileSystem;

import cn.jsu.projectmanage.pojo.dto.CatalogueInfo;
import cn.jsu.projectmanage.pojo.dto.FileInfo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface FileDao {

    public void insertFile(FileInfo fileInfo);

    public void deleteFile(String url);

    public void createCatalogue(CatalogueInfo catalogueInfo);

    public void updateCatalogue(CatalogueInfo catalogueInfo);

    /**
     * 删除目录下的所有子目录
     * @param catalogueId
     */
    public int deleteCatalogue(Integer catalogueId);

    public List<CatalogueInfo> queryCatalogueByCatalogue(Integer catalogueId);

    public List<FileInfo> queryFileByCatalogue(Integer catalogueId);

    public FileInfo queryFileByUrl(String url);

    public void deleteCatalogueFile(Integer catalogueId,Integer fileId);

    public void insertCatalogueFile(Integer catalogueId,Integer fileId);

}
