/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: zzqiang
 * Created On: 2013-3-6
 *
 * Amendment History:
 *
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.dto;

import java.io.Serializable;
import java.util.List;

import com.shangkang.bo.CommonType;
import com.shangkang.bo.CompanyCommend;
import com.shangkang.bo.CustDelivery;
import com.shangkang.bo.Customer;
import com.shangkang.bo.Exhibition;
import com.shangkang.bo.Express;
import com.shangkang.bo.Factory;
import com.shangkang.bo.Favorite;
import com.shangkang.bo.FileUpload;
import com.shangkang.bo.FreightTemplate;
import com.shangkang.bo.Product;
import com.shangkang.bo.ProductExt;
import com.shangkang.bo.ProductPolicy;
import com.shangkang.bo.ProductWm;

public class ProductDetailDto implements Serializable {
    public static final String IS_VALID_DATE_YES = "1";
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /*
     * 产品信息： 产品基本信息: t_product 产品扩展信息: t_product_ext 产品图片：t_file_upload 产品价格:
     * t_product_policy（价格） 库存数量： t_product_wm
     *
     * 与产品关联的信息： 生成厂家： t_factory 产品价格授权表：t_price_policy
     *
     * //供应商其他相关产品
     */
    private Long productId;

    private Product product;

    private ProductExt productExt;

    private String moduleId;

    private FileUpload mainFileUpload;

    private FileUpload fileUpload;

    private List<FileUpload> listFileUpload;

    private ProductWm productWm;

    private List<ProductPolicyDto> listProductPolicyDto;        // 放置产品的价格策略与价格策略是否可见的信息

    private Factory factory;

    private Favorite favorite;

    private int favoriteTimes;              //收藏次数

    private String showPrice;                    // 放置产品最低价格

    private Customer supplyInfo;

    private CustDelivery custDelivery;

    private CommonType drugformType;

    private CommonType effectType;

    private CommonType productType;

    private Boolean isAuthorVisible;

    private CommonType productStatus;

    private CommonType auditStatus;

    private CommonType busiType;

    private Exhibition exhibition;

    private int minCount;
    private Double lowPrice;

    private List<Long> listCatalogId;

    private CompanyCommend companyCommend;

    private String topestProductType;

    private String searchStartDate;

    private String searchEndDate;

    private String orderField;

    private String onlineShop;

    private String auditReason;

    private FreightTemplate freightTemplate;

    private List<ProductPolicy> listProductPolicy;

    private String headImageUrl;
    /**
     * 价格是否可见
     */
    private String vipProductPriceVisible = "1";
    private String priceNotVisibleInfo = "";
    private String templateId;
    private String shipMethod;
    private Express express;
    private String defaultPrice;
    private String isValidDate;
    private int saleCount;

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public FreightTemplate getFreightTemplate() {
        return freightTemplate;
    }

    public void setFreightTemplate(FreightTemplate freightTemplate) {
        this.freightTemplate = freightTemplate;
    }

    public Express getExpress() {
        return express;
    }

