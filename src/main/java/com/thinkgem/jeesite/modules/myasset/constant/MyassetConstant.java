package com.thinkgem.jeesite.modules.myasset.constant;

public class MyassetConstant {
	private MyassetConstant(){}
	public static final int ASSET_BAR_CODE_LENGTH=22;
	public static final class OsPlatFormId {
		private OsPlatFormId() {}
		
		public static final String PC = "1";
		public static final String PDA = "2";
		
	}
	public static final class AssetStatus {
		//借用单状态（1-在库；2-使用；3-维护；4-报废）
		
		private AssetStatus() {}
		
		public static final String STOCK = "1";
		public static final String USING = "2";
		public static final String MAINTAIN = "3";
		public static final String SCRAP = "3";
		
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
	public static final class MaintainBillStatus {
		//维护单状态（0-申请中；1-维护中；2-已维护）
		
		private MaintainBillStatus() {}
		
		public static final String APPLY = "0";
		public static final String MAINTAIN = "1";
		
	}
	public static final class AssetIsMaintain {
		//资产是否归还(0-借用；1-归还)
		
		private AssetIsMaintain() {}
		
		public static final String NO = "0";
		public static final String YES = "1";
		
	}
	
	public static final class TransferBillStatus {
		//转移单状态（0-申请中；1-转移）
		
		private TransferBillStatus() {}
		
		public static final String APPLY = "0";
		public static final String TRANSFER = "1";
		
	}
	public static final class AssetIsTransfer {
		//资产是否归还(0-借用；1-归还)
		
		private AssetIsTransfer() {}
		
		public static final String NO = "0";
		public static final String YES = "1";
		
	}
	public static final class ScrapBillStatus {
		//报废单状态（0-申请中；1-报废）
		
		private ScrapBillStatus() {}
		
		public static final String APPLY = "0";
		public static final String SCRAP = "1";
		
	}
	public static final class AssetIsScrap {
		//资产是否归还(0-借用；1-归还)
		
		private AssetIsScrap() {}
		
		public static final String NO = "0";
		public static final String YES = "1";
		
	}
	
	public enum AssetBigClass{
		
		T01_DIAN_NENG_BIAO("01","电能表"),
		T02_HU_GAN_QI("02","互感器"),
		T05_JI_LIANG_XIANG("05","计量箱（屏、柜）"),
		T09_DIAN_NENG_COLLECT_TERM("09","电能信息采集终端"),
		T10_JI_LIANG_STANDARD("10","计量标准"),
		T13_TEST_DEVICE("13","测试装置"),
		T14_OTHER_YIQI_YIBIAO("14","其他仪器仪表"),
		T19_ZHOU_ZHUAN_XIANG("19","周转箱（托盘）"),
		T20_HANDLE_PDA("20","现场手持终端"),
		T50_HUI_LU_PATROL("50","回路状态巡检仪"),
		T51_REVERSE_STOLE_DEVICE("51","反窃电装置"),
		T52_PIPELINE_DEVICE("52","流水线设备"),
		T53_STOCK_DEVICE("53","仓储设备"),
		T54_COMM_MODULE("54","通信模块");
		
		private String value;
		private String desc;
		public String getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		private AssetBigClass(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		public static AssetBigClass getAssetBigClass(String value) {
	    	for (AssetBigClass e : AssetBigClass.values()) {
	            if(e.getValue().equalsIgnoreCase(value)) {
	            	return e;
	            }
	        }
	    	return null;
	    }

		
		
	}
	
}
