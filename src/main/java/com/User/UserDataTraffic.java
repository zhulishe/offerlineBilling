package com.User;

import com.utils.FormatConvert;

/**
 * 用于统计该用户流量的使用情况
 * @author aby
 *
 */
public class UserDataTraffic {
	private String bandName;
	private float basic_fee;
	private long free_gn;
	private long free_sn;
	private long free_bd;

	// 超过部分的单价
	private float gn_unitPrice;	
	private float sn_unitPrice;
	private float bd_unitPrice;
	
	private float total_fee = 0; // 总费用
	
	// 超出部分总量
	private long exceed_gn = 0;
	private long exceed_sn = 0;
	private long exceed_bd = 0;
	
	// 剩余部分总量
	private long residue_gn = 0;
	private long residue_sn = 0;
	private long residue_bd = 0;
	
	public UserDataTraffic(String bandName, float basic_fee, long free_gn, long free_sn, long free_bd, float gn_unitPrice, float sn_unitPrice,
			float bd_unitPrice) {
		super();
		this.bandName = bandName;
		this.basic_fee = basic_fee;
		this.free_gn = free_gn;
		this.free_sn = free_sn;
		this.free_bd = free_bd;
		this.gn_unitPrice = gn_unitPrice;
		this.sn_unitPrice = sn_unitPrice;
		this.bd_unitPrice = bd_unitPrice;
	}
	
	
	public float getBasic_fee() {
		return basic_fee;
	}


	public void setBasic_fee(float basic_fee) {
		this.basic_fee = basic_fee;
	}


	public float getTotal_fee() {
		return total_fee;
	}


	public void setTotal_fee(float total_fee) {
		this.total_fee = total_fee;
	}


	public String getBandName() {
		return bandName;
	}
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	public float getGn_unitPrice() {
		return gn_unitPrice;
	}
	public void setGn_unitPrice(float gn_unitPrice) {
		this.gn_unitPrice = gn_unitPrice;
	}
	public float getSn_unitPrice() {
		return sn_unitPrice;
	}
	public void setSn_unitPrice(float sn_unitPrice) {
		this.sn_unitPrice = sn_unitPrice;
	}
	public float getBd_unitPrice() {
		return bd_unitPrice;
	}
	public void setBd_unitPrice(float bd_unitPrice) {
		this.bd_unitPrice = bd_unitPrice;
	}
	public long getFree_gn() {
		return free_gn;
	}
	public void setFree_gn(long free_gn) {
		this.free_gn = free_gn;
	}
	public long getFree_sn() {
		return free_sn;
	}
	public void setFree_sn(long free_sn) {
		this.free_sn = free_sn;
	}
	public long getFree_bd() {
		return free_bd;
	}
	public void setFree_bd(long free_bd) {
		this.free_bd = free_bd;
	}
	public long getExceed_gn() {
		return exceed_gn;
	}
	public void setExceed_gn(long exceed_gn) {
		this.exceed_gn = exceed_gn;
	}
	public long getExceed_sn() {
		return exceed_sn;
	}
	public void setExceed_sn(long exceed_sn) {
		this.exceed_sn = exceed_sn;
	}
	public long getExceed_bd() {
		return exceed_bd;
	}
	public void setExceed_bd(long exceed_bd) {
		this.exceed_bd = exceed_bd;
	}
	public long getResidue_gn() {
		return residue_gn;
	}
	public void setResidue_gn(long residue_gn) {
		this.residue_gn = residue_gn;
	}
	public long getResidue_sn() {
		return residue_sn;
	}
	public void setResidue_sn(long residue_sn) {
		this.residue_sn = residue_sn;
	}
	public long getResidue_bd() {
		return residue_bd;
	}
	public void setResidue_bd(long residue_bd) {
		this.residue_bd = residue_bd;
	}
	
	// 增加一段国内的数据流量
	public void addDataTrafficGN(String traffic){
		long quantity = FormatConvert.getKB(traffic);	// 将字符串表示法的流量转化为用KB为单位表示的long类型
		addDataTrafficGN(quantity);
	}
	public void addDataTrafficGN(long quantity){
		if (free_gn >= quantity) {
			free_gn -= quantity;
		}else {
			quantity -= free_gn;
			free_gn = 0;
			exceed_gn += quantity;
		}
	} 
	
	
	// 增加一段省内的数据流量
	public void addDataTrafficSN(String traffic){
		long quantity = FormatConvert.getKB(traffic);	// 将字符串表示法的流量转化为用KB为单位表示的long类型
		addDataTrafficSN(quantity);
	}
	public void addDataTrafficSN(long quantity){
		if (free_sn >= quantity) {
			free_sn -= quantity;
		}else {
			quantity -= free_sn;		//扣完省内剩余免费流量后还剩余的流量数
			free_sn = 0;
			
			// 省内流量用完了，可以使用国内免费流量
			if (free_gn > quantity) {
				addDataTrafficGN(quantity);
			}else {
				quantity -= free_gn;
				addDataTrafficGN(free_gn);
				exceed_sn += quantity;
			}
		}
	} 
	
	
	// 增加一段本地数据流量
		public void addDataTrafficBD(String traffic){
			long quantity = FormatConvert.getKB(traffic);	// 将字符串表示法的流量转化为用KB为单位表示的long类型
			addDataTrafficBD(quantity);
		}
		public void addDataTrafficBD(long quantity){
			if (free_bd >= quantity) {
				free_bd -= quantity;
			}else {
				quantity -= free_bd;
				free_bd = 0;
				
				// 省内流量用完了，可以使用国内免费流量
				if ((free_gn  +  free_sn ) >= quantity) {
					addDataTrafficSN(quantity);
				}else {
					quantity -= (free_gn + free_sn);
					addDataTrafficSN(free_gn + free_gn);
					exceed_bd += quantity;
				}
			}
		} 
}
