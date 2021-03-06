package com.itheima.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Region;
import com.itheima.bos.service.IRegionService;
import com.itheima.bos.utils.PinYin4jUtils;
import com.itheima.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{
	//属性驱动，接收上传的文件
	private File regionFile;
	//业务对象
	@Autowired
	private IRegionService regionService;
	//下拉列表异步上传搜索区域的值
	private String q;
	
	
	public void setQ(String q) {
		this.q = q;
	}

	public File getRegionFile() {
		return regionFile;
	}

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}
	
	/**
	 * 区域导入
	 * 1.获取文件；
	 * 2.读取sheet表；获得行集合；从每行中获得列数据；
	 * 3.封装进区域实体对象中；
	 * 4.通过业务方法，保存区域对象；
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String importXls() throws FileNotFoundException, IOException{
		List<Region> regions = new ArrayList<>();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(regionFile));
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		for (Row row : hssfSheet) {
			int firstRow = row.getRowNum();
			if (firstRow == 0) {
				continue;
			}
			//获取每行每列的值
			 String id = row.getCell(0).getStringCellValue();
			 String province  = row.getCell(1).getStringCellValue();
			 String city      = row.getCell(2).getStringCellValue();
			 String district  = row.getCell(3).getStringCellValue();
			 String postcode  = row.getCell(4).getStringCellValue();
			 //封装每行数据
			 Region region = new Region(id, province, city, district, postcode, null, null, null);
			 //封装简码和城市码
			province = province.substring(0, province.length() - 1);
          	city = city.substring(0, city.length() - 1);
          	district = district.substring(0, district.length() - 1);
          	String info = province + city + district;
          	String[] headByString = PinYin4jUtils.getHeadByString(info);
          	String shortcode = StringUtils.join(headByString);
          	//城市编码---->>shijiazhuang
          	String citycode = PinYin4jUtils.hanziToPinyin(city, "");
          	
          	region.setShortcode(shortcode);
          	region.setCitycode(citycode);
          	regions.add(region);
			
		}
		regionService.saveBatch(regions);
		return NONE;
	}
	/**
	 * 功能：
	 * 1.分页查询区域数据；
	 * @return
	 */
	public String pageQuery() {
		regionService.pageQuery(pageBean);
		this.java2Json(pageBean, 
					new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
		return NONE;
	}
	
	/**
	 * 功能：
	 * 1.获取区域数据，返回json格式的数据；
	 * @return
	 */
	public String listAjax() {
		/**
		 * 1.获取所有区域数据;
		 * 2.将数据转成json格式；
		 */
		List<Region> list = null;
		if (StringUtils.isNotBlank(q)) {
			list = regionService.findListByQ(q);
		}else {
			list = regionService.findAll();
		}
		
		this.java2Json(list, 
					new String[]{"province","city","district","postcode","shortcode","citycode","subareas"});
		return NONE;
	}	
	
}
