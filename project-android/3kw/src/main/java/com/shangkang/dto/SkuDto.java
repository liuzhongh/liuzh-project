package com.shangkang.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.shangkang.bo.Brand;
import com.shangkang.bo.CompSale;
import com.shangkang.bo.Factory;
import com.shangkang.bo.Favorite;
import com.shangkang.bo.FileUpload;
import com.shangkang.bo.Product;
import com.shangkang.bo.ProductSysinfo;
import com.shangkang.bo.ProductWm;
import com.shangkang.bo.Sku;
import com.shangkang.bo.SkuPolicy;
import com.shangkang.bo.SkuTag;
import com.shangkang.tools.UtilHelper;

public class SkuDto implements Serializable{
	/**
	 * 
	 */
	public static final String IS_CAN_SALE = "canSale";
	
	private static final long	serialVersionUID	= 1L;
	private Long skuId;
	private Sku sku;
	private Product product;
	private Brand brand;
	private Factory factory;
	private FileUpload mainImg;
	private Favorite favorite;
	private ProductWm productWm;
	private SkuTag skuTag;
	private int productWmCount;
	private int productSaleCount;
	//产品图片   
	private List<FileUpload> listFileUpload;
	private List<ProductWm> listProductWm;
	private List<ProductSysinfo> listProductSysinfo;
	private List<SkuPolicy> listSkuPolicy;
	private List<CompSale> listCompSale;
	
	private String minSaleSpec;
	private String mSpec;
	private String wholeSpec;
	private String startDate;
	private String endDate;
	private String drugFormType;
	private Long productId;
	private Long factoryId;
	
	private Long brandId;
	private String productType;
	private String drugformType;
	private String otcTag;
	private String maTag;
	private String rxTag;
	private String agentTag;
	private String productSkuName;
	private String validDate;
	private String skuStatus; 
	private Long stock;
	private String keyWord;
	private String wholeTag;
	private String busiType;
	private String effectType;
	private String tagType;
	private String spec;
	private String approvalNum;
	private String productName;
	private String factoryName;
	private BigDecimal profitCost; //单个毛利
	
	private String canSale;//为空表示不能卖了。为IS_CAN_SALE值 表示能卖
	
	public String getCanSale()
	{
		return canSale;
	}
	public void setCanSale(String canSale)
	{
		this.canSale = canSale;
	}
	public BigDecimal getProfitCost()
	{
		return profitCost;
	}
	public void setProfitCost(BigDecimal profitCost)
	{
		this.profitCost = profitCost;
	}
	private String productImageUrl;
	
	private List<Object> skuTagList;
	
	public List<Object> getSkuTagList() {
		return skuTagList;
	}
	public void setSkuTagList(List<Object> skuTagList) {
		this.skuTagList = skuTagList;
	}
	public String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	/**
	 * @return the factoryName
	 */
	public String getFactoryName() {
		return factoryName;
	}
	/**
	 * @param factoryName the factoryName to set
	 */
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	/**
	 * @return the spec
	 */
	public String getSpec() {
		return spec;
	}
	/**
	 * @param spec the spec to set
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}
	/**
	 * @return the approvalNum
	 */
	public String getApprovalNum() {
		return approvalNum;
	}
	/**
	 * @param approvalNum the approvalNum to set
	 */
	public void setApprovalNum(String approvalNum) {
		this.approvalNum = approvalNum;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the tagType
	 */
	public String getTagType() {
		return tagType;
	}
	/**
	 * @param tagType the tagType to set
	 */
	
	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
	public SkuTag getSkuTag() {
		return skuTag;
	}
	public void setSkuTag(SkuTag skuTag) {
		this.skuTag = skuTag;
	}
	public String getWholeTag() {
		return wholeTag;
	}
	public void setWholeTag(String wholeTag) {
		this.wholeTag = wholeTag;
	}
	public String getBusiType() {
		return busiType;
	}
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}
	public String getEffectType() {
		return effectType;
	}
	public void setEffectType(String effectType) {
		this.effectType = effectType;
	}
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public ProductWm getProductWm() {
		return productWm;
	}
	public void setProductWm(ProductWm productWm) {
		this.productWm = productWm;
	}
	public String getProductSkuName() {
		return productSkuName;
	}
	public void setProductSkuName(String productSkuName) {
		this.productSkuName = productSkuName;
	}
	
