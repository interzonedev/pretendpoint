package com.interzonedev.pretendpoint.web.status;

import com.interzonedev.pretendpoint.web.PretendPointResponse;

public class StatusResponse extends PretendPointResponse {

	public long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

}
