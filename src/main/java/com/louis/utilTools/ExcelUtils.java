package com.louis.utilTools;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Created on 16/12/28. excel操作工具类
 */
public class ExcelUtils {

	private ExcelUtils() {

	}

	/**
	 * 设置边框为黑色
	 * 
	 * @param cellStyle
	 */
	public static void setNormalBorder(CellStyle cellStyle) {
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
	}

	/**
	 * @param workbook
	 * @return
	 */
	public static CellStyle buildCenterCellStyle(Workbook workbook) {
		CellStyle centerCellStyle = workbook.createCellStyle();
		centerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		centerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		setNormalBorder(centerCellStyle);
		centerCellStyle.setWrapText(true);
		return centerCellStyle;
	}

	/**
	 * @param workbook
	 * @return
	 */
	public static CellStyle buildLeftCellStyle(Workbook workbook) {
		CellStyle leftCellStyle = workbook.createCellStyle();
		leftCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		leftCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		setNormalBorder(leftCellStyle);
		leftCellStyle.setWrapText(true);
		return leftCellStyle;
	}

	/**
	 * 构建order属性的cellStyle
	 * 
	 * @param workbook
	 * @return
	 */
	public static CellStyle buildOrderCellStyle(Workbook workbook) {
		CellStyle orderCellStyle = workbook.createCellStyle();
		Font orderFont = workbook.createFont();
		orderFont.setFontHeightInPoints((short) 12);
		orderFont.setFontName("黑体");
		orderFont.setColor(Font.COLOR_NORMAL);
		orderFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		orderCellStyle.setFont(orderFont);
		orderCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		orderCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		orderCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		return orderCellStyle;
	}
}