	public String getmSpec() {
		return mSpec;
	}
	public void setmSpec(String mSpec) {
		this.mSpec = mSpec;
	}
	public String getDrugFormType() {
		return drugFormType;
	}
	public void setDrugFormType(String drugFormType) {
		this.drugFormType = drugFormType;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getDrugformType() {
		return drugformType;
	}
	public void setDrugformType(String drugformType) {
		this.drugformType = drugformType;
	}
	public String getOtcTag() {
		return otcTag;
	}
	public void setOtcTag(String otcTag) {
		this.otcTag = otcTag;
	}
	public String getMaTag() {
		return maTag;
	}
	public void setMaTag(String maTag) {
		this.maTag = maTag;
	}
	public String getRxTag() {
		return rxTag;
	}
	public void setRxTag(String rxTag) {
		this.rxTag = rxTag;
	}
	public String getAgentTag() {
		return agentTag;
	}
	public void setAgentTag(String agentTag) {
		this.agentTag = agentTag;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	public String getSkuStatus() {
		return skuStatus;
	}
	public void setSkuStatus(String skuStatus) {
		this.skuStatus = skuStatus;
	}
	/**
	 * @return the skuId
	 */
	public Long getSkuId() {
		return skuId;
	}
	/**
	 * @param skuId the skuId to set
	 */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	/**
	 * @return the sku
	 */
	public Sku getSku() {
		return sku;
	}
	/**
	 * @param sku the sku to set
	 */
	public void setSku(Sku sku) {
		this.sku = sku;
	}
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * @return the listProductWm
	 */
	public List<ProductWm> getListProductWm() {
		return listProductWm;
	}
	/**
	 * @param listProductWm the listProductWm to set
	 */
	public void setListProductWm(List<ProductWm> listProductWm) {
		this.listProductWm = listProductWm;		
	}
	/**
	 * @return the listProductSysinfo
	 */
	public List<ProductSysinfo> getListProductSysinfo() {
		return listProductSysinfo;
	}
	/**
	 * @param listProductSysinfo the listProductSysinfo to set
	 */
	public void setListProductSysinfo(List<ProductSysinfo> listProductSysinfo) {
		this.listProductSysinfo = listProductSysinfo;		
	}
	/**
	 * @return the listSkuPolicy
	 */
	public List<SkuPolicy> getListSkuPolicy() {
		return listSkuPolicy;
	}
	/**
	 * @param listSkuPolicy the listSkuPolicy to set
	 */
	public void setListSkuPolicy(List<SkuPolicy> listSkuPolicy) {
		this.listSkuPolicy = listSkuPolicy;
	}
	/**
	 * @return the listCompSale
	 */
	public List<CompSale> getListCompSale() {
		return listCompSale;
	}
	/**
	 * @param listCompSale the listCompSale to set
	 */
	public void setListCompSale(List<CompSale> listCompSale) {
		this.listCompSale = listCompSale;
	}
	/**
	 * @return the listFileUpload
	 */
	public List<FileUpload> getListFileUpload() {
		return listFileUpload;
	}
	/**
	 * @param listFileUpload the listFileUpload to set
	 */
	public void setListFileUpload(List<FileUpload> listFileUpload) {
		this.listFileUpload = listFileUpload;
		
		for (FileUpload upload:listFileUpload) {
			if (upload.getMainFlag() != null && upload.getMainFlag().equals(FileUpload.MAIN_FLAG_TRUE)) {
				setMainImg(upload);
			}
		}
	}
	/**
	 * @return the mainImg
	 */
	public FileUpload getMainImg() {
		return mainImg;
	}
	/**
	 * @param mainImg the mainImg to set
	 */
	public void setMainImg(FileUpload mainImg) {
		this.mainImg = mainImg;
	}
	/**
	 * @return the brand
	 */
	public Brand getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	/**
	 * @return the factory
	 */
	public Factory getFactory() {
		return factory;
	}
	/**
	 * @param factory the factory to set
	 */
	public void setFactory(Factory factory) {
		this.factory = factory;
	}
	/**
	 * @return the productWmCount
	 */
	public int getProductWmCount() {
		return productWmCount;
	}
	/**
	 * @param productWmCount the productWmCount to set
	 */
	public void setProductWmCount(int productWmCount) {
		this.productWmCount = productWmCount;

		if(UtilHelper.isEmpty(sku.getMinSale()))return;
		
		if (productWmCount<sku.getMinSale()) {
			sku.setMinSale((short)0);
			sku.setMaxSale((short)0);
		} else if (sku.getMaxSale()>0 && productWmCount>sku.getMaxSale()){
			//按sku的min,max来执行
		} else {
			sku.setMaxSale((short)productWmCount);
		}
	}
	/**
	 * @return the productSaleCount
	 */
	public int getProductSaleCount() {
		return productSaleCount;
	}
	/**
	 * @param productSaleCount the productSaleCount to set
	 */
	public void setProductSaleCount(int productSaleCount) {
		this.productSaleCount = productSaleCount;
	}
	/**
	 * @return the minSaleSpec
	 */
	public String getMinSaleSpec() {
		return minSaleSpec;
	}
	/**
	 * @param minSaleSpec the minSaleSpec to set
	 */
	public void setMinSaleSpec(String minSaleSpec) {
		this.minSaleSpec = minSaleSpec;
	}
	/**
	 * @return the mSpec
	 */
	public String getMSpec() {
		return mSpec;
	}
	/**
	 * @param mSpec the mSpec to set
	 */
	public void setMSpec(String mSpec) {
		this.mSpec = mSpec;
	}
	/**
	 * @return the wholeSpec
	 */
	public String getWholeSpec() {
		return wholeSpec;
	}
	/**
	 * @param wholeSpec the wholeSpec to set
	 */
	public void setWholeSpec(String wholeSpec) {
		this.wholeSpec = wholeSpec;
	}
	
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	public Long getFactoryId() {
		return factoryId;
	}
	public Favorite getFavorite() {
		return favorite;
	}
	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}
	public void setFactoryId(Long factoryId) {
		this.factoryId = factoryId;
	}
	@Override
	public String toString() {
		return "SkuDto [skuId=" + skuId + ", sku=" + sku + ", product="
				+ product + ", factory=" + factory + ", productWm=" + productWm
				+ ", skuTag=" + skuTag + ", productWmCount=" + productWmCount
				+ ", productSaleCount=" + productSaleCount + ", minSaleSpec="
				+ minSaleSpec + ", mSpec=" + mSpec + ", wholeSpec=" + wholeSpec
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", drugFormType=" + drugFormType + ", productId=" + productId
				+ ", factoryId=" + factoryId + ", brandId=" + brandId
				+ ", productType=" + productType + ", drugformType="
				+ drugformType + ", keyWord=" + keyWord + ", productImageUrl="
				+ productImageUrl + "]";
	}
	
	
	
	
}
