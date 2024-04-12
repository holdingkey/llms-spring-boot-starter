package com.holdingkey.llms.basic.mode.chat;

import com.holdingkey.llms.basic.IPlatform;
import com.holdingkey.llms.basic.dto.req.IReq;
import com.holdingkey.llms.basic.dto.resp.IResp;

public interface IChat<Platform extends IPlatform, Req extends IReq, Resp extends IResp> {

    void setPlatform(Platform platform);

    IChat req(Req req);

    Resp send();

}
