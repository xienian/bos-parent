package com.itheima.bos.utils;
/**
 * 封装分类数据
 * @author 10334
 *
 */

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBean {
   private    int currentPage;
   private    int total;
   private    int pageSize;
   private    List rows;
   private    DetachedCriteria detachedCriteria;
   
   
   
public int getCurrentPage() {
	return currentPage;
}
public void setCurrentPage(int currentPage) {
	this.currentPage = currentPage;
}
public int getTotal() {
	return total;
}
public void setTotal(int total) {
	this.total = total;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}
public List getRows() {
	return rows;
}
public void setRows(List rows) {
	this.rows = rows;
}
public DetachedCriteria getDetachedCriteria() {
	return detachedCriteria;
}
public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
	this.detachedCriteria = detachedCriteria;
}
   
   

}
