package wuye.logic.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

import wuye.bean.AssessDataBean;
import wuye.bean.BadCheckList;
import wuye.bean.CheckDayItem;
import wuye.bean.CheckTitle;
import wuye.bean.PianquData;
import wuye.bean.PianquRelationHead;
import wuye.bean.PianquSortListBean;
import wuye.bean.PicBean;
import wuye.bean.StaticString;
import wuye.bean.WuyeSortListBean;
import wuye.dao.AssessDao;
import wuye.logic.AssessLogic;

public class AssessLogicImpl implements AssessLogic {

	private AssessDao assessDao;
	
	
	HSSFCellStyle styleblack;
	HSSFCellStyle stylesimple;
	HSSFCellStyle stylesimpleLeft;
	HSSFCellStyle styleblackbaifenbi;
	
	public AssessDao getAssessDao() {
		return assessDao;
	}

	public void setAssessDao(AssessDao assessDao) {
		this.assessDao = assessDao;
	}

	public int submit(AssessDataBean data) {
		
		return assessDao.submit(data);
	}


	public List<String> getPoint(int street,int pianqu,String time) {
		return assessDao.getPoint(street, pianqu, time);
	}
	
	public List<CheckDayItem> getCheckDayList(Date dStart, Date dEnd,
			String pianquid, String checkyenei, int page) {
		return assessDao.getCheckDayList(dStart, dEnd, pianquid, checkyenei, page);
	}

	@Override
	public List<AssessDataBean> getDetailitem(Date dStart,
			String pianquid, String checkyenei, int page) {
		return assessDao.getDetailitem(dStart, pianquid, checkyenei, page);
	}

	@Override
	public void doSumWeek() {
		assessDao.weekjisuanpianqu(); // 
		assessDao.weekSumWuye();      // 
	}

	@Override
	public void doSumMonth() {
		assessDao.monthSumPianqu(1);
		assessDao.monthSumWuye();

	}

	@Override
	public List<PianquData> getPianquSortData(char type, String date, int jibie, String areaid) {
		return assessDao.getPianquSortData(type, date, jibie, areaid);
	}

	@Override
	public List<PianquSortListBean> getPianquSortList(char type, String date, String pianquid) {
		
		return assessDao.getPianquSortList(type, date, pianquid);
	}

	@Override
	public List<WuyeSortListBean> getWuyeSortList(char type, String date, String wuyeid) {
		return assessDao.getWuyeSortList(type, date, wuyeid);
	}

	@Override
	public BadCheckList getBadCheck(char type, String date) {
		return assessDao.getBadCheck(type, date);
	}
	