    public void setExpress(Express express) {
        this.express = express;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getShipMethod() {
        return shipMethod;
    }

    public void setShipMethod(String shipMethod) {
        this.shipMethod = shipMethod;
    }

    public String getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(String defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public String getPriceNotVisibleInfo() {
        return priceNotVisibleInfo;
    }

    public void setPriceNotVisibleInfo(String priceNotVisibleInfo) {
        this.priceNotVisibleInfo = priceNotVisibleInfo;
    }

    public String getIsValidDate() {
        return isValidDate;
    }

    public void setIsValidDate(String isValidDate) {
        this.isValidDate = isValidDate;
    }

    public String getVipProductPriceVisible() {
        return vipProductPriceVisible;
    }

    public void setVipProductPriceVisible(String vipProductPriceVisible) {
        this.vipProductPriceVisible = vipProductPriceVisible;
    }

    public List<ProductPolicy> getListProductPolicy() {
        return listProductPolicy;
    }

    public void setListProductPolicy(List<ProductPolicy> listProductPolicy) {
        this.listProductPolicy = listProductPolicy;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public String getTopestProductType() {
        return topestProductType;
    }

    public void setTopestProductType(String topestProductType) {
        this.topestProductType = topestProductType;
    }

    public String getSearchStartDate() {
        return searchStartDate;
    }

    public void setSearchStartDate(String searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public String getSearchEndDate() {
        return searchEndDate;
    }

    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }

    public String getOnlineShop() {
        return onlineShop;
    }

    public void setOnlineShop(String onlineShop) {
        this.onlineShop = onlineShop;
    }

    public CommonType getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(CommonType auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public List<Long> getListCatalogId() {
        return listCatalogId;
    }

    public void setListCatalogId(List<Long> listCatalogId) {
        this.listCatalogId = listCatalogId;
    }

    public int getMinCount() {
        return minCount;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public CommonType getBusiType() {
        return busiType;
    }

    public void setBusiType(CommonType busiType) {
        this.busiType = busiType;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public CommonType getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(CommonType productStatus) {
        this.productStatus = productStatus;
    }

    public Boolean getIsAuthorVisible() {
        return isAuthorVisible;
    }

    public void setIsAuthorVisible(Boolean isAuthorVisible) {
        this.isAuthorVisible = isAuthorVisible;
    }

    public CommonType getEffectType() {
        return effectType;
    }

    public void setEffectType(CommonType effectType) {
        this.effectType = effectType;
    }

    public CommonType getProductType() {
        return productType;
    }

    public void setProductType(CommonType productType) {
        this.productType = productType;
    }

    public CommonType getDrugformType() {
        return drugformType;
    }

    public void setDrugformType(CommonType drugformType) {
        this.drugformType = drugformType;
    }

    public CustDelivery getCustDelivery() {
        return custDelivery;
    }

    public void setCustDelivery(CustDelivery custDelivery) {
        this.custDelivery = custDelivery;
    }

    public Customer getSupplyInfo() {
        return supplyInfo;
    }

    public void setSupplyInfo(Customer supplyInfo) {
        this.supplyInfo = supplyInfo;
    }

    public String getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(String showPrice) {
        this.showPrice = showPrice;
    }

    public List<FileUpload> getListFileUpload() {
        return listFileUpload;
    }

    public void setListFileUpload(List<FileUpload> listFileUpload) {
        this.listFileUpload = listFileUpload;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductExt getProductExt() {
        return productExt;
    }

    public void setProductExt(ProductExt productExt) {
        this.productExt = productExt;
    }

    public FileUpload getMainFileUpload() {
        return mainFileUpload;
    }

    public void setMainFileUpload(FileUpload mainFileUpload) {
        this.mainFileUpload = mainFileUpload;
    }

    public ProductWm getProductWm() {
        return productWm;
    }

    public void setProductWm(ProductWm productWm) {
        this.productWm = productWm;
    }

    public List<ProductPolicyDto> getListProductPolicyDto() {
        return listProductPolicyDto;
    }

    public void setListProductPolicyDto(
            List<ProductPolicyDto> listProductPolicyDto) {
        this.listProductPolicyDto = listProductPolicyDto;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public Favorite getFavorite() {
        return favorite;
    }

    public void setFavorite(Favorite favorite) {
        this.favorite = favorite;
    }


    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }


    public int getFavoriteTimes() {
        return favoriteTimes;
    }

    public void setFavoriteTimes(int favoriteTimes) {
        this.favoriteTimes = favoriteTimes;
    }


    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public CompanyCommend getCompanyCommend() {
        return companyCommend;
    }

    public void setCompanyCommend(CompanyCommend companyCommend) {
        this.companyCommend = companyCommend;
    }


    public FileUpload getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }


    @Override
    public String toString() {
        return "ProductDetailDto [productId=" + productId + ", product="
                + product + ", productExt=" + productExt + ", moduleId="
                + moduleId + ", mainFileUpload=" + mainFileUpload
                + ", fileUpload=" + fileUpload + ", listFileUpload="
                + listFileUpload + ", productWm=" + productWm
                + ", listProductPolicyDto=" + listProductPolicyDto
                + ", factory=" + factory + ", favorite=" + favorite
                + ", favoriteTimes=" + favoriteTimes + ", showPrice="
                + showPrice + ", supplyInfo=" + supplyInfo + ", custDelivery="
                + custDelivery + ", drugformType=" + drugformType
                + ", effectType=" + effectType + ", productType=" + productType
                + ", isAuthorVisible=" + isAuthorVisible + ", productStatus="
                + productStatus + ", auditStatus=" + auditStatus
                + ", busiType=" + busiType + ", exhibition=" + exhibition
                + ", minCount=" + minCount + ", lowPrice=" + lowPrice
                + ", listCatalogId=" + listCatalogId + ", companyCommend="
                + companyCommend + ", topestProductType=" + topestProductType
                + ", searchStartDate=" + searchStartDate + ", searchEndDate="
                + searchEndDate + ", orderField=" + orderField
                + ", onlineShop=" + onlineShop + ", auditReason=" + auditReason
                + ", freightTemplate=" + freightTemplate
                + ", listProductPolicy=" + listProductPolicy
                + ", vipProductPriceVisible=" + vipProductPriceVisible
                + ", priceNotVisibleInfo=" + priceNotVisibleInfo
                + ", isValidDate=" + isValidDate + ", saleCount=" + saleCount
                + "]";
    }


}
