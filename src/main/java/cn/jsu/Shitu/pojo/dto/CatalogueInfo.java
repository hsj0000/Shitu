package cn.jsu.projectmanage.pojo.dto;

import lombok.Data;

@Data
public class CatalogueInfo {
    private Integer catalogueId;
    private Integer parentId;
    private String catalogueName;

    public CatalogueInfo(Integer catalogueId, Integer parentId, String catalogueName) {
        this.catalogueId = catalogueId;
        this.parentId = parentId;
        this.catalogueName = catalogueName;
    }

    public CatalogueInfo() {
    }
}