	@Override
	public int doneWordData(int date) {
		Map<Integer, String> title =  assessDao.getAssessid();
		List<PianquData> list = assessDao.getPianquWeekData(date);
		List<String> fileList = new ArrayList<String>(50);
		for(int i=0,len=list.size(); i< len;i++) {
			String name = String.valueOf(date) + list.get(i).getPianquName() + ".html";
			fileList.add(name);
			File file = new File("/home/htdocs/doc/" + name);
			try {
				FileOutputStream out = new FileOutputStream(file); 
				List<PicBean>  detailList = assessDao.getPicList(list.get(i).getPianquid(), date);
				out.write(StaticString.s1.getBytes());
				out.write(String.format("<h1>%s</h1>检查时间:%s<br/>检查区域:%s<br/>得分:%.2f<br/>检查人员:%s <ul>", 
						list.get(i).getPianquName(), 
						"",
						list.get(i).getAreaWholeName(),
						list.get(i).getScore()*0.7, 
						"").getBytes("UTF-8"));
				
				if(detailList != null) {
					for(int j=0,len2 = detailList.size(); j<len2; j++) {
						if(detailList.get(j) != null) {
							out.write(String.format("<h2>%s</h2>扣分值: %.1f<br/>扣分处：%d处<br/>扣分原因：%s<br/> 照片:<ul>", 
									title.get(detailList.get(j).getAssessid()),
									detailList.get(j).getScore(),
									detailList.get(j).getNum(),
									detailList.get(j).getMsg()
									).getBytes("UTF-8"));
							for(int z=0,len3=detailList.get(j).getPiclist().size(); z<len3;z++) {
								out.write(String.format("<li><img src=\"%s\" height=\"120\" width=\"200\"/></li>","/home/htdocs/pic/"+detailList.get(j).getPiclist().get(z)).getBytes("UTF-8"));
							}
							out.write("</ul>".getBytes());
						}
					}
				}
				out.write("</ul></body></html> ".getBytes());
				out.flush();
				out.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		
		// to pdf
		for(int i=0,len=fileList.size(); i< len;i++) {
			try {
				convertHtmlToPdf("/home/htdocs/doc/"+fileList.get(i),"/home/htdocs/doc/"+ fileList.get(i).replace("html", "pdf"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return 0;
	}

	@Override
	public int getPianquWeekData(int date) {
		HSSFWorkbook wb = new HSSFWorkbook();
		
		styleblack = wb.createCellStyle();  
		styleblack.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		HSSFFont font = wb.createFont();    
		font.setFontName("黑体");    
		font.setFontHeightInPoints((short) 14);//设置字体大小  
		font.setBoldweight((short)4);
		styleblack.setFont(font);
		
		stylesimple = wb.createCellStyle();  
		stylesimple.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		HSSFFont font2 = wb.createFont();        
		font2.setFontHeightInPoints((short) 13);//设置字体大小  
		stylesimple.setFont(font2);
		
		stylesimpleLeft = wb.createCellStyle();  
		stylesimpleLeft.setFont(font2);
		
		styleblackbaifenbi =wb.createCellStyle();  
		styleblackbaifenbi.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
		
		// sheet 1
        HSSFSheet sheet = wb.createSheet("基础数据");  
        sheet.setDefaultColumnWidth(12);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 7)); 
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 8, 9)); 
        
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        
  
        HSSFCell cell = row.createCell(0);  
        cell.setCellValue("序号");  
        cell.setCellStyle(styleblack);  
        cell = row.createCell(1);  
        cell.setCellValue("街道办事处");  
        cell.setCellStyle(styleblack);  
        cell = row.createCell(2);  
        cell.setCellValue("区域");  
        cell.setCellStyle(styleblack);  
        cell = row.createCell(3);  
        cell.setCellValue("级别");  
        cell.setCellStyle(styleblack); 
        cell = row.createCell(4);  
        cell.setCellValue("物业公司");  
        cell.setCellStyle(styleblack);  
        cell = row.createCell(5);  
        cell.setCellValue("胡同名称");  
        cell.setCellStyle(styleblack);  
        cell = row.createCell(6);  
        cell.setCellValue("内业");  
        cell.setCellStyle(styleblack);  
        cell = row.createCell(8);  
        cell.setCellValue("外页");  
        cell.setCellStyle(styleblack);  
        cell = row.createCell(10);  
        cell.setCellValue("总分");  
        cell.setCellStyle(styleblack);
        
        
        List<PianquData> list = assessDao.getPianquWeekData(date);
        
        for (int i = 0, length = list.size(); i < length; i++)
        {  
            row = sheet.createRow((int) i + 1);
            PianquData stu = (PianquData) list.get(i);  
            // 第四步，创建单元格，并设置值  
            cell = row.createCell(0);   // 序号
            cell.setCellValue(i + 1);
            cell.setCellStyle(stylesimple);
            cell = row.createCell(1);
            cell.setCellValue(stu.getStreetName());
            cell.setCellStyle(stylesimple);
            cell = row.createCell(2);  
            cell.setCellValue(stu.getPianquName());
            cell.setCellStyle(stylesimple);
            cell = row.createCell(3);  
            cell.setCellValue(stu.getLevelName());
            cell.setCellStyle(stylesimple);
            cell = row.createCell(4); 
            cell.setCellValue(stu.getWuyeName());
            cell.setCellStyle(stylesimple);
            cell = row.createCell(5);
            cell.setCellValue(stu.getHutongName());
            cell.setCellStyle(stylesimple);
            cell = row.createCell(6);  
            cell.setCellValue(stu.getNeidel());
            cell.setCellStyle(stylesimple);
            cell = row.createCell(7);
            cell.setCellValue(stu.getNeiscore()*0.3);
            cell.setCellStyle(stylesimple);
            cell = row.createCell(8);
            cell.setCellValue(stu.getWaidel());
            cell.setCellStyle(stylesimple);
            cell = row.createCell(9);
            cell.setCellValue(stu.getWaiscore()*0.7);
            cell.setCellStyle(stylesimple);
            cell = row.createCell(10);
            cell.setCellValue(stu.getScore());
            cell.setCellStyle(stylesimple);
        }
        
        // sheet 2
        doSheet(wb, "外业-扣分汇总-%", 2, date);
        
        // sheet 3
        doSheet(wb, "内业-扣分汇总-%", 1, date);
        
        
        // last step xiewenjian
        try  
        {
        	//FileOutputStream fout = new FileOutputStream("E:/22/"+new SimpleDateFormat("yyyy-mm-dd").format(new Date())+".xls");
           FileOutputStream fout = new FileOutputStream("/home/htdocs/doc/"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xls");  
            wb.write(fout);
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();
        } 
		
		return 0;
	}
	
	private void doSheet(HSSFWorkbook wb, String name, int yenei, int date) {
		HSSFSheet sheet2 = wb.createSheet(name);
        
        sheet2.setDefaultColumnWidth(12);
        sheet2.setColumnWidth(2, 1200*18);
        sheet2.addMergedRegion(new CellRangeAddress(0, 3, 0, 0)); 
        sheet2.addMergedRegion(new CellRangeAddress(0, 3, 1, 1)); 
        sheet2.addMergedRegion(new CellRangeAddress(0, 3, 2, 2)); 
        sheet2.addMergedRegion(new CellRangeAddress(0, 3, 3, 3));
        HSSFRow rowtemp = null;
    	HSSFCell celltemp = null;
    	Row rowMap = new Row(sheet2);
    	rowtemp = rowMap.getRow(0);
        celltemp = rowtemp.createCell(0);
        celltemp.setCellValue("项目");
        celltemp.setCellStyle(styleblack);
    	celltemp = rowtemp.createCell(1);
    	celltemp.setCellValue("序号");
    	celltemp.setCellStyle(styleblack);
    	celltemp = rowtemp.createCell(2);
    	celltemp.setCellValue("考评内容");
    	celltemp.setCellStyle(styleblack);
    	celltemp = rowtemp.createCell(3);
    	celltemp.setCellValue("分值");
    	celltemp.setCellStyle(styleblack);
    	
    	
    	// 列表头
        List<PianquRelationHead> pianquList = assessDao.getRelationPainqu();
        int titleindexx = 4;
        rowtemp = rowMap.getRow(0);
        for(int i=0, length = pianquList.size(); i<length; i++) {
        	PianquRelationHead title = pianquList.get(i);
        	int size = title.getCount();
        	sheet2.addMergedRegion(new CellRangeAddress(0, 0, titleindexx, titleindexx + size - 1));
        	
        	celltemp = rowtemp.createCell(titleindexx);
        	celltemp.setCellValue(title.getName());
        	celltemp.setCellStyle(styleblack);
        	titleindexx += size;
        }
        titleindexx = 4;
        rowtemp = rowMap.getRow(1);
        for(int i=0, length = pianquList.size(); i<length; i++) {
        	PianquRelationHead title = pianquList.get(i);
        	int size = title.getCount();
        	
        	for(int j = 0;j<size;j++) {
        		celltemp = rowtemp.createCell(titleindexx + j);
        		celltemp.setCellValue(title.getSub().get(j).getName());
        		celltemp.setCellStyle(styleblack);
        	}
        	
        	titleindexx += size;
        }
        // 统计项
        rowtemp = rowMap.getRow(0);
        celltemp = rowtemp.createCell(titleindexx);
		celltemp.setCellValue("单项合计");
		celltemp.setCellStyle(styleblack);
		celltemp = rowtemp.createCell(titleindexx + 1);
		celltemp.setCellValue("单项总分");
		celltemp.setCellStyle(styleblack);
		celltemp = rowtemp.createCell(titleindexx + 2);
		celltemp.setCellValue("占比");
		celltemp.setCellStyle(styleblack);
        
        
    	// 行表头
        List<CheckTitle> list2 =  assessDao.getCheckTitle(yenei);
        Map<String, Float> scoremap = assessDao.getScore(date, yenei);
        
        int titleindex = 4;
        for(int i=0, length = list2.size(); i<length; i++) {
        	CheckTitle title = list2.get(i);
        	int size = title.getCount();
        	sheet2.addMergedRegion(new CellRangeAddress(titleindex, titleindex+size+1, 0, 0));
        	
        	rowtemp = rowMap.getRow(titleindex);
        	celltemp = rowtemp.createCell(0);
        	celltemp.setCellValue(title.getName());
        	celltemp.setCellStyle(stylesimple);
        	
        	for(int j = 0;j<size;j++) {
        		rowtemp = rowMap.getRow((int) titleindex + j);
        		celltemp = rowtemp.createCell(1);
        		celltemp.setCellValue(title.getSub().get(j).getId());
        		celltemp.setCellStyle(stylesimple);
        		celltemp = rowtemp.createCell(2);
        		celltemp.setCellValue(title.getSub().get(j).getName());
        		celltemp.setCellStyle(stylesimpleLeft);
        		celltemp = rowtemp.createCell(3);
        		celltemp.setCellValue(title.getSub().get(j).getScore());
        		celltemp.setCellStyle(stylesimple);
        	}
        	
        	
        	
        	sheet2.addMergedRegion(new CellRangeAddress(titleindex + size , titleindex + size, 1, 2));
        	sheet2.addMergedRegion(new CellRangeAddress(titleindex + size + 1 , titleindex + size + 1, 1, 2));
        	
        	rowtemp = rowMap.getRow(titleindex + size);
        	celltemp = rowtemp.createCell(1);
    		celltemp.setCellValue("小计");
    		celltemp.setCellStyle(styleblack);
    		celltemp = rowtemp.createCell(3);
    		celltemp.setCellValue(title.getScore());
    		celltemp.setCellStyle(stylesimple);
    		rowtemp = rowMap.getRow(titleindex + size + 1);
        	celltemp = rowtemp.createCell(1);
    		celltemp.setCellValue("占比");
    		celltemp.setCellStyle(styleblack);
        	
        	titleindex += (size+2);
        }
        
        // fill data
        titleindexx = 4;
        for(int i=0, lengthx = pianquList.size(); i<lengthx; i++) {
        	PianquRelationHead title = pianquList.get(i);
        	int size = title.getCount();
        	
        	for(int j = 0;j<size;j++) {
        		
        		int pianquid = title.getSub().get(j).getId();
        		
        		titleindex = 4;
        		for(int k=0, lengthy = list2.size(); k<lengthy; k++) {
        			CheckTitle titlexiangmu = list2.get(k);
                	int sizexiangmu = titlexiangmu.getCount();
                	for(int l = 0;l<sizexiangmu;l++) {
                		int assessid = titlexiangmu.getSub().get(l).getId();
                		
                		Float value = scoremap.get(pianquid+"|"+assessid);
                		if(value != null) {
                			rowtemp = rowMap.getRow(titleindex + l);
                			celltemp = rowtemp.createCell(titleindexx + j);
                			//celltemp.setCellValue(value);
                			celltemp.setCellFormula(String.format("ROUND(%f,1)", value));
                			celltemp.setCellStyle(stylesimple);
                		}
                	}
                	
                	int indexx = titleindexx + j ;
                	char charx = (char)('A' + indexx);
                	int endy =  titleindex + sizexiangmu;
                	int beginy = titleindex + sizexiangmu - sizexiangmu + 1;
                	rowtemp = rowMap.getRow(titleindex + sizexiangmu);
                	celltemp = rowtemp.createCell(titleindexx + j);
        			celltemp.setCellFormula(String.format("SUM(%c%d:%c%d)", charx, beginy, charx, endy));
        			celltemp.setCellStyle(styleblack);
        			rowtemp = rowMap.getRow(titleindex + sizexiangmu + 1);
        			celltemp = rowtemp.createCell(titleindexx + j);
        			celltemp.setCellFormula(String.format("ROUND(%c%d/D%d,4)", charx, endy + 1, endy + 1));
        			celltemp.setCellStyle(styleblackbaifenbi);
        			
                	titleindex += (sizexiangmu + 2);
        		}
        	}
        	
        	titleindexx += size;
        }
        
        int countx = titleindexx - 3;
        titleindex = 4;
        for(int i=0, length = list2.size(); i<length; i++) {
        	CheckTitle title = list2.get(i);
        	int size = title.getCount();
        	for(int j = 0;j<=size;j++) {
        		rowtemp = rowMap.getRow(titleindex + j);
        		celltemp = rowtemp.createCell(titleindexx);
            	celltemp.setCellFormula(String.format("SUM(E%d:%c%d)", titleindex + j +1 , titleindexx + 'A' - 1, titleindex + j+1 ));
            	celltemp = rowtemp.createCell(titleindexx + 1);
            	celltemp.setCellFormula(String.format("D%d*%d", titleindex + j +1 , countx));
            	celltemp = rowtemp.createCell(titleindexx + 2);
            	celltemp.setCellFormula(String.format("ROUND(%c%d/%c%d,4)", titleindexx + 'A', titleindex + j +1 , titleindexx+ 1 + 'A', titleindex + j +1 ));
            	celltemp.setCellStyle(styleblackbaifenbi);
        	}
        	titleindex += (size+2);
        }
	}
	
	private boolean convertHtmlToPdf(String inputFile, String outputFile)  throws Exception {  
		          
		        OutputStream os = new FileOutputStream(outputFile);       
		        ITextRenderer renderer = new ITextRenderer();       
		        String url = new File(inputFile).toURI().toURL().toString();   
		         
		        renderer.setDocument(url);     
		          
		        // 解决中文支持问题       
		        ITextFontResolver fontResolver = renderer.getFontResolver();      
		        //fontResolver.addFont("C:/Windows/Fonts/ARIALUNI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);   
		        fontResolver.addFont("/home/htdocs/wuye/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
		        //解决图片的相对路径问题  
		        //renderer.getSharedContext().setBaseURL("file:/D:/");  
		        renderer.layout();      
		        renderer.createPDF(os);    
		          
		        os.flush();  
		        os.close();  
		        return true;  
	}  
	
	private class Row {
		HashMap<Integer,HSSFRow> bb = new HashMap<>();
		private HSSFSheet sheet;
		
		public Row(HSSFSheet sheet) {
			this.sheet = sheet;
		}
		public HSSFRow getRow(int n) {
			if(bb.containsKey(n)) {
				return bb.get(n);
			}
			HSSFRow row = sheet.createRow(n);
			bb.put(n, row);
			return row;
		}
		
	}

	@Override
	public int delAssess(String serialid) {
		return assessDao.delAssess(serialid);
	}

}
