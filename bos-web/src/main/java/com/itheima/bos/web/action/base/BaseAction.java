package com.itheima.bos.web.action.base;

import com.itheima.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T> {
	//分页对象
	protected PageBean pageBean = new PageBean();

	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}
	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}

	//几个视图
	protected static final String HOME = "home";
	protected static final String LIST = "list";

	protected T model;
	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	//通过构造函数，给model属性赋值
	public BaseAction() {
		Class<T> clazz = (Class<T>) this.getClass();
		ParameterizedType parameterizedType =  (ParameterizedType) clazz.getGenericSuperclass();
		Type[] types = parameterizedType.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) types[0];
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		pageBean.setDetachedCriteria(detachedCriteria);
		try {
			model = entityClass.newInstance();
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能：
	 * 1.将对象转换成json格式数据
	 */
	public void java2Json(Object o,String[] excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		String json = JSONObject.fromObject(o, jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 重载函数，功能：
	 * 1.将集合转成json数组；
	 * @param o
	 * @param excludes
	 */
	public void java2Json(List<?> o,String[] excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		String json = JSONArray.fromObject(o, jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	


}
