package com.thinkgem.jeesite.modules.myasset.constant;

public class MyassetConstant {
	private MyassetConstant(){}
	
	public static final class OsPlatFormId {
		private OsPlatFormId() {}
		
		public static final String PC = "1";
		public static final String PDA = "2";
		
	}
	public static final class BorrowBillStatus {
		//借用单状态（0-全部未归还；1-部分归还；2-全部归还）
		
		private BorrowBillStatus() {}
		
		public static final String ALL_NOT_RETURN = "0";
		public static final String PART_RETURN = "1";
		public static final String ALL_RETURNED = "2";
		
	}
	public static final class AssetIsReturn {
		//资产是否归还(0-借用；1-归还)
		
		private AssetIsReturn() {}
		
		public static final String NOT_RETURN = "0";
		public static final String RETURN = "1";
		
	}
	
	
}
