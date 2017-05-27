package com.bstek.ureport.build.paging;

import java.util.List;

import com.bstek.ureport.model.Column;
import com.bstek.ureport.model.Row;

/**
 * @author Jacky.gao
 * @since 2017年1月17日
 */
public class Page {
//	private Map<Row,Map<Column,Cell>> rowColCellMap;
	private List<Row> rows;
	private List<Column> columns;
	private HeaderFooter header;
	private HeaderFooter footer;
	
	public Page(List<Row> rows,List<Column> columns) {
//		this.rowColCellMap = rowColCellMap;
		this.rows = rows;
		this.columns=columns;
	}
	/*public Map<Row, Map<Column, Cell>> getRowColCellMap() {
		return rowColCellMap;
	}*/
	public List<Row> getRows() {
		return rows;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public HeaderFooter getHeader() {
		return header;
	}
	public void setHeader(HeaderFooter header) {
		this.header = header;
	}
	public HeaderFooter getFooter() {
		return footer;
	}
	public void setFooter(HeaderFooter footer) {
		this.footer = footer;
	}
}