package com.thinkgem.jeesite.modules.myasset.util;

import com.myxapp.sdk.sequence.util.SeqUtil;
import com.myxapp.sdk.util.DateUtil;

public class PandianSeqUtil {
	public static String getPandianBaseBatchUuid() {
		String seqPrefix=DateUtil.getDateString(DateUtil.YYYYMMDD);
		return seqPrefix+SeqUtil.getNewId("PANDIAN_BASE$BATCH_UUID$SEQ$"+seqPrefix, 4);
	}
}
