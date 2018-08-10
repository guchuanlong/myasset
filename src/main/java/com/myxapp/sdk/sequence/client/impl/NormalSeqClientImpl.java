package com.myxapp.sdk.sequence.client.impl;

import com.myxapp.sdk.sequence.client.ISeqClient;
import com.myxapp.sdk.sequence.service.ISequenceService;
import com.myxapp.sdk.sequence.service.impl.SequenceServiceImpl;

public class NormalSeqClientImpl implements ISeqClient {

    private ISequenceService sequenceService;

    public NormalSeqClientImpl() {
        this.sequenceService = new SequenceServiceImpl();
    }

    @Override
    public Long nextValue(String sequenceName) {
        return sequenceService.nextValue(sequenceName);
    }

	@Override
	public void modifySequence(String sequenceName, long nextVal) {
		sequenceService.modifySequence(sequenceName, nextVal);
	}

}
